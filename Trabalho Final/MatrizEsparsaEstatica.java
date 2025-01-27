import java.util.Random;

public class MatrizEsparsaEstatica {
    private int[][] matriz;
    private int dimensao;

    public MatrizEsparsaEstatica(int tamanho) {
        matriz = new int[tamanho][tamanho];
        this.dimensao = tamanho;
    }

    public boolean isForaDoLimite(int x, int y) {
        if (x < 0 || y < 0 || x >= dimensao || y >= dimensao) {
            System.out.println("Fora do limite definido.");
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

    public boolean buscarElementoPorValor(int valor) {

        return buscarElementoPorValor(valor, 0, 0);
    }

    // Item 3
    private boolean buscarElementoPorValor(int valor, int x, int y) {
        if (x >= dimensao) {
            return false;
        }
        if (y < dimensao && matriz[x][y] == valor) {
            return true;
        }
        if (y == dimensao) {
            return buscarElementoPorValor(valor, x + 1, 0);
        }
        return buscarElementoPorValor(valor, x, y + 1);
    }

    public boolean buscarElementoPorValorIterativo(int valor){
        for (int i = 0; i < dimensao; i++){
            for (int j = 0; j < dimensao ; j++){
                if (this.matriz[i][j] == valor){
                    return true;
                }
            }
        }
        return false;
    }

    public int buscarElementoPorCoordenada(int x, int y){
        return this.matriz[x][y];
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
        System.out.printf(" [" + "%2d" + "] ", matriz[x][y]);
        if (y >= dimensao - 1) {
            System.out.println();
            printarMatriz(x + 1, 0);
            return;
        }

        printarMatriz(x, y + 1);

    }

    public void printarIterativo(){
        for (int i = 0; i < dimensao; i++){
            for (int j = 0; j < dimensao; j++){
                System.out.printf(" [" + "%2d" + "] ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static void representarMatrizVazia(int dimensao){
        MatrizEsparsaEstatica vazia = new MatrizEsparsaEstatica(dimensao);
        vazia.printarIterativo();
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

    public boolean isVaziaIterativo(){
        for (int i = 0; i < dimensao; i++){
            for (int j = 0; j < dimensao; j++){
                if (this.matriz[i][j] != 0){
                    return false;}
            }
            }
        return true;
    }

    // Item 7
    public boolean isDiagonal() {
        return isDiagonal(0, 0);
    }

    // Item 7
    private boolean isDiagonal(int x, int y) {

        if (x == dimensao) {
            return true;
        }
        if (x != y && matriz[x][y] != 0) {
            return false;
        }
        if (y == dimensao) {
            return isDiagonal(x + 1, 0);
        }

        return isDiagonal(x, y + 1);
    }

    public boolean isDiagonalIterativo(){
        for (int i = 0; i < dimensao; i++){
            for (int j = 0; j < dimensao; j++) {
                if (i != j && matriz[i][j] != 0) {
                    return false;}
            }}return true;
    }


    // Item 8
    public boolean isLinha() {
        return isLinha(0, 0, false);
    }

    // Item 8
    private boolean isLinha(int x, int y, boolean achouNumLinha) {
        if (x == dimensao) {
            return true;
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

    public boolean isLinhaIterativo(){
        boolean achouNumLinha = false;
        for (int i = 0; i < dimensao; i++){

            for (int j = 0; j < dimensao; j++) {
                if (achouNumLinha == false && matriz[i][j] != 0) {
                    achouNumLinha = true;
                    j = dimensao;}
                if (achouNumLinha == true && matriz[i][j] != 0){
                    return false;
                }
            }}
        return true;
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

    public boolean isColunaIterativo(){
        boolean achouNumColuna = false;
        for (int j = 0; j < dimensao; j++){

            for (int i = 0; i < dimensao; i++) {
                if (achouNumColuna == false && matriz[i][j] != 0) {
                    achouNumColuna = true;
                    i = dimensao;}
                if (achouNumColuna == true && matriz[i][j] != 0){
                    return false;
                }
            }}
        return true;
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

    public boolean isTriangularInferiorIterativo(){
        for (int i = 0; i < dimensao; i++){

            for (int j = i + 1; j < dimensao; j++) {
                if(this.matriz[i][j] != 0){
                    return false;
                }
            }}
        return true;
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

    public boolean isTriangularSuperiorIterativo(){
        for (int i = 1; i < dimensao; i++){

            for (int j = 0; j < i; j++) {
                if(this.matriz[i][j] != 0){
                    return false;
                }
            }}
        return true;
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

    public boolean isSimetricaIterativo(){
        for (int i = 0; i < dimensao; i++){

            for (int j = 0; j < dimensao; j++) {
                if(this.matriz[i][j] !=this.matriz[j][i]){
                    return false;
                }
            }}
        return true;
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

    public static MatrizEsparsaEstatica somarMatrizesIterativo(MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2){
        if (m1.dimensao != m2.dimensao) {
            return null;
        }
        MatrizEsparsaEstatica resultado = new MatrizEsparsaEstatica(m1.dimensao);

        for (int i = 0; i < resultado.dimensao; i++){

            for (int j = 0; j < resultado.dimensao; j++) {
                resultado.matriz[i][j] = (m1.matriz[i][j] + m2.matriz[i][j]);
                }
            }

        return resultado;
    }

    public static MatrizEsparsaEstatica multiplicarMatrizes(MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2){
        if (m1.dimensao != m2.dimensao) {
            return null;
        }
        MatrizEsparsaEstatica resultado = new MatrizEsparsaEstatica(m1.dimensao);
        multiplicarRecursiva(0, 0, m1, m2, resultado);
        return resultado;
    }

    private static void multiplicarRecursiva(int x, int y, MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2, MatrizEsparsaEstatica resultado){
        if (x == m1.dimensao) {
            return;
        }
        if (y == m1.dimensao) {
            multiplicarRecursiva(x + 1, 0, m1, m2, resultado);
            return;}
        int valor = resultado.multiplicarRecursivoAux(x, y, 0, 0, m1, m2);

        resultado.matriz[x][y] = valor;

        multiplicarRecursiva(x, y + 1, m1, m2, resultado);
    }

    private int multiplicarRecursivoAux(int x, int y, int k, int valorAtual, MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2){
        if (k == this.dimensao){
            return valorAtual;
        }
        valorAtual += m1.matriz[x][k] * m2.matriz[k][y];
        return multiplicarRecursivoAux(x, y, k, valorAtual, m1, m2);
    }

    public static MatrizEsparsaEstatica  multiplicarMatrizesIterativo(MatrizEsparsaEstatica m1, MatrizEsparsaEstatica m2){
        if (m1.dimensao != m2.dimensao) {
            return null;
        }
        MatrizEsparsaEstatica resultado = new MatrizEsparsaEstatica(m1.dimensao);
        for (int i = 0; i < resultado.dimensao; i++){

            for (int j = 0; j < resultado.dimensao; j++) {
                resultado.matriz[i][j] = 0;
                for (int k = 0; k < m1.dimensao; k++){
                    resultado.matriz[i][j] += (m1.matriz[i][k] * m2.matriz[k][j]);
                }
            }
        }
        return resultado;
    }

    public MatrizEsparsaEstatica obterMatrizTransposta(){
        MatrizEsparsaEstatica transposta = new MatrizEsparsaEstatica(this.dimensao);
        obterMatrizTransposta(0, 0, transposta);
        return transposta;
    }

    private void obterMatrizTransposta(int x, int y, MatrizEsparsaEstatica transposta){
        if (x == this.dimensao) {
            return;
        }
        if (y == this.dimensao) {
            obterMatrizTransposta(x + 1, 0, transposta);
            return;
        }
        transposta.matriz[y][x] = this.matriz[x][y];

        obterMatrizTransposta(x, y + 1, transposta);
        return;
    }

    public MatrizEsparsaEstatica obterMatrizTranspostaIterativo(){
        MatrizEsparsaEstatica transposta = new MatrizEsparsaEstatica(this.dimensao);
        for (int i = 0; i < transposta.dimensao; i++){

            for (int j = 0; j < transposta.dimensao; j++) {
                transposta.matriz[j][i] = this.matriz[i][j];
            }
        }
        return transposta;
    }

    public static MatrizEsparsaEstatica criarMatrizAleatoria(int dimensao){
        MatrizEsparsaEstatica novaMatriz = new MatrizEsparsaEstatica(dimensao);

        int lotacao = (int) ((novaMatriz.dimensao * novaMatriz.dimensao) * 0.4) ;



        novaMatriz.preencherMatrizAleatoria(0,0,lotacao);
        return novaMatriz;
    }
    private void preencherMatrizAleatoria(int x, int y, int lotacao){

        Random random = new Random();
        do{
            x = random.nextInt(this.dimensao);
            y = random.nextInt(this.dimensao);
        }
        while (this.matriz[x][y] != 0);
        this.matriz[x][y] = random.nextInt(100);
        lotacao--;
        if (lotacao <= 0){
            return;
        }
        this.preencherMatrizAleatoria(x, y, lotacao);

    }

    public static MatrizEsparsaEstatica criarMatrizAleatoriaIterativo(int dimensao){
        MatrizEsparsaEstatica novaMatriz = new MatrizEsparsaEstatica(dimensao);
        int vagas = (int) (dimensao * dimensao * 0.4);
        Random random = new Random();
        int x = 0;
        int y = 0;
        for(int i = 0; i < vagas; i++){
            do{
                x = random.nextInt(novaMatriz.dimensao);
                y = random.nextInt(novaMatriz.dimensao);
            }
            while (novaMatriz.matriz[x][y] != 0);
            novaMatriz.matriz[x][y] = random.nextInt(100);
        }return novaMatriz;
    }
}