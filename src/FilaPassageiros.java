public class FilaPassageiros {
    private Passageiro[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaPassageiros(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new Passageiro[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean enfileirar(Passageiro p) {
        if (tamanho == capacidade) {
            return false;
        }
        elementos[fim] = p;
        fim = (fim + 1) % capacidade;
        tamanho++;
        return true;
    }

    public Passageiro desenfileirar() {
        if (tamanho == 0) {
            return null;
        }
        Passageiro p = elementos[inicio];
        elementos[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return p;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public String toString() {
        if (estaVazia()) return "Fila vazia";

        StringBuilder sb = new StringBuilder();
        int i = inicio;
        for (int c = 0; c < tamanho; c++) {
            Passageiro p = elementos[i];
            sb.append(p.toString()).append(" | ");
            i = (i + 1) % capacidade;
        }
        return sb.toString();
    }
}
