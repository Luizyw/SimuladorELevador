import java.util.Random;

// Classe que controla os elevadores, filas de pessoas e lógica principal
public class CentralDeControle {
    private Elevador[] elevadores;          // Array de elevadores
    private Lista[] filasPorAndar;          // Filas de espera por andar (listas de pessoas)
    private UnidadeEnergia unidadeEnergia;  // Registro do consumo energético
    private int totalAndares;               // Total de andares do prédio
    private int capacidadeElevador = 5;    // Capacidade fixa do elevador
    private int totalPessoasGeradas = 0;   // Contador para limitar pessoas geradas
    private int maxPessoas = 10;            // Máximo de pessoas a serem geradas na simulação
    private Random random;                  // Para gerar pessoas aleatórias

    // Construtor que inicializa os elevadores e filas de espera
    public CentralDeControle(int quantidadeElevadores, int totalAndares, UnidadeEnergia unidadeEnergia) {
        this.totalAndares = totalAndares;
        this.unidadeEnergia = unidadeEnergia;
        elevadores = new Elevador[quantidadeElevadores];
        filasPorAndar = new Lista[totalAndares];
        random = new Random();

        // Inicializa elevadores
        for (int i = 0; i < quantidadeElevadores; i++) {
            elevadores[i] = new Elevador(capacidadeElevador);
        }

        // Inicializa filas vazias em cada andar
        for (int i = 0; i < totalAndares; i++) {
            filasPorAndar[i] = new Lista();
        }
    }

    // Método para gerar pessoas aleatórias em andares aleatórios
    private void gerarPessoasAleatorias() {
        if (totalPessoasGeradas >= maxPessoas) return;

        // Gera até 1 pessoa por segundo (pode ser zero também)
        int pessoasAGerar = random.nextInt(2); // 0 ou 1 pessoa
        for (int i = 0; i < pessoasAGerar && totalPessoasGeradas < maxPessoas; i++) {
            int idade = random.nextInt(90) + 1; // idade entre 1 e 90
            boolean cadeirante = random.nextBoolean();
            int andarOrigem = random.nextInt(totalAndares);
            int andarDestino;
            do {
                andarDestino = random.nextInt(totalAndares);
            } while (andarDestino == andarOrigem); // destino diferente da origem

            Pessoa novaPessoa = new Pessoa(idade, cadeirante, andarDestino);
            filasPorAndar[andarOrigem].inserirFim(novaPessoa);
            totalPessoasGeradas++;

            System.out.println("Nova pessoa gerada: Idade = " + idade + ", Cadeirante = " + (cadeirante ? "Sim" : "Não") + ", Andar origem: " + andarOrigem + ", Andar destino: " + andarDestino);
        }
    }

    // Atualiza o estado da central e dos elevadores a cada ciclo (segundo)
    public void atualizar(int tempoAtual) {
        System.out.println("\n--- Ciclo de simulação: segundo " + tempoAtual + " ---");

        // Gera pessoas aleatórias
        gerarPessoasAleatorias();

        // Para cada elevador, movimenta e trata entrada/saída de pessoas
        for (int i = 0; i < elevadores.length; i++) {
            Elevador elev = elevadores[i];

            int andarAnterior = elev.getAndarAtual();

            // Move o elevador
            elev.mover(totalAndares);

            // Se houve movimentação, adiciona consumo de energia
            if (elev.getAndarAtual() != andarAnterior) {
                unidadeEnergia.adicionarConsumo(1, 1); // 1 unidade energia, 1 segundo
            }

            // Pessoas saindo no andar atual
            Lista saindo = elev.pessoasSaindoNoAndar();
            for (int j = 0; j < saindo.tamanho(); j++) {
                Pessoa p = (Pessoa) saindo.get(j);
                System.out.println("Elevador " + i + ": Pessoa saindo -> Idade: " + p.getIdade() + ", Cadeirante: " + (p.isCadeirante() ? "Sim" : "Não") + ", Prioridade: " + (p.temPrioridade() ? "Sim" : "Não"));
                elev.removerPessoa(p);
            }

            // Pessoas entrando no elevador no andar atual
            Lista fila = filasPorAndar[elev.getAndarAtual()];
            Lista restantes = new Lista(); // Pessoas que não entraram pois elevador cheio

            for (int j = 0; j < fila.tamanho(); j++) {
                Pessoa p = (Pessoa) fila.get(j);
                boolean entrou = elev.adicionarPessoa(p);
                if (entrou) {
                    System.out.println("Andar " + elev.getAndarAtual() + ": Pessoa entrando no elevador -> Idade: " + p.getIdade() + ", Cadeirante: " + (p.isCadeirante() ? "Sim" : "Não") + ", Prioridade: " + (p.temPrioridade() ? "Sim" : "Não"));
                } else {
                    restantes.inserirFim(p); // não entrou, fica esperando
                }
            }

            // Atualiza fila do andar com as pessoas que não conseguiram entrar
            filasPorAndar[elev.getAndarAtual()] = restantes;
        }
    }
}
