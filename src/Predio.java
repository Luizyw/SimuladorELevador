import java.io.Serializable;

public class Predio implements Serializable {
    // Atributos da classe Predio
    private CentralDeControle central;  // Instância da CentralDeControle, responsável por controlar os elevadores
    private Lista andares;  // Lista personalizada que armazena os andares do prédio

    // Construtor da classe Predio
    public Predio(int quantidadeAndares, int quantidadeElevadores) {
        andares = new Lista();  // Inicializa a lista de andares, utilizando a classe Lista personalizada

        // Criação dos andares e adição à lista de andares
        for (int i = 0; i < quantidadeAndares; i++) {
            andares.inserirFim(new Andar(i));  // Cada andar é criado com um ID (índice) e adicionado à lista
        }

        // Criação de uma instância de UnidadeEnergia para monitorar o consumo de energia
        UnidadeEnergia unidadeEnergia = new UnidadeEnergia();

        // Criação da CentralDeControle, passando a quantidade de elevadores, andares e a instância de UnidadeEnergia
        central = new CentralDeControle(quantidadeElevadores, quantidadeAndares, unidadeEnergia);
    }

    // Método para obter a instância da CentralDeControle
    public CentralDeControle getCentral() {
        return central;  // Retorna a instância da CentralDeControle
    }

    // Método para obter a lista de andares do prédio
    public Lista getAndares() {
        return andares;  // Retorna a lista de andares do prédio
    }
}
