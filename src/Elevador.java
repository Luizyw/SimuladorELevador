/**
 * Classe que representa um elevador no sistema.
 * Controla passageiros embarcados, capacidade máxima de peso,
 * andar atual, energia consumida e andares percorridos.
 */
public class Elevador {
    private int id;                       // Identificador único do elevador
    private int andarAtual;               // Andar em que o elevador está atualmente
    private int capacidadePesoMax;        // Capacidade máxima de peso em kg
    private Passageiro[] passageiros;     // Array para armazenar passageiros dentro do elevador
    private int qtdPassageiros;           // Quantidade atual de passageiros embarcados
    private int pesoAtual;                // Peso total atual dos passageiros dentro do elevador
    private int andaresPercorridos;       // Contador de andares percorridos pelo elevador
    private int energiaConsumida;         // Energia consumida pelo elevador em unidades arbitrárias

    /**
     * Construtor para criar um elevador com capacidades definidas.
     * Inicializa arrays e variáveis de controle.
     *
     * @param id Identificador do elevador
     * @param capacidadePesoMax Capacidade máxima em kg
     * @param capacidadePassageiros Capacidade máxima em número de passageiros
     */
    public Elevador(int id, int capacidadePesoMax, int capacidadePassageiros) {
        this.id = id;
        this.capacidadePesoMax = capacidadePesoMax;
        this.passageiros = new Passageiro[capacidadePassageiros]; // Array para passageiros
        this.qtdPassageiros = 0;    // Nenhum passageiro embarcado inicialmente
        this.pesoAtual = 0;         // Peso inicial zero
        this.andaresPercorridos = 0;
        this.energiaConsumida = 0;
        this.andarAtual = 0;        // Elevador inicia no térreo (andar 0)
    }

    /** Retorna o identificador do elevador. */
    public int getId() {
        return id;
    }

    /** Retorna o andar atual do elevador. */
    public int getAndarAtual() {
        return andarAtual;
    }

    /** Retorna o peso total atual dentro do elevador. */
    public int getPesoAtual() {
        return pesoAtual;
    }

    /** Retorna o total de andares percorridos pelo elevador. */
    public int getAndaresPercorridos() {
        return andaresPercorridos;
    }

    /** Retorna a energia consumida pelo elevador. */
    public int getEnergiaConsumida() {
        return energiaConsumida;
    }

    /** Retorna o array de passageiros embarcados no elevador. */
    public Passageiro[] getPassageiros() {
        return passageiros;
    }

    /** Retorna a quantidade atual de passageiros no elevador. */
    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    /**
     * Verifica se o passageiro pode embarcar no elevador, considerando
     * limite de peso e quantidade máxima de passageiros.
     *
     * @param p Passageiro a ser verificado
     * @return true se o passageiro pode embarcar, false caso contrário
     */
    public boolean podeEmbarcar(Passageiro p) {
        return (pesoAtual + p.getPeso() <= capacidadePesoMax) && (qtdPassageiros < passageiros.length);
    }

    /**
     * Tenta embarcar um passageiro no elevador.
     * Atualiza peso e quantidade de passageiros.
     * Marca passageiro como embarcado.
     *
     * @param p Passageiro a embarcar
     * @return true se o embarque foi realizado, false caso contrário
     */
    public boolean embarcar(Passageiro p) {
        if (!podeEmbarcar(p)) {
            return false; // Não embarca se exceder capacidade
        }
        passageiros[qtdPassageiros] = p; // Adiciona passageiro no array
        qtdPassageiros++;                // Incrementa quantidade
        pesoAtual += p.getPeso();        // Atualiza peso total
        p.setEmbarcado(true);            // Marca passageiro como embarcado
        return true;
    }

    /**
     * Desembarca todos os passageiros cujo andar destino é o andar atual.
     * Remove passageiros do array e atualiza peso e quantidade.
     *
     * @return true se ao menos um passageiro desembarcou, false caso contrário
     */
    public boolean desembarcarPassageirosNoAndar() {
        boolean algumDesembarcou = false;
        int i = 0;
        while (i < qtdPassageiros) {
            if (passageiros[i].getAndarDestino() == andarAtual) {
                // Remove peso do passageiro que desembarca
                pesoAtual -= passageiros[i].getPeso();
                passageiros[i].setEmbarcado(false);

                // Remove passageiro do array deslocando os demais à esquerda
                for (int j = i; j < qtdPassageiros - 1; j++) {
                    passageiros[j] = passageiros[j + 1];
                }
                passageiros[qtdPassageiros - 1] = null;
                qtdPassageiros--;
                algumDesembarcou = true;
                // Não incrementa i pois o próximo passageiro já está na posição i
            } else {
                i++;
            }
        }
        return algumDesembarcou;
    }

    /**
     * Move o elevador para o andar destino.
     * Atualiza andares percorridos, energia consumida e andar atual.
     *
     * @param destino Andar para onde o elevador deve se mover
     */
    public void moverParaAndar(int destino) {
        int distancia = Math.abs(destino - andarAtual);
        if (distancia > 0) {
            andaresPercorridos += distancia;
            energiaConsumida += distancia * 10; // Consumo fictício: 10 unidades por andar
            andarAtual = destino;
        }
    }

    /**
     * Retorna uma string resumida com informações dos passageiros embarcados.
     * Exibe id, peso e se possuem prioridade.
     */
    public String passageirosToString() {
        if (qtdPassageiros == 0) return "Nenhum passageiro";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < qtdPassageiros; i++) {
            Passageiro p = passageiros[i];
            sb.append(String.format("P%d(id:%d,peso:%dkg,pri:%b) ", i+1, p.getId(), p.getPeso(), p.isPrioridade()));
        }
        return sb.toString();
    }

    /**
     * Retorna uma string com o estado atual do elevador, incluindo
     * andar, peso, quantidade de passageiros, andares percorridos e energia consumida.
     */
    @Override
    public String toString() {
        return String.format(
                "Elevador %d [Andar atual: %d, Peso atual: %d kg, Passageiros: %d, Andares percorridos: %d, Energia: %d]",
                id, andarAtual, pesoAtual, qtdPassageiros, andaresPercorridos, energiaConsumida);
    }
}
