/**
 * Classe que representa um passageiro do sistema de elevadores.
 * Contém informações pessoais e de viagem, como idade, peso,
 * se é cadeirante, andar de origem e destino, e se possui prioridade.
 */
public class Passageiro {
    private int id;               // Identificador único do passageiro
    private int idade;            // Idade do passageiro
    private boolean cadeirante;   // Indica se o passageiro é cadeirante
    private int peso;             // Peso do passageiro em kg
    private int andarOrigem;      // Andar onde o passageiro inicia a viagem
    private int andarDestino;     // Andar para onde o passageiro deseja ir
    private boolean prioridade;   // Verdadeiro se o passageiro tem prioridade (idoso ou cadeirante)
    private boolean embarcado;    // Indica se o passageiro já está embarcado em um elevador

    /**
     * Construtor para criar um passageiro com suas características.
     * Define automaticamente se o passageiro tem prioridade (idoso >= 60 anos ou cadeirante).
     *
     * @param id Identificador único do passageiro
     * @param idade Idade do passageiro
     * @param cadeirante true se o passageiro é cadeirante, false caso contrário
     * @param peso Peso do passageiro em kg
     * @param andarOrigem Andar onde o passageiro inicia
     * @param andarDestino Andar destino para onde o passageiro deseja ir
     */
    public Passageiro(int id, int idade, boolean cadeirante, int peso, int andarOrigem, int andarDestino) {
        this.id = id;
        this.idade = idade;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.andarOrigem = andarOrigem;
        this.andarDestino = andarDestino;
        // Define prioridade: verdadeiro se for cadeirante ou tiver idade >= 60 anos
        this.prioridade = cadeirante || idade >= 60;
        // Inicialmente, passageiro não está embarcado
        this.embarcado = false;
    }

    /** Retorna o identificador único do passageiro. */
    public int getId() {
        return id;
    }

    /** Retorna a idade do passageiro. */
    public int getIdade() {
        return idade;
    }

    /** Retorna se o passageiro é cadeirante. */
    public boolean isCadeirante() {
        return cadeirante;
    }

    /** Retorna o peso do passageiro em kg. */
    public int getPeso() {
        return peso;
    }

    /** Retorna o andar de origem do passageiro. */
    public int getAndarOrigem() {
        return andarOrigem;
    }

    /** Retorna o andar destino do passageiro. */
    public int getAndarDestino() {
        return andarDestino;
    }

    /** Retorna true se o passageiro tem prioridade (idoso ou cadeirante). */
    public boolean isPrioridade() {
        return prioridade;
    }

    /** Retorna true se o passageiro já está embarcado em um elevador. */
    public boolean isEmbarcado() {
        return embarcado;
    }

    /**
     * Define o status de embarque do passageiro.
     *
     * @param embarcado true se o passageiro embarcou, false se desembarcou
     */
    public void setEmbarcado(boolean embarcado) {
        this.embarcado = embarcado;
    }

    /**
     * Retorna uma representação textual do passageiro, incluindo
     * id, idade, se é cadeirante, peso, andar de origem, destino e prioridade.
     */
    @Override
    public String toString() {
        return String.format(
                "Passageiro %d [Idade: %d, Cadeirante: %b, Peso: %dkg, Origem: %d, Destino: %d, Prioridade: %b]",
                id, idade, cadeirante, peso, andarOrigem, andarDestino, prioridade);
    }
}
