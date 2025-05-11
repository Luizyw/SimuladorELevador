public class PainelElevador {
    // Atributos que representam os botões do painel de controle do elevador
    private boolean botaoSubirAtivado;  // Indica se o botão de subir foi pressionado
    private boolean botaoDescerAtivado; // Indica se o botão de descer foi pressionado

    // Construtor da classe, que inicializa os botões como não ativados
    public PainelElevador() {
        this.botaoSubirAtivado = false;  // Inicialmente, o botão de subir não está ativado
        this.botaoDescerAtivado = false; // Inicialmente, o botão de descer não está ativado
    }

    // Método que ativa o botão de subir
    public void pressionarSubir() {
        botaoSubirAtivado = true;  // Quando pressionado, ativa o botão de subir
    }

    // Método que ativa o botão de descer
    public void pressionarDescer() {
        botaoDescerAtivado = true; // Quando pressionado, ativa o botão de descer
    }

    // Método que reseta o painel, desativando ambos os botões
    public void resetar() {
        botaoSubirAtivado = false;   // Desativa o botão de subir
        botaoDescerAtivado = false;  // Desativa o botão de descer
    }

    // Método que verifica se o botão de subir está ativado
    public boolean isBotaoSubirAtivado() {
        return botaoSubirAtivado;  // Retorna o estado do botão de subir (ativado ou não)
    }

    // Método que verifica se o botão de descer está ativado
    public boolean isBotaoDescerAtivado() {
        return botaoDescerAtivado;  // Retorna o estado do botão de descer (ativado ou não)
    }
}
