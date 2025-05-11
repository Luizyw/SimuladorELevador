import java.io.Serializable;

// Classe abstrata base para todas as entidades que participam da simulação.
// Ela define um contrato para que qualquer entidade simulável (como elevadores, andares, etc.)
// implemente o método 'atualizar', que será chamado a cada minuto (ou unidade de tempo) da simulação.
public abstract class EntidadeSimulavel implements Serializable {

    // Método abstrato que deve ser implementado pelas subclasses.
    // Será chamado a cada minuto simulado para atualizar o estado da entidade.
    public abstract void atualizar(int minutoSimulado);
}
