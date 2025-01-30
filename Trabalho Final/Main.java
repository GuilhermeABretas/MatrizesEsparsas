import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long nanosTotais = 0;
        int repeticoes = 100;
        int dimensao = 10000;
        //MatrizEsparsaEstatica matriz1 = MatrizEsparsaEstatica.criarMatrizAleatoriaIterativo(dimensao);
        MatrizEsparsaLista matriz1 = MatrizEsparsaLista.criarMatrizAleatoriaIterativo(dimensao);
        for (int i = repeticoes; i > 0; i--) {

            Random random = new Random();
            int x = random.nextInt(dimensao);
            random = new Random();
            int y = random.nextInt(dimensao);

            long inicio = System.nanoTime();

            matriz1.buscarElementoPorCoordenada(x, y);

            long fim = System.nanoTime();

            long tempoExecucao = (fim - inicio);
            System.out.println("Tempo de execução: " + tempoExecucao + "ns");

            nanosTotais += tempoExecucao;
        }

        double tempoMedioDeExecucao = nanosTotais / repeticoes;
        System.out.println("Tempo médio de execução após " + repeticoes + " repetições: " + String.format("%d", (long) tempoMedioDeExecucao) + "ns");
    }
}