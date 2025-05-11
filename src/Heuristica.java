// Enumeração que representa os diferentes modelos de heurística disponíveis
// no sistema de controle de elevadores. Cada modelo pode definir uma lógica
// diferente para otimizar o funcionamento do elevador (ex: tempo, energia, prioridade).

public enum Heuristica {
    // Modelo 1 de heurística - pode representar uma lógica simples baseada em ordem de chegada
    MODELO_1,

    // Modelo 2 de heurística - pode dar prioridade a passageiros com mobilidade reduzida, por exemplo
    MODELO_2,

    // Modelo 3 de heurística - pode ser otimizado para reduzir o consumo de energia
    MODELO_3
}
