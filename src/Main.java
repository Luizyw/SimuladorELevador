// Classe principal que inicia a execução do programa
public class Main {
    public static void main(String[] args) {
        // Define o número de elevadores no sistema
        int quantidadeElevadores = 2;

        // Define o número de andares do prédio (0 a 9, total 10 andares)
        int quantidadeAndares = 10;

        // Define o tempo total da simulação em segundos (ou ciclos)
        int tempoSimulado = 20;

        // Cria uma instância do simulador com os parâmetros definidos
        Simulador simulador = new Simulador(quantidadeElevadores, quantidadeAndares, tempoSimulado);

        // Inicia a simulação - método que deve conter toda a lógica do simulador
        simulador.iniciar();
    }
}
