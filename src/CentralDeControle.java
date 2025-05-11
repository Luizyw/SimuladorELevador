// Classe responsável por centralizar o controle dos elevadores.
// Ela mantém uma lista de elevadores e é responsável por atualizá-los a cada ciclo de tempo.
// Também mantém uma referência à unidade de energia usada para registrar o consumo energético.
public class CentralDeControle {

    // Lista de elevadores controlados pelo sistema.
    private Lista elevadores;

    // Referência compartilhada à unidade de energia — usada por todos os elevadores
    // para registrar consumo de forma centralizada.
    private UnidadeEnergia unidadeEnergia;

    // Construtor da central. Recebe a quantidade de elevadores e andares, além da referência à unidade de energia.
    public CentralDeControle(int quantidadeElevadores, int quantidadeAndares, UnidadeEnergia unidadeEnergia) {
        this.unidadeEnergia = unidadeEnergia;
        elevadores = new Lista();  // Criação da lista de elevadores

        // Para cada elevador, cria uma instância e adiciona ao final da lista.
        // A referência de unidadeEnergia é passada para que cada elevador registre seu consumo nela.
        for (int i = 0; i < quantidadeElevadores; i++) {
            elevadores.inserirFim(new Elevador(i, unidadeEnergia));
        }
    }

    // Atualiza todos os elevadores da lista com base no tempo atual da simulação (segundo).
    // Esse método deve ser chamado em cada ciclo da simulação.
    public void atualizar(int segundo) {
        for (int i = 0; i < elevadores.tamanho(); i++) {
            Elevador elevador = (Elevador) elevadores.get(i);  // Recupera o elevador da posição i
            elevador.atualizar(segundo);  // Atualiza seu estado para o tempo atual
        }
    }
}
