public class TestesMatriz {
    public static void main(String[] args) {
        MatrizEsparsaLista m1 = new MatrizEsparsaLista(4);
        MatrizEsparsaLista m2 = new MatrizEsparsaLista(4);
        /*
        m1.inserirElemento(0, 0, 4);
        m1.inserirElemento(0, 3, 7);
        m1.inserirElemento(1, 1, 3);
        m1.inserirElemento(1, 2, 14);
        m1.inserirElemento(1, 3, 6);
        m1.inserirElemento(2, 2, 3);
        m1.inserirElemento(2, 3, 7);
        m1.inserirElemento(3, 0, 32);
        m1.inserirElemento(3, 1, 1);
        m1.inserirElemento(3, 2, 6);
        m1.inserirElemento(3, 3, 4);

        m2.inserirElemento(0, 0, 12);
        m2.inserirElemento(0, 2, 1);
        m2.inserirElemento(1, 1, 12);
        m2.inserirElemento(1, 2, 1);
        m2.inserirElemento(2, 2, 12);
        m2.inserirElemento(3, 3, 1);

        m1.printarMatriz();
        System.out.println();
        m2.printarMatriz();

         */

        System.out.println();
        MatrizEsparsaLista m3 = MatrizEsparsaLista.criarMatrizAleatoriaIterativo(2000);
        m3.printarMatriz();

    }
}
