public class FilaPassageiros {
    // Array para armazenar os elementos da fila (passageiros)
    private Passageiro[] elementos;
    // Índice do primeiro elemento da fila (o próximo a sair)
    private int inicio;
    // Índice da próxima posição livre para enfileirar
    private int fim;
    // Quantidade atual de elementos na fila
    private int tamanho;
    // Capacidade máxima da fila (tamanho do array)
    private int capacidade;

    // Construtor: inicializa a fila com a capacidade especificada
    public FilaPassageiros(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new Passageiro[capacidade]; // cria o array para armazenar os passageiros
        this.inicio = 0;  // fila inicialmente vazia, início no índice 0
        this.fim = 0;     // fim também no índice 0 (sem elementos ainda)
        this.tamanho = 0; // tamanho 0 porque não tem elementos
    }

    // Método para adicionar um passageiro ao final da fila (enfileirar)
    public boolean enfileirar(Passageiro p) {
        // Se a fila estiver cheia, não é possível adicionar, retorna false
        if (tamanho == capacidade) {
            return false;
        }
        // Insere o passageiro na posição indicada por 'fim'
        elementos[fim] = p;
        // Incrementa 'fim' circularmente para a próxima posição válida no array
        fim = (fim + 1) % capacidade;
        // Incrementa o tamanho da fila
        tamanho++;
        return true; // sucesso ao enfileirar
    }

    // Método para remover e retornar o passageiro do início da fila (desenfileirar)
    public Passageiro desenfileirar() {
        // Se a fila estiver vazia, retorna null para indicar que não há elemento
        if (tamanho == 0) {
            return null;
        }
        // Guarda o passageiro que está no início da fila
        Passageiro p = elementos[inicio];
        // Remove a referência no array para ajudar a liberar memória (garbage collection)
        elementos[inicio] = null;
        // Incrementa 'inicio' circularmente para apontar para o próximo elemento da fila
        inicio = (inicio + 1) % capacidade;
        // Decrementa o tamanho da fila
        tamanho--;
        // Retorna o passageiro removido
        return p;
    }

    // Verifica se a fila está vazia (tamanho 0)
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Retorna a quantidade atual de passageiros na fila
    public int tamanho() {
        return tamanho;
    }

    // Representação em String da fila para exibição
    public String toString() {
        // Se estiver vazia, retorna mensagem simples
        if (estaVazia()) return "Fila vazia";

        StringBuilder sb = new StringBuilder();
        int i = inicio;
        // Percorre todos os elementos da fila, respeitando a ordem circular
        for (int c = 0; c < tamanho; c++) {
            Passageiro p = elementos[i];
            sb.append(p.toString()).append(" | ");  // concatena a string do passageiro
            i = (i + 1) % capacidade; // incrementa circularmente o índice
        }
        return sb.toString();
    }
}
