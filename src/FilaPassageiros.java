public class FilaPassageiros {
    private Passageiro[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    // Construtor que inicializa a fila com a capacidade informada
    public FilaPassageiros(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new Passageiro[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    // Método para adicionar um passageiro na fila (enfileirar)
    public boolean enfileirar(Passageiro p) {
        if (tamanho == capacidade)   // Se a fila estiver cheia não pode adicionar
            return false;

        elementos[fim] = p;
        fim = (fim + 1) % capacidade;
        tamanho++;
        return true;
    }

    // Método para remover um passageiro da fila (desenfileirar)
    public Passageiro desenfileirar() {
        if (tamanho == 0)            // Se a fila estiver vazia, não tem o que remover
            return null;

        Passageiro p = elementos[inicio];
        elementos[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return p;
    }

    // Aqui verifica se a fila está vazia
    public boolean estaVazia() {
        return tamanho == 0;
    }

    //E Aqui retorna a quantidade de passageiros na fila
    public int tamanho() {
        return tamanho;
    }


    @Override
    public String toString() {
        if (estaVazia()) return "Fila vazia";

        StringBuilder sb = new StringBuilder();
        int i = inicio;
        for (int c = 0; c < tamanho; c++) {
            sb.append(elementos[i].toString()).append(" | ");
            i = (i + 1) % capacidade;
        }
        return sb.toString();
    }
}

