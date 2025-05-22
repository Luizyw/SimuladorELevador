public class SistemaElevadores {
    private static final int MAX_PASSAGEIROS_FILAS = 100;

    private int numElevadores;
    private Elevador[] elevadores;
    private Passageiro[] filaEspera;
    private int qtdFila;
    private int tempo;
    private int totalEnergiaConsumida;
    private int totalAndaresPercorridos;

    // Inicializa o sistema com os elevadores e a fila de espera
    public SistemaElevadores(int numElevadores, int capacidadePassageirosElevador, int capacidadePesoElevador) {
        this.numElevadores = numElevadores;
        this.elevadores = new Elevador[numElevadores];
        for (int i = 0; i < numElevadores; i++) {
            elevadores[i] = new Elevador(i + 1, capacidadePesoElevador, capacidadePassageirosElevador);
        }
        this.filaEspera = new Passageiro[MAX_PASSAGEIROS_FILAS];
        this.qtdFila = 0;
        this.tempo = 0;
        this.totalEnergiaConsumida = 0;
        this.totalAndaresPercorridos = 0;
    }

    // Adiciona passageiro na fila de espera
    public void adicionarPassageiroNaFila(Passageiro p) {
        if (qtdFila < MAX_PASSAGEIROS_FILAS) {
            filaEspera[qtdFila] = p;
            qtdFila++;
        } else {
            System.out.println("Fila de espera cheia, não foi possível adicionar passageiro: " + p);
        }
    }

    // Busca passageiro prioritário mais próximo do elevador
    private int buscarPassageiroPrioritario(int andarElevador) {
        int idxPrioritario = -1;
        int menorDistancia = 1000;
        for (int i = 0; i < qtdFila; i++) {
            Passageiro p = filaEspera[i];
            if (!p.isEmbarcado() && p.isPrioridade()) {
                int dist = Math.abs(p.getAndarOrigem() - andarElevador);
                if (dist < menorDistancia) {
                    menorDistancia = dist;
                    idxPrioritario = i;
                }
            }
        }
        return idxPrioritario;
    }

    // Busca passageiro normal mais próximo do elevador
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

    // Remove passageiro da fila de espera
    private void removerDaFila(int idx) {
        for (int i = idx; i < qtdFila - 1; i++) {
            filaEspera[i] = filaEspera[i + 1];
        }
        filaEspera[qtdFila - 1] = null;
        qtdFila--;
    }

    // Executa um ciclo da simulação
    public void operarUmCiclo() {
        tempo++;
        System.out.println("\n--- Ciclo " + tempo + " ---");

        for (int i = 0; i < numElevadores; i++) {
            Elevador e = elevadores[i];

            if (e.desembarcarPassageirosNoAndar()) {
                System.out.println("Elevador " + e.getId() + ": passageiros desembarcaram no andar " + e.getAndarAtual());
            }

            // Embarque prioritário
            while (true) {
                int idxPrioritario = buscarPassageiroPrioritario(e.getAndarAtual());
                if (idxPrioritario == -1) break;
                Passageiro p = filaEspera[idxPrioritario];
                if (p.getAndarOrigem() == e.getAndarAtual()) {
                    if (e.podeEmbarcar(p)) {
                        e.embarcar(p);
                        removerDaFila(idxPrioritario);
                        System.out.println("Elevador " + e.getId() + ": embarcou passageiro prioritário " + p);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // Embarque normal
            while (true) {
                int idxNormal = buscarPassageiroNormal(e.getAndarAtual());
                if (idxNormal == -1) break;    //Isso aqui faz procurar uma pessao sem prioridade
                Passageiro p = filaEspera[idxNormal];
                if (p.getAndarOrigem() == e.getAndarAtual()) {
                    if (e.podeEmbarcar(p)) {
                        e.embarcar(p);
                        removerDaFila(idxNormal);
                        System.out.println("Elevador " + e.getId() + ": embarcou passageiro normal " + p);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // Define próximo andar a ir
            int proxAndar = -1;
            int menorDistancia = 1000;    //Recebe um valor alto logo de começo para poder ver a menor distância possivel

            for (int j = 0; j < e.getQtdPassageiros(); j++) {
                Passageiro p = e.getPassageiros()[j];
                int dist = Math.abs(p.getAndarDestino() - e.getAndarAtual());
                if (dist < menorDistancia) {
                    menorDistancia = dist;
                    proxAndar = p.getAndarDestino();
                }
            }

            // Se não há passageiros embarcados, busca na fila
            if (proxAndar == -1) {
                int idxPri = buscarPassageiroPrioritario(e.getAndarAtual());
                if (idxPri != -1) {
                    proxAndar = filaEspera[idxPri].getAndarOrigem();
                } else {
                    int idxNor = buscarPassageiroNormal(e.getAndarAtual());
                    if (idxNor != -1) {
                        proxAndar = filaEspera[idxNor].getAndarOrigem();
                    }
                }
            }

            // Move elevador se necessário
            if (proxAndar != -1 && proxAndar != e.getAndarAtual()) {
                e.moverParaAndar(proxAndar);
                System.out.println("Elevador " + e.getId() + " moveu para andar " + proxAndar);
            } else {
                System.out.println("Elevador " + e.getId() + " está parado no andar " + e.getAndarAtual());
            }

            System.out.println("Status: " + e);
        }
    }

    // Verifica se a simulação terminou
    public boolean simFinalizada() {
        if (qtdFila > 0) return false;
        for (int i = 0; i < numElevadores; i++) {
            if (elevadores[i].getQtdPassageiros() > 0) return false;
        }
        return true;
    }

    // Imprime resumo final da simulação
    public void imprimirResumo() {
        int totalAndares = 0;
        int totalEnergia = 0;
        for (int i = 0; i < numElevadores; i++) {
            totalAndares += elevadores[i].getAndaresPercorridos();
            totalEnergia += elevadores[i].getEnergiaConsumida();
        }
        System.out.println("\n=== Resumo da Simulação ===");
        System.out.println("Tempo total (ciclos): " + tempo);
        System.out.println("Andares percorridos total: " + totalAndares);
        System.out.println("Energia consumida total: " + totalEnergia);
        System.out.println(String.format("Consumo médio por ciclo: %.2f", tempo > 0 ? ((double) totalEnergia / tempo) : 0.0));
    }
}
