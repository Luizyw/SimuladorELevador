import java.io.Serializable;

// Implementa uma fila ligada simples com prioridade para pessoas idosas ou cadeirantes
public class FilaDePrioridade implements Serializable {
    private No inicio;    // Primeiro nó da fila
    private No fim;       // Último nó da fila
    private int tamanho;  // Quantidade de elementos na fila

    // Nó da fila contendo uma pessoa e referência para o próximo nó
    private class No {
        Pessoa pessoa;
        No proximo;

        No(Pessoa pessoa) {
            this.pessoa = pessoa;
            this.proximo = null;
        }
    }

    // Enfileira uma pessoa respeitando a prioridade
    public void enfileirar(Pessoa pessoa) {
        No novo = new No(pessoa);
        if (inicio == null) {
            // Fila vazia, novo nó é início e fim
            inicio = fim = novo;
        } else {
            if (temPrioridade(novo.pessoa)) {
                // Pessoa com prioridade
                if (!temPrioridade(inicio.pessoa)) {
                    // Início não tem prioridade, insere novo no início
                    novo.proximo = inicio;
                    inicio = novo;
                } else {
                    // Percorre até o último com prioridade para inserir depois
                    No atual = inicio;
                    while (atual.proximo != null && temPrioridade(atual.proximo.pessoa)) {
                        atual = atual.proximo;
                    }
                    novo.proximo = atual.proximo;
                    atual.proximo = novo;
                }
            } else {
                // Pessoa sem prioridade vai para o fim
                fim.proximo = novo;
                fim = novo;
            }
        }
        tamanho++;
    }

    // Remove e retorna a pessoa da frente da fila
    public Pessoa desenfileirar() {
        if (inicio == null) return null;
        Pessoa p = inicio.pessoa;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        tamanho--;
        return p;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    // Verifica se pessoa tem prioridade (idade >= 60 ou cadeirante)
    private boolean temPrioridade(Pessoa p) {
        return p.getIdade() >= 60 || p.isCadeirante();
    }
}
