public class Lista {
    // Atributos da classe Lista
    private Nodo inicio;  // Referência para o primeiro nodo (início da lista)
    private Nodo fim;     // Referência para o último nodo (fim da lista)
    private int tamanho;  // Contador para o número de elementos na lista

    // Construtor da classe Lista
    public Lista() {
        this.inicio = null;  // Inicializa o início da lista como nulo (lista vazia)
        this.fim = null;     // Inicializa o fim da lista como nulo
        this.tamanho = 0;    // Inicializa o tamanho da lista como 0
    }

    // Método para adicionar um novo objeto no final da lista
    public void inserirFim(Object objeto) {
        Nodo novoNodo = new Nodo(objeto);  // Cria um novo nodo com o objeto fornecido
        if (fim == null) {  // Se a lista estiver vazia (fim é nulo)
            inicio = novoNodo;  // O novo nodo se torna o início da lista
            fim = novoNodo;     // O novo nodo também se torna o fim da lista
        } else {
            fim.setProximo(novoNodo);  // Caso contrário, o novo nodo é adicionado após o último nodo
            fim = novoNodo;            // O novo nodo se torna o último nodo da lista
        }
        tamanho++;  // Incrementa o contador de tamanho da lista
    }

    // Método para obter um objeto de um índice específico
    public Object get(int indice) {
        if (indice >= tamanho || indice < 0) {  // Se o índice for inválido (fora do intervalo)
            throw new IndexOutOfBoundsException();  // Lança uma exceção
        }
        Nodo atual = inicio;  // Começa a busca a partir do início da lista
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();  // Navega até o índice desejado
        }
        return atual.getObjeto();  // Retorna o objeto encontrado no índice especificado
    }

    // Método para obter o tamanho da lista
    public int tamanho() {
        return tamanho;  // Retorna o número de elementos na lista
    }
}
