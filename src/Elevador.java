public class Elevador {
    private int id;
    private int andarAtual;
    private int capacidadePesoMax;
    private Passageiro[] passageiros;
    private int qtdPassageiros;
    private int pesoAtual;
    private int andaresPercorridos;
    private int energiaConsumida;

    public Elevador(int id, int capacidadePesoMax, int capacidadePassageiros) {
        this.id = id;
        this.capacidadePesoMax = capacidadePesoMax;
        this.passageiros = new Passageiro[capacidadePassageiros];
        this.qtdPassageiros = 0;
        this.pesoAtual = 0;
        this.andaresPercorridos = 0;
        this.energiaConsumida = 0;
        this.andarAtual = 0;
    }

    public int getId() {
        return id;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public int getPesoAtual() {
        return pesoAtual;
    }

    public int getAndaresPercorridos() {
        return andaresPercorridos;
    }

    public int getEnergiaConsumida() {
        return energiaConsumida;
    }

    public Passageiro[] getPassageiros() {
        return passageiros;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    // Verifica se o passageiro pode embarcar considerando peso e capacidade
    public boolean podeEmbarcar(Passageiro p) {
        return (pesoAtual + p.getPeso() <= capacidadePesoMax) && (qtdPassageiros < passageiros.length);
    }

    // Embarca o passageiro no elevador se possível
    public boolean embarcar(Passageiro p) {
        if (!podeEmbarcar(p)) {
            return false;
        }
        passageiros[qtdPassageiros] = p;
        qtdPassageiros++;
        pesoAtual += p.getPeso();
        p.setEmbarcado(true);
        return true;
    }

    // Desembarca passageiros cujo destino é o andar atual
    public boolean desembarcarPassageirosNoAndar() {
        boolean algumDesembarcou = false;
        int i = 0;
        while (i < qtdPassageiros) {
            if (passageiros[i].getAndarDestino() == andarAtual) {
                pesoAtual -= passageiros[i].getPeso();
                passageiros[i].setEmbarcado(false);
                // Remove passageiro e desloca os demais para a esquerda
                for (int j = i; j < qtdPassageiros - 1; j++) {
                    passageiros[j] = passageiros[j + 1];
                }
                passageiros[qtdPassageiros - 1] = null;
                qtdPassageiros--;
                algumDesembarcou = true;
            } else {
                i++;
            }
        }
        return algumDesembarcou;
    }

    // Move o elevador para o andar de destino, atualizando andares percorridos e energia consumida
    public void moverParaAndar(int destino) {
        int distancia = Math.abs(destino - andarAtual);
        if (distancia > 0) {
            andaresPercorridos += distancia;
            energiaConsumida += distancia * 10;
            andarAtual = destino;
        }
    }

    // Representa passageiros embarcados em string resumida
    public String passageirosToString() {
        if (qtdPassageiros == 0) return "Nenhum passageiro";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < qtdPassageiros; i++) {
            Passageiro p = passageiros[i];
            sb.append(String.format("P%d(id:%d,peso:%dkg,pri:%b) ", i + 1, p.getId(), p.getPeso(), p.isPrioridade()));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format(
                "Elevador %d [Andar atual: %d, Peso atual: %d kg, Passageiros: %d, Andares percorridos: %d, Energia: %d]",
                id, andarAtual, pesoAtual, qtdPassageiros, andaresPercorridos, energiaConsumida);
    }
}
