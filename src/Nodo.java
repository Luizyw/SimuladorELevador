public class Nodo {
    private Object objeto;  // Atributo para armazenar o objeto contido no nodo
    private Nodo proximo;   // Referência para o próximo nodo na lista

    // Construtor para inicializar o nodo com um objeto
    public Nodo(Object objeto) {
        this.objeto = objeto;  // Atribui o objeto ao atributo 'objeto'
        this.proximo = null;   // Inicializa o 'proximo' como nulo, pois não há próximo nodo no momento
    }

    // Método para obter o objeto armazenado no nodo
    public Object getObjeto() {
        return objeto;  // Retorna o objeto contido no nodo
    }

    // Método para definir o próximo nodo da lista
    public void setProximo(Nodo proximo) {
        this.proximo = proximo;  // Atribui o próximo nodo à referência 'proximo'
    }

    // Método para obter o próximo nodo
    public Nodo getProximo() {
        return proximo;  // Retorna o próximo nodo referenciado
    }
}
