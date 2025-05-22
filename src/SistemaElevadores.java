public class SistemaElevadores {
    // Limite máximo de passageiros que a fila de espera pode conter
    private static final int MAX_PASSAGEIROS_FILAS = 100;

    private int numElevadores; // Quantidade de elevadores no sistema
    private Elevador[] elevadores; // Array que guarda os elevadores
    private Passageiro[] filaEspera;  // Passageiros esperando para embarcar
    private int qtdFila; // Quantidade atual de passageiros na fila
    private int tempo; // Tempo fictício da simulação, contado em segundos ou ciclos
    private int totalEnergiaConsumida; // Energia total consumida pelo sistema
    private int totalAndaresPercorridos; // Total de andares percorridos pelos elevadores

    // Construtor do sistema, recebe número de elevadores e capacidades de cada um
    public SistemaElevadores(int numElevadores, int capacidadePassageirosElevador, int capacidadePesoElevador) {
        this.numElevadores = numElevadores;
        this.elevadores = new Elevador[numElevadores]; // Cria o array de elevadores com o tamanho informado
        // Inicializa cada elevador, dando um ID sequencial, capacidade de peso e de passageiros
        for (int i = 0; i < numElevadores; i++) {
            elevadores[i] = new Elevador(i+1, capacidadePesoElevador, capacidadePassageirosElevador);
        }
        this.filaEspera = new Passageiro[MAX_PASSAGEIROS_FILAS]; // Cria a fila de espera com limite fixo
        this.qtdFila = 0; // No começo, fila está vazia
        this.tempo = 0; // Simulação começa no tempo zero
        this.totalEnergiaConsumida = 0; // Nenhuma energia consumida ainda
        this.totalAndaresPercorridos = 0; // Nenhum andar percorrido ainda
    }

    // Método para adicionar um passageiro na fila de espera
    public void adicionarPassageiroNaFila(Passageiro p) {
        // Só adiciona se ainda não chegou no limite da fila
        if (qtdFila < MAX_PASSAGEIROS_FILAS) {
            filaEspera[qtdFila] = p; // Coloca passageiro na próxima posição livre da fila
            qtdFila++; // Incrementa a quantidade na fila
        } else {
            // Caso a fila já esteja cheia, avisa que não dá para adicionar mais passageiros
            System.out.println("Fila de espera cheia, não foi possível adicionar passageiro: " + p);
        }
    }

    // Método privado que procura na fila o passageiro prioritário (idoso ou cadeirante)
    // mais próximo do andar atual do elevador. Retorna índice na fila ou -1 se não encontrar
    private int buscarPassageiroPrioritario(int andarElevador) {
        int idxPrioritario = -1; // Índice do passageiro prioritário encontrado, -1 se nenhum
        int menorDistancia = 1000; // Guarda a menor distância encontrada (um valor grande inicial)
        for (int i = 0; i < qtdFila; i++) {
            Passageiro p = filaEspera[i];
            // Verifica se o passageiro ainda não embarcou e se tem prioridade
            if (!p.isEmbarcado() && p.isPrioridade()) {
                int dist = Math.abs(p.getAndarOrigem() - andarElevador); // Calcula distância até o elevador
                if (dist < menorDistancia) {
                    menorDistancia = dist; // Atualiza menor distância
                    idxPrioritario = i; // Guarda índice desse passageiro
                }
            }
        }
        return idxPrioritario; // Retorna índice do passageiro prioritário mais próximo ou -1
    }

    // Método parecido com o anterior, mas busca passageiro normal (sem prioridade)
    private int buscarPassageiroNormal(int andarElevador) {
        int idxNormal = -1;
        int menorDistancia = 1000;
        for (int i = 0; i < qtdFila; i++) {
            Passageiro p = filaEspera[i];
            if (!p.isEmbarcado() && !p.isPrioridade()) {
                int dist = Math.abs(p.getAndarOrigem() - andarElevador);
                if (dist < menorDistancia) {
                    menorDistancia = dist;
                    idxNormal = i;
                }
            }
        }
        return idxNormal;
    }

    // Remove um passageiro da fila, deslocando os demais à esquerda para preencher o espaço
    private void removerDaFila(int idx) {
        for (int i = idx; i < qtdFila - 1; i++) {
            filaEspera[i] = filaEspera[i+1]; // Desloca passageiro seguinte para a posição atual
        }
        filaEspera[qtdFila-1] = null; // Última posição fica vazia (null)
        qtdFila--; // Decrementa a quantidade na fila
    }

    // Método que executa um ciclo da simulação, fazendo os elevadores funcionarem
    public void operarUmCiclo() {
        tempo++; // Incrementa o tempo da simulação, um ciclo a mais
        System.out.println("\n--- Ciclo " + tempo + " ---");

        // Para cada elevador
        for (int i = 0; i < numElevadores; i++) {
            Elevador e = elevadores[i];

            // Primeiro, desembarca passageiros que devem sair no andar atual
            if (e.desembarcarPassageirosNoAndar()) {
                System.out.println("Elevador " + e.getId() + ": passageiros desembarcaram no andar " + e.getAndarAtual());
            }

            // Embarca primeiro os passageiros prioritários (cadeirantes e idosos)
            while (true) {
                int idxPrioritario = buscarPassageiroPrioritario(e.getAndarAtual()); // Procura passageiro prioritário nesse andar
                if (idxPrioritario == -1) break; // Se não tem nenhum, sai do loop

                Passageiro p = filaEspera[idxPrioritario];
                // Só embarca se passageiro estiver exatamente no andar do elevador
                if (p.getAndarOrigem() == e.getAndarAtual()) {
                    if (e.podeEmbarcar(p)) { // Verifica se elevador tem capacidade para embarcar
                        e.embarcar(p); // Embarca passageiro
                        removerDaFila(idxPrioritario); // Remove passageiro da fila
                        System.out.println("Elevador " + e.getId() + ": embarcou passageiro prioritário " + p);
                    } else {
                        break; // Se não pode embarcar mais ninguém, sai do loop
                    }
                } else {
                    break; // Se passageiro prioritário não está nesse andar, sai do loop
                }
            }

            // Depois, embarca passageiros normais, se ainda houver capacidade
            while (true) {
                int idxNormal = buscarPassageiroNormal(e.getAndarAtual()); // Procura passageiro normal nesse andar
                if (idxNormal == -1) break; // Se não achar, sai do loop

                Passageiro p = filaEspera[idxNormal];
                if (p.getAndarOrigem() == e.getAndarAtual()) {
                    if (e.podeEmbarcar(p)) {
                        e.embarcar(p);
                        removerDaFila(idxNormal);
                        System.out.println("Elevador " + e.getId() + ": embarcou passageiro normal " + p);
                    } else {
                        break; // Sem capacidade para mais passageiros, sai do loop
                    }
                } else {
                    break; // Passageiro não está neste andar, sai do loop
                }
            }

            // Agora decide para qual andar o elevador vai se mover no próximo ciclo

            int proxAndar = -1; // Guarda o próximo andar para onde o elevador deve ir
            int menorDistancia = 1000; // Inicializa distância mínima para buscar destino

            // Se o elevador tem passageiros, tenta priorizar o desembarque do passageiro mais próximo
            for (int j = 0; j < e.getQtdPassageiros(); j++) {
                Passageiro p = e.getPassageiros()[j];
                int dist = Math.abs(p.getAndarDestino() - e.getAndarAtual());
                if (dist < menorDistancia) {
                    menorDistancia = dist;
                    proxAndar = p.getAndarDestino();
                }
            }

            // Se não tem passageiros dentro, tenta buscar passageiros na fila para atender
            if (proxAndar == -1) {
                int idxPri = buscarPassageiroPrioritario(e.getAndarAtual()); // Busca passageiro prioritário na fila
                if (idxPri != -1) {
                    proxAndar = filaEspera[idxPri].getAndarOrigem();
                } else {
                    int idxNor = buscarPassageiroNormal(e.getAndarAtual()); // Busca passageiro normal
                    if (idxNor != -1) {
                        proxAndar = filaEspera[idxNor].getAndarOrigem();
                    }
                }
            }

            // Se tem um andar destino diferente do andar atual, move o elevador para lá
            if (proxAndar != -1 && proxAndar != e.getAndarAtual()) {
                e.moverParaAndar(proxAndar);
                System.out.println("Elevador " + e.getId() + " moveu para andar " + proxAndar);
            } else {
                // Caso contrário, elevador fica parado nesse ciclo
                System.out.println("Elevador " + e.getId() + " está parado no andar " + e.getAndarAtual());
            }

            // Imprime status atual do elevador (provavelmente toString)
            System.out.println("Status: " + e);
        }
    }

    // Verifica se a simulação terminou: fila vazia e nenhum passageiro dentro dos elevadores
    public boolean simFinalizada() {
        if (qtdFila > 0) return false; // Ainda tem gente esperando na fila
        for (int i = 0; i < numElevadores; i++) {
            if (elevadores[i].getQtdPassageiros() > 0) return false; // Ainda tem gente dentro do elevador
        }
        return true; // Se chegou aqui, acabou a simulação
    }

    // Imprime um resumo da simulação: tempo, total de andares percorridos e energia consumida
    public void imprimirResumo() {
        int totalAndares = 0;
        int totalEnergia = 0;
        // Soma dados de todos os elevadores
        for (int i = 0; i < numElevadores; i++) {
            totalAndares += elevadores[i].getAndaresPercorridos();
            totalEnergia += elevadores[i].getEnergiaConsumida();
        }
        System.out.println("\n=== Resumo da Simulação ===");
        System.out.println("Tempo total (ciclos): " + tempo);
        System.out.println("Andares percorridos total: " + totalAndares);
        System.out.println("Energia consumida total: " + totalEnergia);
        // Consumo médio de energia por ciclo, formatado com 2 casas decimais
        System.out.println(String.format("Consumo médio por ciclo: %.2f", (totalEnergia * 1.0 / tempo)));
    }

    // Imprime os passageiros ainda esperando na fila
    public void imprimirFila() {
        System.out.println("Fila de espera:");
        for (int i = 0; i < qtdFila; i++) {
            Passageiro p = filaEspera[i];
            System.out.println(p); // Aqui p.toString() deve mostrar dados do passageiro
        }
    }

    // Método para simular um dia completo - está vazio por enquanto, pode implementar depois
    public void simularDiaCompleto() {

    }
}
