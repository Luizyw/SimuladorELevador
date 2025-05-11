public class Pessoa {
    // Atributos da classe Pessoa
    private int idade;        // Armazena a idade da pessoa
    private boolean cadeirante; // Indica se a pessoa é cadeirante ou não

    // Construtor da classe Pessoa que recebe idade e o status de cadeirante
    public Pessoa(int idade, boolean cadeirante) {
        this.idade = idade;           // Inicializa o atributo idade com o valor passado
        this.cadeirante = cadeirante; // Inicializa o atributo cadeirante com o valor passado
    }

    // Método getter para obter a idade da pessoa
    public int getIdade() {
        return idade;  // Retorna a idade da pessoa
    }

    // Método getter para verificar se a pessoa é cadeirante
    public boolean isCadeirante() {
        return cadeirante;  // Retorna o status de cadeirante (verdadeiro ou falso)
    }

    // Sobrescrita do método toString para exibir informações da pessoa de forma legível
    @Override
    public String toString() {
        // Retorna uma string com as informações de idade e se é cadeirante ou não
        return "Pessoa{" +
                "idade=" + idade +
                ", cadeirante=" + cadeirante +
                '}';
    }
}
