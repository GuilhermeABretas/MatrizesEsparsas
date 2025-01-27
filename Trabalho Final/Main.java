//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] array = new int[5];
        System.out.println(array[4]);


        MatrizEsparsaEstatica matriz1 = new MatrizEsparsaEstatica(10);



        MatrizEsparsaEstatica matriz2 = new MatrizEsparsaEstatica(10);
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
    }
}