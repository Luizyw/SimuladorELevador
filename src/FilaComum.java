import java.io.Serializable;

// Classe que implementa uma fila simples, armazenando objetos Pessoa
public class FilaComum implements Serializable {

    // Atributos para gerenciar a fila
    private No inicio;  // O início da fila, onde as pessoas entram
    private No fim;     // O fim da fila, onde as pessoas saem
    private int tamanho; // O tamanho atual da fila (quantas pessoas estão na fila)

    // Classe interna No, que representa um nó da fila. Cada nó contém uma Pessoa e um ponteiro para o próximo nó.
    private static class No implements Serializable {
        Pessoa pessoa;  // Pessoa armazenada no nó
        No proximo;     // Ponteiro para o próximo nó na fila

        // Construtor que inicializa o nó com uma pessoa
        No(Pessoa pessoa) {
            this.pessoa = pessoa;
            this.proximo = null;
        }
    }

    // Método para adicionar uma pessoa à fila
    public void enfileirar(Pessoa pessoa) {
        // Cria um novo nó com a pessoa
        No novo = new No(pessoa);

        // Se a fila não está vazia, adiciona o novo nó ao final
        if (fim != null) {
            fim.proximo = novo;  // O último nó aponta para o novo nó
        } else {
            inicio = novo;  // Se a fila estava vazia, o novo nó é o início
        }

        // Atualiza o ponteiro fim para o novo nó
        fim = novo;

        // Aumenta o tamanho da fila
        tamanho++;
    }

    // Método para remover e retornar a pessoa do início da fila
    public Pessoa desenfileirar() {
        // Se a fila estiver vazia, retorna null
        if (inicio == null) return null;

        // Armazena a pessoa que está no início da fila
        Pessoa p = inicio.pessoa;

        // Move o ponteiro início para o próximo nó
        inicio = inicio.proximo;

        // Se a fila ficou vazia, o ponteiro fim também deve ser nulo
        if (inicio == null) fim = null;

        // Diminui o tamanho da fila
        tamanho--;

        // Retorna a pessoa removida
        return p;
    }

    // Método que verifica se a fila está vazia
    public boolean estaVazia() {
        // A fila está vazia se o tamanho for zero
        return tamanho == 0;
    }

    // Método para obter o tamanho atual da fila
    public int tamanho() {
        // Retorna o número de pessoas na fila
        return tamanho;
    }
}
