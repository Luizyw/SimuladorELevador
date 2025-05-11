import java.io.Serializable;

public class FilaDePrioridade implements Serializable {
    // Atributos da fila
    private No inicio;   // Referência para o primeiro elemento da fila
    private No fim;      // Referência para o último elemento da fila
    private int tamanho; // Armazena o tamanho da fila

    // Classe interna que representa um nó na fila
    private class No {
        Pessoa pessoa;  // A pessoa que está no nó
        No proximo;     // Referência para o próximo nó na fila

        // Construtor do nó, que recebe uma pessoa para colocar no nó
        No(Pessoa pessoa) {
            this.pessoa = pessoa;
        }
    }

    // Método para adicionar uma pessoa à fila com prioridade
    public void enfileirar(Pessoa pessoa) {
        No novo = new No(pessoa);  // Cria um novo nó com a pessoa fornecida
        if (inicio == null) {  // Se a fila estiver vazia
            inicio = fim = novo;  // O novo nó é o único na fila
        } else {
            // Inserção com base em prioridade (ex: idosos ou cadeirantes têm prioridade)
            if (temPrioridade(novo.pessoa)) {
                // Se a pessoa tem prioridade, insere antes de pessoas sem prioridade
                if (!temPrioridade(inicio.pessoa)) {
                    novo.proximo = inicio;  // Coloca o novo nó no início da fila
                    inicio = novo;          // Atualiza o início da fila
                } else {
                    No atual = inicio;  // Começa do início da fila
                    // Percorre a fila até encontrar a posição adequada para inserir
                    while (atual.proximo != null && temPrioridade(atual.proximo.pessoa)) {
                        atual = atual.proximo;
                    }
                    novo.proximo = atual.proximo;  // Faz o novo nó apontar para o próximo nó
                    atual.proximo = novo;          // Faz o nó atual apontar para o novo nó
                }
            } else {
                // Se a pessoa não tem prioridade, adiciona ao final da fila
                fim.proximo = novo;
                fim = novo;
            }
        }
        tamanho++;  // Incrementa o tamanho da fila
    }

    // Método para remover e retornar a pessoa da frente da fila
    public Pessoa desenfileirar() {
        if (inicio == null) return null;  // Se a fila estiver vazia, retorna null
        Pessoa p = inicio.pessoa;  // Armazena a pessoa do início da fila
        inicio = inicio.proximo;   // Atualiza o início da fila para o próximo nó
        if (inicio == null) fim = null;  // Se a fila ficou vazia, atualiza o fim também
        tamanho--;  // Decrementa o tamanho da fila
        return p;   // Retorna a pessoa que foi removida da fila
    }

    // Método para verificar se a fila está vazia
    public boolean estaVazia() {
        return tamanho == 0;  // Retorna verdadeiro se o tamanho for zero
    }

    // Método para obter o tamanho da fila
    public int tamanho() {
        return tamanho;  // Retorna o número de elementos na fila
    }

    // Método privado que verifica se a pessoa tem prioridade (idosos ou cadeirantes)
    private boolean temPrioridade(Pessoa p) {
        // A pessoa tem prioridade se tiver 60 anos ou mais, ou se for cadeirante
        return p.getIdade() >= 60 || p.isCadeirante();
    }
}
