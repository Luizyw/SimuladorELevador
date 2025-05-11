// Classe que representa um elevador individual dentro do sistema.
// Cada elevador sabe seu andar atual, destino, direção, se está em movimento
// e tem acesso à unidade de energia para registrar seu consumo.
public class Elevador {

    // Identificador único do elevador
    private int id;

    // Andar atual onde o elevador está localizado
    private int andarAtual;

    // Próximo andar de destino
    private int andarDestino;

    // Flag que indica se o elevador está em movimento ou parado
    private boolean emMovimento;

    // Direção do movimento: 1 (subindo), -1 (descendo)
    private int direcao;

    // Referência à unidade de energia, usada para registrar o consumo
    private UnidadeEnergia unidadeEnergia;

    // Construtor que define o ID do elevador e recebe a instância de energia compartilhada
    public Elevador(int id, UnidadeEnergia unidadeEnergia) {
        this.id = id;
        this.andarAtual = 0; // Começa no térreo
        this.emMovimento = false; // Inicialmente parado
        this.unidadeEnergia = unidadeEnergia; // Associação com a unidade de energia
    }

    // Atualiza o estado do elevador a cada segundo da simulação
    public void atualizar(int segundo) {
        // Se o elevador estiver parado, decide um novo destino aleatório entre 0 e 9
        if (!emMovimento) {
            andarDestino = (int) (Math.random() * 10); // Gera andar aleatório
            if (andarDestino == andarAtual) return; // Se já estiver no destino, não faz nada

            // Define a direção com base no destino
            direcao = andarDestino > andarAtual ? 1 : -1;
            emMovimento = true; // Marca o elevador como em movimento

            // Log de início do movimento
            System.out.println("[" + segundo + "s] Elevador " + id + " iniciando movimento do andar " + andarAtual + " para o andar " + andarDestino);
        }

        // Move o elevador um andar por segundo, conforme a direção
        andarAtual += direcao;

        // Registra o consumo de 1 unidade de energia para 1 segundo de movimento
        unidadeEnergia.adicionarConsumo(1, 1);

        // Log de movimento atual
        System.out.println("[" + segundo + "s] Elevador " + id + " está no andar " + andarAtual);

        // Se chegou ao destino, para o movimento
        if (andarAtual == andarDestino) {
            System.out.println("[" + segundo + "s] Elevador " + id + " chegou ao destino: andar " + andarAtual);
            emMovimento = false;
        }
    }
}
