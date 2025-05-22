public class SistemaElevadores {
    private static final int MAX_PASSAGEIROS_FILAS = 100;

    private int numElevadores;
    private Elevador[] elevadores;
    private Passageiro[] filaEspera;
    private int qtdFila;
    private int tempo;
    private int totalEnergiaConsumida;
    private int totalAndaresPercorridos;

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

    public void adicionarPassageiroNaFila(Passageiro p) {
        if (qtdFila < MAX_PASSAGEIROS_FILAS) {
            filaEspera[qtdFila] = p;
            qtdFila++;
        } else {
            System.out.println("Fila de espera cheia, não foi possível adicionar passageiro: " + p);
        }
    }

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

    private void removerDaFila(int idx) {
        for (int i = idx; i < qtdFila - 1; i++) {
            filaEspera[i] = filaEspera[i + 1];
        }
        filaEspera[qtdFila - 1] = null;
        qtdFila--;
    }

    public void operarUmCiclo() {
        tempo++;
        System.out.println("\n--- Ciclo " + tempo + " ---");

        for (int i = 0; i < numElevadores; i++) {
            Elevador e = elevadores[i];

            if (e.desembarcarPassageirosNoAndar()) {
                System.out.println("Elevador " + e.getId() + ": passageiros desembarcaram no andar " + e.getAndarAtual());
            }

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

            while (true) {
                int idxNormal = buscarPassageiroNormal(e.getAndarAtual());
                if (idxNormal == -1) break;
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

            int proxAndar = -1;
            int menorDistancia = 1000;

            for (int j = 0; j < e.getQtdPassageiros(); j++) {
                Passageiro p = e.getPassageiros()[j];
                int dist = Math.abs(p.getAndarDestino() - e.getAndarAtual());
                if (dist < menorDistancia) {
                    menorDistancia = dist;
                    proxAndar = p.getAndarDestino();
                }
            }

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

            if (proxAndar != -1 && proxAndar != e.getAndarAtual()) {
                e.moverParaAndar(proxAndar);
                System.out.println("Elevador " + e.getId() + " moveu para andar " + proxAndar);
            } else {
                System.out.println("Elevador " + e.getId() + " está parado no andar " + e.getAndarAtual());
            }

            System.out.println("Status: " + e);
        }
    }

    public boolean simFinalizada() {
        if (qtdFila > 0) return false;
        for (int i = 0; i < numElevadores; i++) {
            if (elevadores[i].getQtdPassageiros() > 0) return false;
        }
        return true;
    }

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
