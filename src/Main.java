// Classe principal que contém o método main, ponto de entrada da aplicação.
// Aqui são definidos os parâmetros da simulação e ela é iniciada.
public class Main {
    public static void main(String[] args) {
        // Define o número de elevadores que estarão disponíveis no sistema.
        int quantidadeElevadores = 3;

        // Define o número de andares do prédio. Neste caso, um prédio com 10 andares (0 a 9).
        int quantidadeAndares = 10;

        // Define o tempo total da simulação (em ciclos ou unidades arbitrárias de tempo).
        int tempoSimulado = 20;

        // Cria uma instância do simulador com os parâmetros definidos acima.
        Simulador simulador = new Simulador(quantidadeElevadores, quantidadeAndares, tempoSimulado);

        // Inicia a simulação. Este método deve conter toda a lógica de movimentação dos elevadores,
        // geração de pessoas, consumo de energia, etc.
        simulador.iniciar();
    }
}
