public class Main {
    public static void main(String[] args) {
        long nanosTotais = 0;
        int repeticoes = 1600000000;
        int count = 0;
        MatrizEsparsaEstatica matriz1 = MatrizEsparsaEstatica.criarMatrizAleatoriaIterativo(20000);
        int x = 0, y = 0;
        for (int i = repeticoes; i > 0; i--) {
            if (40000 / (y + 1) == 1) {
                y = 0;
                x++;
            }
            long inicio = System.nanoTime();

            matriz1.inserirElemento(x, y, 10);

            long fim = System.nanoTime();

            long tempoExecucao = (fim - inicio);
            System.out.println("Tempo de execução: " + tempoExecucao + "ns");

            nanosTotais += tempoExecucao;
            y++;
            count++;
        }

        double tempoMedioDeExecucao = nanosTotais / repeticoes;
        System.out.println("Tempo médio de execução após " + repeticoes + " repetições: " + tempoMedioDeExecucao + "ns");
        System.out.println(count);

        /*
        long nanosTotais = 0;
        int repeticoes = 1000;
        for (int i = repeticoes; i > 0; i--) {
            long inicio = System.nanoTime();

            matriz1.inserirElemento(5, 5, 45);

            long fim = System.nanoTime();


            long tempoExecucao = (fim - inicio);
            System.out.println("Tempo de execução: " + tempoExecucao + "ns");

            nanosTotais += tempoExecucao;
        }

        double tempoMedioDeExecucao = nanosTotais / repeticoes;
        System.out.println("Tempo médio de execução após " + repeticoes + " repetições: " + tempoMedioDeExecucao + "ns");


        /*MatrizEsparsaEstatica matriz2 = new MatrizEsparsaEstatica(10);
        matriz2.printarMatriz();
        System.out.println(matriz2.isSimetrica());
        matriz2.inserirElemento(0,1, 2);

        MatrizEsparsaEstatica m1 = new MatrizEsparsaEstatica(3);
        MatrizEsparsaEstatica m2 = new MatrizEsparsaEstatica(3);

        // Preenche m1
        m1.inserirElemento(0, 0, 1);
        m1.inserirElemento(1, 0, 2);
        m1.inserirElemento(2, 0, 3);

        // Preenche m2
        m2.inserirElemento(0, 0, 5);
        m2.inserirElemento(1, 1, 10);


        MatrizEsparsaEstatica resultado = MatrizEsparsaEstatica.multiplicarMatrizes(m1, m2);
        resultado.printarMatriz();

        MatrizEsparsaEstatica trans = m1.obterMatrizTransposta();

        m1.printarMatriz();
        trans.printarMatriz();

        MatrizEsparsaEstatica.representarMatrizVazia(10);

        MatrizEsparsaEstatica m3 = MatrizEsparsaEstatica.criarMatrizAleatoriaIterativo(30000);
        m3.printarIterativo();

        for (int i = 0; i > 10; i++){}


         */
    }
}