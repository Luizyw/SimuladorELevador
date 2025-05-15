public class Simulador {
    private CentralDeControle central;      // Controla os elevadores e andares
    private int tempoSimulado;              // Duração da simulação em segundos
    private UnidadeEnergia unidadeEnergia;  // Mede consumo de energia

    // Construtor para inicializar o simulador
    public Simulador(int quantidadeElevadores, int quantidadeAndares, int tempoSimulado) {
        this.unidadeEnergia = new UnidadeEnergia();
        this.central = new CentralDeControle(quantidadeElevadores, quantidadeAndares, unidadeEnergia);
        this.tempoSimulado = tempoSimulado;
    }

    // Método que inicia e executa a simulação
    public void iniciar() {
        for (int segundo = 1; segundo <= tempoSimulado; segundo++) {
            System.out.println("\n--- Ciclo de simulação: segundo " + segundo + " ---");

            // Atualiza a lógica da central de controle (movimentação, chamadas, etc)
            central.atualizar(segundo);

            // Aqui você pode incluir a geração de pessoas, entrada/saída nos elevadores e prints

            // Pausa de 1 segundo para simular tempo real (pode ser removida para acelerar testes)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Após a simulação, imprime o consumo de energia e tempo total
        System.out.println("\nSimulação concluída.");
        System.out.println("Energia total consumida: " + unidadeEnergia.getTotalEnergia() + " unidades.");
        System.out.println("Tempo total de operação: " + unidadeEnergia.getTempoTotal() + " segundos.");
        System.out.println("Consumo médio por segundo: " + unidadeEnergia.calcularConsumoPorTempo() + " unidades/segundo.");
    }
}
