public class Simulador {
    private CentralDeControle central;  // Instância da CentralDeControle que gerencia os elevadores e andares
    private int tempoSimulado;  // Tempo de simulação em segundos
    private UnidadeEnergia unidadeEnergia;  // Instância para registrar o consumo de energia durante a simulação

    // Construtor que inicializa o simulador com a quantidade de elevadores, andares e o tempo de simulação
    public Simulador(int quantidadeElevadores, int quantidadeAndares, int tempoSimulado) {
        this.unidadeEnergia = new UnidadeEnergia();  // Cria a instância da UnidadeEnergia
        this.central = new CentralDeControle(quantidadeElevadores, quantidadeAndares, unidadeEnergia);  // Inicializa a central de controle com os parâmetros fornecidos
        this.tempoSimulado = tempoSimulado;  // Atribui o tempo de simulação
    }

    // Método que inicia o simulador
    public void iniciar() {
        // Loop para simular o tempo, a cada iteração é um segundo
        for (int i = 0; i < tempoSimulado; i++) {
            central.atualizar(i + 1);  // Atualiza a central de controle no segundo atual
            try {
                Thread.sleep(1000);  // Pausa de 1 segundo entre os ciclos de simulação
            } catch (InterruptedException e) {
                e.printStackTrace();  // Trata possíveis exceções de interrupção
            }
        }

        // Após a simulação, exibe os resultados do consumo de energia
        System.out.println("Simulação concluída.");
        System.out.println("Energia total consumida: " + unidadeEnergia.getTotalEnergia() + " unidades.");
        System.out.println("Tempo total de operação: " + unidadeEnergia.getTempoTotal() + " segundos.");
        System.out.println("Consumo médio por segundo: " + unidadeEnergia.calcularConsumoPorTempo() + " unidades/segundo.");
    }
}
