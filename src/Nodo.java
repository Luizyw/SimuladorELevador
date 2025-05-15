// Classe que representa um nodo da lista encadeada.
// Cada nodo armazena um objeto genérico e referência para o próximo nodo.
public class Nodo {
    private Object objeto;   // Objeto armazenado no nodo
    private Nodo proximo;    // Referência para o próximo nodo na lista

    // Construtor que inicializa o nodo com o objeto fornecido
    public Nodo(Object objeto) {
        this.objeto = objeto;     // Atribui o objeto ao nodo
        this.proximo = null;      // Inicializa o próximo nodo como nulo
    }

    // Método para obter o objeto armazenado no nodo
    public Object getObjeto() {
        return objeto;
    }

    // Método para obter o próximo nodo da lista
    public Nodo getProximo() {
        return proximo;
    }

    // Método para definir o próximo nodo da lista
    public void setProximo(Nodo proximo) {
        this.proximo = proximo;
    }
}
