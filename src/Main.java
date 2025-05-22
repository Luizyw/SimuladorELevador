
public class Main {
    public static void main(String[] args) {
        SistemaElevadores sistema = new SistemaElevadores(2, 10, 800);

        // Adicionar passageiros de exemplo (id, idade, cadeirante, peso, andar origem, andar destino)
        sistema.adicionarPassageiroNaFila(new Passageiro(1, 70, false, 70, 0, 5)); // idoso
        sistema.adicionarPassageiroNaFila(new Passageiro(2, 30, true, 80, 0, 7));  // cadeirante
        sistema.adicionarPassageiroNaFila(new Passageiro(3, 40, false, 90, 3, 8)); // normal
        sistema.adicionarPassageiroNaFila(new Passageiro(4, 65, false, 75, 2, 9)); // idoso
        sistema.adicionarPassageiroNaFila(new Passageiro(5, 25, false, 60, 0, 10));// normal
        sistema.adicionarPassageiroNaFila(new Passageiro(6, 55, false, 85, 1, 4)); // normal
        sistema.adicionarPassageiroNaFila(new Passageiro(7, 80, false, 65, 0, 6)); // idoso
        sistema.adicionarPassageiroNaFila(new Passageiro(8, 35, true, 90, 4, 0));  // cadeirante

        System.out.println("Estado inicial da fila:");
        sistema.imprimirFila();

        // Loop até simulação terminar
        while (!sistema.simFinalizada()) {
            sistema.operarUmCiclo();
        }

        sistema.imprimirResumo();
    }
}
