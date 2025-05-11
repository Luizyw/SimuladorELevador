// Classe responsável por representar uma unidade de medição de energia e tempo.
// Essa classe é útil para acompanhar o consumo energético ao longo do tempo,
// por exemplo, em um simulador de elevadores onde queremos analisar a eficiência do sistema.
public class UnidadeEnergia {

    // Armazena a quantidade total de energia consumida até o momento.
    private int totalEnergia;

    // Armazena o tempo total de operação acumulado até o momento.
    private int tempoTotal;

    // Construtor padrão que inicializa os valores de energia e tempo com zero.
    public UnidadeEnergia() {
        this.totalEnergia = 0;
        this.tempoTotal = 0;
    }

    // Método utilizado para registrar um novo consumo de energia e o tempo correspondente.
    // Os valores recebidos são somados aos totais já acumulados.
    public void adicionarConsumo(int energia, int tempo) {
        totalEnergia += energia; // Acumula a energia consumida
        tempoTotal += tempo;     // Acumula o tempo de operação
    }

    // Retorna o total de energia consumida até agora.
    public int getTotalEnergia() {
        return totalEnergia;
    }

    // Retorna o tempo total registrado até agora.
    public int getTempoTotal() {
        return tempoTotal;
    }

    // Calcula e retorna o consumo médio de energia por unidade de tempo.
    // Evita divisão por zero retornando 0 caso nenhum tempo tenha sido registrado.
    public double calcularConsumoPorTempo() {
        return tempoTotal == 0 ? 0 : (double) totalEnergia / tempoTotal;
    }
}
