public class Passageiro {
    private int id;
    private int idade;
    private boolean cadeirante;
    private int peso;
    private int andarOrigem;
    private int andarDestino;
    private boolean prioridade;
    private boolean embarcado;

    public Passageiro(int id, int idade, boolean cadeirante, int peso, int andarOrigem, int andarDestino) {
        this.id = id;
        this.idade = idade;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.andarOrigem = andarOrigem;
        this.andarDestino = andarDestino;
        this.prioridade = cadeirante || idade >= 60;
        this.embarcado = false;
    }

    public int getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public boolean isCadeirante() {
        return cadeirante;
    }

    public int getPeso() {
        return peso;
    }

    public int getAndarOrigem() {
        return andarOrigem;
    }

    public int getAndarDestino() {
        return andarDestino;
    }

    public boolean isPrioridade() {
        return prioridade;
    }

    public boolean isEmbarcado() {
        return embarcado;
    }

    public void setEmbarcado(boolean embarcado) {
        this.embarcado = embarcado;
    }

    @Override
    public String toString() {
        return String.format(
                "Passageiro %d [Idade: %d, Cadeirante: %b, Peso: %dkg, Origem: %d, Destino: %d, Prioridade: %b]",
                id, idade, cadeirante, peso, andarOrigem, andarDestino, prioridade);
    }
}
