public class MatrizEsparsaEstatica {
    private int[][] matriz;
    private int dimensao;

    public MatrizEsparsaEstatica(int tamanho) {
        matriz = new int[tamanho][tamanho];
        this.dimensao = tamanho;
    }

    public boolean isForaDoLimite(int x, int y) {
        if (x < 0 || y < 0 || x >= dimensao || y >= dimensao) {
            System.out.println("out of bounds");
            return true;
        }
        return false;
    }

    // Item 1
    public boolean inserirElemento(int x, int y, int valor) {
        if (isForaDoLimite(x, y)) {
            return false;
        }
        matriz[x][y] = valor;
        return true;
    }

    // Item 2
    public boolean removerElemento(int x, int y) {
        if (isForaDoLimite(x, y)) {
            return false;
        }
        matriz[x][y] = 0;
        return true;
    }

    // Item 3
    public boolean buscarElemento(int valor) {

        return buscarElemento(valor, 0, 0);
    }

    // Item 3
    private boolean buscarElemento(int valor, int x, int y) {
        if (x >= dimensao) {
            return false;
        }
        if (y < dimensao && matriz[x][y] == valor) {
            return true;
        }
        if (y == dimensao) {
            return buscarElemento(valor, x + 1, 0);
        }
        return buscarElemento(valor, x, y + 1);
    }

    // Item 4
    public void printarMatriz() {
        printarMatriz(0, 0);
    }


    // Item 4
    private void printarMatriz(int x, int y) {
        if (x == dimensao) {
            return;
        }
        System.out.print(matriz[x][y]);
        if (y >= dimensao - 1) {
            System.out.println();
            printarMatriz(x + 1, 0);
            return;
        }

        printarMatriz(x, y + 1);

    }

    // Item 6
    public boolean isVazia() {

        return isVazia(0, 0);
    }

    // Item 6
    private boolean isVazia(int x, int y) {
        if (x == dimensao) {
            return true;
        }
        if (matriz[x][y] != 0) {
            return false;
        }

        if (y == dimensao) {
            return isVazia(x + 1, 0);
        }

        return isVazia(x, y + 1);
    }

    // Item 7
    public boolean isDiagonal() {
        return isDiagonal(0, 0, false);
    }

    // Item 7
    private boolean isDiagonal(int x, int y, boolean achouNumDiagonal) {
        // 1° caso: Se x != y, verificar se valor é diferente de 0. Se sim, retorna falso.
        // 2° caso: Se x == y, verificar se valor é diferente de 0. Se sim, continua.
        if (x == dimensao) {
            return achouNumDiagonal;
        }
        if (x != y && matriz[x][y] != 0) {
            return false;
        }
        if (achouNumDiagonal == false && x == y && matriz[x][y] != 0) {
            achouNumDiagonal = true;
        }
        if (y == dimensao) {
            return isDiagonal(x + 1, 0, achouNumDiagonal);
        }

        return isDiagonal(x, y + 1, achouNumDiagonal);
    }

    // Item 8
    public boolean isLinha() {
        return isLinha(0, 0, false);
    }

    // Item 8
    private boolean isLinha(int x, int y, boolean achouNumLinha) {
        if (x == dimensao) {
            return achouNumLinha;
        }
        if (achouNumLinha == true && matriz[x][y] != 0) {
            return false;
        }
        if (matriz[x][y] != 0) {
            achouNumLinha = true;
            return isLinha(x + 1, 0, achouNumLinha);
        }
        if (y == dimensao) {
            return isLinha(x + 1, 0, achouNumLinha);
        }

        return isLinha(x, y + 1, achouNumLinha);
    }

    // Item 9
    public boolean isColuna() {
        return isColuna(0, 0, false);
    }

    // Item 9
    private boolean isColuna(int x, int y, boolean achouNumColuna) {
        if (y == dimensao) {
            return achouNumColuna;
        }
        if (achouNumColuna == true && matriz[x][y] != 0) {
            return false;
        }
        if (matriz[x][y] != 0) {
            achouNumColuna = true;
            return isLinha(0, y + 1, achouNumColuna);
        }
        if (x == dimensao) {
            return isLinha(0, y + 1, achouNumColuna);
        }

        return isLinha(x + 1, y, achouNumColuna);
    }

    public boolean isTriangularInferior() {

        return isTriangularInferior(0, 1, 1);
    }

    private boolean isTriangularInferior(int x, int y, int aux) {
        if (x == dimensao - 1) {
            return true;
        }
        if (x < y && matriz[x][y] != 0 && y < dimensao) {
            return false;
        }
        if (y == dimensao) {
            return isTriangularInferior(x + 1, ++aux, aux);
        }

        return isTriangularInferior(x, y + 1, aux);

    }

    public boolean isTriangularSuperior() {

        return isTriangularSuperior(1, 0);
    }

    private boolean isTriangularSuperior(int x, int y) {
        if (x == dimensao) {
            return true;
        }
        if (x > y && matriz[x][y] != 0) {
            return false;
        }
        if (x == y) {
            return isTriangularSuperior(x + 1, 0);
        }

        return isTriangularSuperior(x, y + 1);

    }

    public boolean isSimetrica() {
        return isSimetrica(0, 0);
    }

    private boolean isSimetrica(int x, int y) {
        if (x == dimensao) {
            return true;
        }
        if (y < dimensao && matriz[x][y] != matriz[y][x]) {
            return false;
        }
        if (y == dimensao) {
            return isSimetrica(x + 1, 0);
        }

        return isSimetrica(x, y + 1);
    }

    public static MatrizEsparsaEstatica somarMatrizes(MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2) {
        if (m1.dimensao != m2.dimensao) {
            return null;
        }
        MatrizEsparsaEstatica resultado = new MatrizEsparsaEstatica(m1.dimensao);

        somaRecursiva(0, 0, m1, m2, resultado);
        return resultado;

    }

    private static void somaRecursiva(int x, int y, MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2, MatrizEsparsaEstatica resultado) {
        if (x == m1.dimensao) {
            return;
        }
        if (y == m1.dimensao) {
            somaRecursiva(x + 1, 0, m1, m2, resultado);
        } else {
            resultado.matriz[x][y] = m1.matriz[x][y] + m2.matriz[x][y];
            somaRecursiva(x, y + 1, m1, m2, resultado);
        }

    }
}