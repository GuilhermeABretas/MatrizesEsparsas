
public class MatrizEsparsaLista {
    private Elo[] linha;
    private int dimensao;

    public MatrizEsparsaLista(int tamanho) {
        this.linha = new Elo[tamanho];
        this.dimensao = tamanho;
    }

    public boolean isForaDoLimite(int i, int j) {
        if (i < 0 || j < 0 || i >= dimensao || j >= dimensao) {
            System.out.println("Fora do limite definido.");
            return true;
        }
        return false;
    }

    // Item 1
    public boolean inserirElemento(int i, int j, int valor) {
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            linha[i] = new Elo(j, valor);
        }
        else{
        return inserirElemento(j, valor, linha[i]);}
        return true;
    }

    private boolean inserirElemento(int j, int valor, Elo elo){

        if (j < elo.coluna){
            Elo newElo = new Elo(j, valor, elo);
            elo = newElo;
            return true;
        }
        else{
            if (elo.prox == null){
                elo.prox = new Elo(j, valor);
                return true;
            }
            return inserirElemento(j, valor, elo.prox);
        }
    }

    public boolean inserirElementoIterativo(int i, int j, int valor){
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            linha[i] = new Elo(j, valor);
        }
        else{
            Elo p;
            for (p = linha[i]; p != null; p = p.prox){
                if (j < p.coluna){
                    Elo newElo = new Elo(j, valor, p);
                    p = newElo;
                    return true;
                }
                if (p.prox == null){
                    p.prox = new Elo(j, valor);
                    return true;
                }
            }
        }
        return true;
    }

    // Item 2
    public boolean removerElemento(int i, int j) {
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            return false;
        }

        return removerElemento(linha[i], j);
    }

    private boolean removerElemento(Elo elo, int j){
        if (j > elo.coluna ){
            return false;
        }
        if (j == elo.coluna){
            elo = elo.prox;
            return true;
        }
        if (elo.prox == null){
            return false;
        }
        else{
            return removerElemento(elo.prox, j);
        }
    }

    public boolean removerElementoIterativo(int i, int j){
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            return false;
        }
        else{
            Elo p;
            for(p = linha[i]; p != null; p = p.prox){
                if (j > p.coluna){
                    return false;
                }
                if (j == p.coluna){
                    p = p.prox;
                    return true;
                }
                if (p.prox == null){
                    return false;
                }
            }
        }return false;
    }

    // Item 3
    public boolean buscarElementoPorValor(int valor) {
        return buscarElementoPorValor(valor, 0);
    }

    // Item 3
    private boolean buscarElementoPorValor(int valor, int i) {
        if (i >= dimensao) {
            return false;
        }

        if (linha[i] == null) {
            return buscarElementoPorValor(valor, i + 1);
        }
        else{
            boolean procuraNaLinha = buscarElementoPorValorAux(valor, linha[i]);
            if (procuraNaLinha){
                return true;
            }
            else{
                return buscarElementoPorValor(valor, i + 1);
            }
        }

    }

    private boolean buscarElementoPorValorAux(int valor, Elo p){
        if (p == null){
            return false;
        }
        if (p.dado == valor){
            return true;
        }
        else{
            return buscarElementoPorValorAux(valor, p.prox);
        }
    }

    public boolean buscarElementoPorValorIterativo(int valor){
        Elo p;
        for (int i = 0; i < dimensao; i++){
            for(p = linha[i]; p != null; p = p.prox){
                if (p.dado == valor){
                    return true;
                }
            }
        }
        return false;
    }

    public int buscarElementoPorCoordenada(int i, int j){
        return buscarElementoPorCoordenada(linha[i], j);
    }

    private int buscarElementoPorCoordenada(Elo p, int j){
        if (p == null){
            return Integer.MIN_VALUE;
        }
        if(p.coluna == j){
            return p.dado;
        }
        return buscarElementoPorCoordenada(p.prox, j);

    }

    // Item 4
    public void printarMatriz() {
        printarMatriz(0);
    }



    private void printarMatriz(int i) {
        if (i >= dimensao) return;
        if (linha[i] == null){
            printarLinhaVazia(0);
        }
        else{
            printarLinhaNaoVazia(linha[i], 0);
        }

        printarMatriz(i + 1);
    }

    private void printarLinhaVazia(int x){
        if (x == dimensao){
            return;
        }
        else{
            System.out.print(" [ 0 ] ");
            printarLinhaVazia(x + 1);
        }
    }

    private void printarLinhaNaoVazia(Elo p, int j){
        if (p == null){
            return;
        }
        if ( j != p.coluna){
            System.out.print(" [ 0 ] ");
            printarLinhaNaoVazia(p, j + 1);
            return;
        }
        else{
            System.out.print(" [" + p.dado + " ]");
            printarLinhaNaoVazia(p.prox, j);
            return;
        }
    }


    public void printarMatrizIterativo(){
        Elo p;
        for(int i = 0; i < dimensao; i++){
            if(linha[i] == null){
                for(i = 0; i < dimensao; i++){
                    System.out.print(" [ 0 ] ");
                }
                System.out.println();
            }
            else{
                int j = 0;
                for(p = linha[j]; p != null; p = p.prox){
                    while(p.coluna != j){
                        System.out.print(" [ 0 ] ");
                        j++;
                    }
                    System.out.print(" [" + p.dado + " ]");

                }
                System.out.println();
            }
        }
        return;
    }

    // Item 6
    public boolean isVazia() {
        return isVazia(0);
    }

    // Item 6
    private boolean isVazia(int i) {
        if (i >= dimensao) return true;
        if (linha[i] == null) {
            return isVazia(i + 1);
        } else {
            return false;
        }
    }

    public boolean isVaziaIterativo(){
        for(int i = 0; i < dimensao; i++){
            if (linha[i] != null ){
                return false;
            }
        }
        return true;
    }

    // Item 7
    public boolean isDiagonal() {
        return isDiagonal(0);
    }

    // Item 7
    private boolean isDiagonal(int i) {
        if (i >= dimensao) return true;

        if(linha[i] == null){
            return isDiagonal(i + 1);
        }
        else{
            return isDiagonalAux(linha[i], i);
        }


    }

    private boolean isDiagonalAux(Elo p, int i){
        if (p == null){
            return true;
        }
        if (i != p.coluna && p.dado != 0){
            return false;
        }
        return isDiagonalAux(p.prox, i);
    }

    public boolean isDiagonalIterativo(){
        Elo p;
        for (int i = 0; i < dimensao; i++){
            if (linha[i] != null){
                for(p = linha[i]; p != null; p = p.prox){
                    if(i != p.coluna && p.dado != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Item 8
    public boolean isLinha() {
        return isLinha(0, false);
    }

    // Item 8
    private boolean isLinha(int i, boolean achouNumLinha) {
        if (i >= dimensao) {
            return true;
        }

        if (linha[i] != null && achouNumLinha) {
            return false;
        }
        else if(linha[i] != null && !achouNumLinha) {
            achouNumLinha = true;
        }

        return isLinha(i + 1, achouNumLinha);
    }

    public boolean isLinhaIterativo(){
        boolean achouNumLinha = false;
        for (int i = 0; i < dimensao; i++){
            if (achouNumLinha == true && linha[i] != null){
                return false;
            }
            if (linha[i] != null){
                achouNumLinha = true;
            }

        }
        return true;
    }

    // Item 9


    public boolean isColuna(){
        return isColuna(0,-1);
    }

    private boolean isColuna(int i, int colunaEncontrada){
        if (i == dimensao){
            return true;
        }
        if (linha[i] == null){
            return isColuna(i + 1, colunaEncontrada);
        }
        if (colunaEncontrada == -1){
            colunaEncontrada = linha[i].coluna;
            boolean linhaAnalisada = isColunaAux(linha[i].prox, colunaEncontrada);
            if (linhaAnalisada == false){
                return false;
            }
        }
        else{
            boolean linhaAnalisada = isColunaAux(linha[i], colunaEncontrada);
            if (linhaAnalisada == false){
                return false;
            }
        }

        return isColuna(i + 1, colunaEncontrada);

    }

    private boolean isColunaAux(Elo p, int colunaEncontrada){
        if(p == null){
            return true;
        }
        if (p.coluna != colunaEncontrada){
            return false;
        }
        return isColunaAux(p.prox, colunaEncontrada);
    }

    public boolean isColunaIterativo(){
        int colunaEncontrada = -1;
        Elo p;
        for(int i = 0; i < dimensao; i++){
            if (linha[i] != null){
                if (colunaEncontrada == -1){
                    colunaEncontrada = linha[i].coluna;
                }
                else{
                    for(p = linha[i]; p != null; p = p.prox){
                        if(p.coluna != colunaEncontrada){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isTriangularInferior(){
        return isTriangularInferior(0);
    }

    private boolean isTriangularInferior(int i){
        if (i == (dimensao - 1)){
            return true;
        }
        if (linha[i] == null){
            return isTriangularInferior(i + 1);
        }
        else{
            boolean linhaAnalisada = isTriangularInferiorAux(linha[i], i);
            if (linhaAnalisada == false){
                return false;
            }
            return isTriangularInferior(i + 1);
        }
    }

    private boolean isTriangularInferiorAux(Elo p, int i){
        if(p == null){
            return true;
        }
        if(p.coluna > i && p.dado != 0){
            return false;
        }
        return isTriangularInferiorAux(p.prox, i);
    }

    public boolean isTriangularInferiorIterativo(){
        Elo p;
        for(int i = 0; i < (dimensao - 1); i++){
            if (linha[i] != null){
                for(p = linha[i]; p != null; p = p.prox){
                    if (p.coluna > i && p.dado != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isTriangularSuperior(){
        return isTriangularSuperior(1);
    }

    private boolean isTriangularSuperior(int i){
        if (i == dimensao){
            return true;
        }
        if (linha[i] == null){
            return isTriangularSuperior(i + 1);
        }
        else{
            boolean linhaAnalisada = isTriangularSuperiorAux(linha[i], i);
            if (linhaAnalisada == false){
                return false;
            }
            return isTriangularSuperior(i + 1);
        }
    }

    private boolean isTriangularSuperiorAux(Elo p, int i){
        if (p == null){
            return true;
        }
        if (i > p.coluna && p.dado != 0){
            return false;
        }
        return isTriangularSuperiorAux(p.prox, i);
    }

    public boolean isTriangularSuperiorIterativo(){
        Elo p;
        for (int i = 1; i < dimensao; i++){
            if (linha[i] != null){
                for (p = linha[i]; p != null; p = p.prox){
                    if (i > p.coluna && p.dado != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSimetrica(){
        return isSimetrica(0);
    }

    private boolean isSimetrica(int i){
        if (i == dimensao){
            return true;
        }
        if (linha[i] == null){
            return isSimetrica(i + 1);
        }
        else{
            boolean linhaAnalisada = isSimetricaAux(linha[i], i);
            if (linhaAnalisada == false){
                return false;
            }
            return isSimetrica(i + 1);
        }
    }

    private boolean isSimetricaAux(Elo p, int i){
        if (p == null){
            return true;
        }
        if (p.dado != buscarElementoPorCoordenada(p.coluna, i)){
            return false;
        }
        return isSimetricaAux(p.prox, i);
    }

    public boolean isSimetricaIterativo(){
        Elo p;
        for (int i = 0; i < dimensao; i++){
            if (linha[i] != null){
                for(p = linha[i]; p != null; p = p.prox){
                    if(p.dado != buscarElementoPorCoordenada(p.coluna, i)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static MatrizEsparsaLista somarMatrizesIterativa(MatrizEsparsaLista m1, MatrizEsparsaLista m2){
        if (m1.dimensao != m2.dimensao){
            return null;
        }
        Elo p;
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(m1.dimensao);
        for (int i = 0; i < resultado.dimensao; i++){
            if(m1.linha[i] != null){
                for (p = m1.linha[i]; p != null; p = p.prox;){
                    resultado.inserirElemento(i, p.coluna, p.dado + m2.buscarElementoPorCoordenada(i, p.coluna));
                }

            }
        }
    }


}
