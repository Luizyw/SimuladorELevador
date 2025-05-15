// Classe que representa uma lista encadeada simples,
// sem uso de estruturas prontas do Java.
// Permite inserir no final, acessar por índice e remover um objeto específico.
public class Lista {
    private Nodo inicio;   // Referência para o primeiro nodo da lista
    private Nodo fim;      // Referência para o último nodo da lista
    private int tamanho;   // Contador de elementos presentes na lista

    // Construtor que inicializa uma lista vazia
    public Lista() {
        this.inicio = null;  // Lista inicia vazia, sem nós
        this.fim = null;
        this.tamanho = 0;
    }

    // Método para inserir um novo objeto no fim da lista
    public void inserirFim(Object objeto) {
        Nodo novoNodo = new Nodo(objeto);  // Cria novo nodo com o objeto

        if (fim == null) {
            // Caso a lista esteja vazia, novo nodo é o início e fim
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            // Caso contrário, adiciona após o nodo fim e atualiza fim
            fim.setProximo(novoNodo);
            fim = novoNodo;
        }
        tamanho++;  // Incrementa tamanho da lista
    }

    // Método para obter o objeto presente em uma posição específica da lista
    public Object get(int indice) {
        if (indice >= tamanho || indice < 0) {
            // Se índice inválido, lança exceção
            throw new IndexOutOfBoundsException();
        }

        Nodo atual = inicio;  // Começa pelo primeiro nodo
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();  // Avança até o nodo do índice
        }
        return atual.getObjeto();  // Retorna o objeto do nodo
    }

    // Método que retorna a quantidade de elementos na lista
    public int tamanho() {
        return tamanho;
    }

    // Método para remover o primeiro objeto igual ao fornecido
    // Retorna true se conseguiu remover, false se objeto não foi encontrado
    public boolean remover(Object objeto) {
        if (inicio == null) {
            // Lista vazia: nada a remover
            return false;
        }

        // Caso o objeto esteja no início da lista
        if (inicio.getObjeto().equals(objeto)) {
            inicio = inicio.getProximo();  // Atualiza início para o próximo nodo

            if (inicio == null) {
                // Se a lista ficou vazia após remoção, fim também deve ser nulo
                fim = null;
            }

            tamanho--;  // Decrementa tamanho da lista
            return true;
        }

        // Percorre a lista procurando o nodo com o objeto a ser removido
        Nodo atual = inicio;
        while (atual.getProximo() != null) {
            if (atual.getProximo().getObjeto().equals(objeto)) {
                // Encontra o nodo que deve ser removido e atualiza os links
                atual.setProximo(atual.getProximo().getProximo());

                if (atual.getProximo() == null) {
                    // Se removeu o último nodo, atualiza referência do fim
                    fim = atual;
                }

                tamanho--;  // Decrementa tamanho
                return true;  // Remoção concluída com sucesso
            }
            atual = atual.getProximo();  // Continua procurando
        }

        return false;  // Objeto não encontrado na lista
    }
}
