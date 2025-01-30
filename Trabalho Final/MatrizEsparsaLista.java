import java.util.Random;

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
            return true;
        }
        linha[i] = inserirElemento(j, valor, linha[i]);
        return true;
    }


    // Item 1
    private Elo inserirElemento(int j, int valor, Elo elo) {
        if (j < elo.coluna) {
            return new Elo(j, valor, elo);
        } else if (j == elo.coluna) {
            elo.dado = valor;
            return elo;
        }
        else if (elo.prox == null) {
            elo.prox = new Elo(j, valor);
            return elo;
        }
        elo.prox = inserirElemento(j, valor, elo.prox);
        return elo;
    }

    public boolean inserirElementoIterativo(int i, int j, int valor) {
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            linha[i] = new Elo(j, valor);
            return true;
        }

        Elo atual = linha[i];
        Elo anterior = null;
        while (atual != null && atual.coluna < j) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null && atual.coluna == j) {
            atual.dado = valor;
        } else {
            Elo novo = new Elo(j, valor, atual);
            if (anterior == null) {
                linha[i] = novo;
            } else {
                anterior.prox = novo;
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

    // Item
    private boolean removerElemento(Elo elo, int j) {
        if (j > elo.coluna ) {
            return false;
        }
        if (j == elo.coluna) {
            elo = elo.prox;
            return true;
        }
        if (elo.prox == null) {
            return false;
        }
        else{
            return removerElemento(elo.prox, j);
        }
    }

    public boolean removerElementoIterativo(int i, int j) {
        if (isForaDoLimite(i, j)) {
            return false;
        }
        if (linha[i] == null) {
            return false;
        }
        else {
            Elo p;
            for(p = linha[i]; p != null; p = p.prox) {
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
        }
        return false;
    }

    // Item 3
    public boolean buscarElementoPorValor(int valor) {
        return buscarElementoPorValor(valor, 0);
    }

    // Item 3
    private boolean buscarElementoPorValor(int valor, int i) {
        if (i >= dimensao) return false;
        if (linha[i] == null) {
            return buscarElementoPorValor(valor, i + 1);}
        else{
            boolean procuraNaLinha = buscarElementoPorValorAux(valor, linha[i]);
            if (procuraNaLinha) return true;
            else return buscarElementoPorValor(valor, i + 1);
        }
    }

    // Item 3
    private boolean buscarElementoPorValorAux(int valor, Elo p) {
        if (p == null) return false;
        if (p.dado == valor) return true;
        else return buscarElementoPorValorAux(valor, p.prox);
    }

    public boolean buscarElementoPorValorIterativo(int valor) {
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

    // Item 3
    public int buscarElementoPorCoordenada(int i, int j){
        return buscarElementoPorCoordenada(linha[i], j);
    }

    // Item 3
    private int buscarElementoPorCoordenada(Elo p, int j){
        if (p == null){
            return 0;
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

    // Item 4
    private void printarMatriz(int i) {
        if (i >= dimensao) return;
        if (linha[i] == null) printarLinhaVazia(0);
        else {
            printarLinhaNaoVazia(linha[i], 0);
        }
        System.out.println();
        printarMatriz(i + 1);
    }

    // Item 4;
    private void printarLinhaVazia(int x) {
        if (x == dimensao) return;
        else{
            System.out.print(" [ 0 ] ");
            printarLinhaVazia(x + 1);
        }
    }

    // Item 4
    private void printarLinhaNaoVazia(Elo p, int j) {
        if (p == null) {
            if (j < (dimensao)) {
                System.out.print(" [ 0 ] ");
                printarLinhaNaoVazia(p, j + 1);
            }
            return;
        }
        if ( j != p.coluna) {
            System.out.print(" [ 0 ] ");
            printarLinhaNaoVazia(p, j + 1);
        }
        else{
            System.out.printf(" [" + "%2d" + " ] ", p.dado);
            printarLinhaNaoVazia(p.prox, j + 1);
        }
        return;
    }

    public void printarMatrizIterativo() {
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

    // Item 5
    public static void representarMatrizVazia(int dimensao){
        MatrizEsparsaLista vazia = new MatrizEsparsaLista(dimensao);
        vazia.printarMatriz();
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

    public boolean isVaziaIterativo() {
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
        if(linha[i] == null) {
            return isDiagonal(i + 1);
        }
        else{
            return isDiagonalAux(linha[i], i);
        }
    }

    // Item 7
    private boolean isDiagonalAux(Elo p, int i) {
        if (p == null) {
            return true;
        }
        if (i != p.coluna && p.dado != 0) {
            return false;
        }
        return isDiagonalAux(p.prox, i);
    }

    public boolean isDiagonalIterativo() {
        Elo p;
        for (int i = 0; i < dimensao; i++) {
            if (linha[i] != null){
                for(p = linha[i]; p != null; p = p.prox) {
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
        for (int i = 0; i < dimensao; i++) {
            if (achouNumLinha == true && linha[i] != null) {
                return false;
            }
            if (linha[i] != null) {
                achouNumLinha = true;
            }

        }
        return true;
    }

    // Item 9
    public boolean isColuna() {
        return isColuna(0,-1);
    }

    // Item 9
    private boolean isColuna(int i, int colunaEncontrada) {
        if (i == dimensao) return true;
        if (linha[i] == null) return isColuna(i + 1, colunaEncontrada);
        if (colunaEncontrada == -1) {
            colunaEncontrada = linha[i].coluna;
            boolean linhaAnalisada = isColunaAux(linha[i].prox, colunaEncontrada);
            if (linhaAnalisada == false) return false;
        }
        else {
            boolean linhaAnalisada = isColunaAux(linha[i], colunaEncontrada);
            if (linhaAnalisada == false) return false;
        }

        return isColuna(i + 1, colunaEncontrada);
    }

    // Item 9
    private boolean isColunaAux(Elo p, int colunaEncontrada) {
        if(p == null) {
            return true;
        }
        if (p.coluna != colunaEncontrada) {
            return false;
        }
        return isColunaAux(p.prox, colunaEncontrada);
    }

    public boolean isColunaIterativo() {
        int colunaEncontrada = -1;
        Elo p;
        for(int i = 0; i < dimensao; i++) {
            if (linha[i] != null){
                if (colunaEncontrada == -1) {
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

    // Item 10
    public boolean isTriangularInferior() {
        return isTriangularInferior(0);
    }

    // Item 10
    private boolean isTriangularInferior(int i) {
        if (i == (dimensao - 1)) {
            return true;
        }
        if (linha[i] == null) {
            return isTriangularInferior(i + 1);
        }
        else {
            boolean linhaAnalisada = isTriangularInferiorAux(linha[i], i);
            if (linhaAnalisada == false) {
                return false;
            }
            return isTriangularInferior(i + 1);
        }
    }

    // Item 10
    private boolean isTriangularInferiorAux(Elo p, int i) {
        if(p == null) {
            return true;
        }
        if(p.coluna > i && p.dado != 0) {
            return false;
        }
        return isTriangularInferiorAux(p.prox, i);
    }

    public boolean isTriangularInferiorIterativo() {
        Elo p;
        for(int i = 0; i < (dimensao - 1); i++) {
            if (linha[i] != null) {
                for(p = linha[i]; p != null; p = p.prox) {
                    if (p.coluna > i && p.dado != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Item 11
    public boolean isTriangularSuperior() {
        return isTriangularSuperior(1);
    }

    // Item 11
    private boolean isTriangularSuperior(int i) {
        if (i == dimensao){
            return true;
        }
        if (linha[i] == null) {
            return isTriangularSuperior(i + 1);
        }
        else {
            boolean linhaAnalisada = isTriangularSuperiorAux(linha[i], i);
            if (linhaAnalisada == false) {
                return false;
            }
            return isTriangularSuperior(i + 1);
        }
    }

    // Item 11
    private boolean isTriangularSuperiorAux(Elo p, int i) {
        if (p == null) {
            return true;
        }
        if (i > p.coluna && p.dado != 0){
            return false;
        }
        return isTriangularSuperiorAux(p.prox, i);
    }

    public boolean isTriangularSuperiorIterativo() {
        Elo p;
        for (int i = 1; i < dimensao; i++) {
            if (linha[i] != null){
                for (p = linha[i]; p != null; p = p.prox) {
                    if (i > p.coluna && p.dado != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Item 12
    public boolean isSimetrica() {
        return isSimetrica(0);
    }

    // Item 12
    private boolean isSimetrica(int i) {
        if (i == dimensao) {
            return true;
        }
        if (linha[i] == null) {
            return isSimetrica(i + 1);
        } else {
            boolean linhaAnalisada = isSimetricaAux(linha[i], i);
            if (linhaAnalisada == false) {
                return false;
            }
            return isSimetrica(i + 1);
        }
    }

    // Item 12
    private boolean isSimetricaAux(Elo p, int i) {
        if (p == null) {
            return true;
        }
        if (p.dado != buscarElementoPorCoordenada(p.coluna, i)) {
            return false;
        }
        return isSimetricaAux(p.prox, i);
    }

    public boolean isSimetricaIterativo() {
        Elo p;
        for (int i = 0; i < dimensao; i++) {
            if (linha[i] != null){
                for(p = linha[i]; p != null; p = p.prox) {
                    if(p.dado != buscarElementoPorCoordenada(p.coluna, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Item 13
    public static MatrizEsparsaLista somarMatrizes(MatrizEsparsaLista m1, MatrizEsparsaLista m2) {
        if ((m1 == null || m2 == null) || (m1.dimensao != m2.dimensao)) {
            return null;
        }
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(m1.dimensao);
        somarMatrizesAux(m1, m2, resultado, 0);
        return resultado;
    }

    // Item 13
    private static void somarMatrizesAux(MatrizEsparsaLista m1, MatrizEsparsaLista m2, MatrizEsparsaLista resultado, int linhaAtual) {
        if (linhaAtual >= m1.dimensao) {
            return;
        }
        Elo p1 = m1.linha[linhaAtual];
        Elo p2 = m2.linha[linhaAtual];

        somarLinha(resultado, linhaAtual, p1, p2);

        somarMatrizesAux(m1, m2, resultado, linhaAtual + 1);
    }

    // Item 13
    private static void somarLinha(MatrizEsparsaLista resultado, int linha, Elo p1, Elo p2) {
        if (p1 == null && p2 == null) {
            return;
        }
        if (p1 != null && (p2 == null || p1.coluna < p2.coluna)) {
            resultado.inserirElemento(linha, p1.coluna, p1.dado);
            somarLinha(resultado, linha, p1.prox, p2);
        }
        else if (p2 != null && (p1 == null || p2.coluna < p1.coluna)) {
            resultado.inserirElemento(linha, p2.coluna, p2.dado);
            somarLinha(resultado, linha, p1, p2.prox);
        }
        else {
            resultado.inserirElemento(linha, p1.coluna, p1.dado + p2.dado);
            somarLinha(resultado, linha, p1.prox, p2.prox);
        }
    }

    public static MatrizEsparsaLista somarMatrizesIterativa(MatrizEsparsaLista m1, MatrizEsparsaLista m2) {
        if ((m1 == null || m2 == null) || (m1.dimensao != m2.dimensao)) {
            return null;
        }
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(m1.dimensao);
        for (int i = 0; i < m1.dimensao; i++) {
            Elo p1 = m1.linha[i];
            Elo p2 = m2.linha[i];
            while (p1 != null || p2 != null) {
                if (p1 != null && (p2 == null || p1.coluna < p2.coluna)) {
                    resultado.inserirElemento(i, p1.coluna, p1.dado);
                    p1 = p1.prox;
                } else if (p2 != null && (p1 == null || p2.coluna < p1.coluna)) {
                    resultado.inserirElemento(i, p2.coluna, p2.dado);
                    p2 = p2.prox;
                } else {
                    resultado.inserirElemento(i, p1.coluna, p1.dado + p2.dado);
                    p1 = p1.prox;
                    p2 = p2.prox;
                }
            }
        }
        return resultado;
    }

    // Item 14
    public static MatrizEsparsaLista multiplicarMatrizes(MatrizEsparsaLista m1, MatrizEsparsaLista m2) {
        if ((m1 == null || m2 == null) || (m1.dimensao != m2.dimensao)) {
            return null;
        }
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(m1.dimensao);
        multiplicarLinhas(m1, m2, resultado, 0);
        return resultado;
    }

    // Item 14
    private static void multiplicarLinhas(MatrizEsparsaLista m1, MatrizEsparsaLista m2, MatrizEsparsaLista resultado, int i) {
        if (i >= m1.dimensao) return;
        Elo p1 = m1.linha[i];
        multiplicarElo(m1, m2, resultado, i, p1);
        multiplicarLinhas(m1, m2, resultado, i + 1);
    }

    // Item 14
    private static void multiplicarElo(MatrizEsparsaLista m1, MatrizEsparsaLista m2, MatrizEsparsaLista resultado, int i, Elo p1) {
        if (p1 == null) return;
        Elo p2 = m2.linha[p1.coluna];
        multiplicarEloP2(m1, m2, resultado, i, p1, p2);
        multiplicarElo(m1, m2, resultado, i, p1.prox);
    }

    // Item 14
    private static void multiplicarEloP2(MatrizEsparsaLista m1, MatrizEsparsaLista m2, MatrizEsparsaLista resultado, int i, Elo p1, Elo p2) {
        if (p2 == null) return;
        int novoValor = p1.dado * p2.dado;
        resultado.inserirNaMultiplicacao(resultado, i, p2.coluna, novoValor);
        multiplicarEloP2(m1, m2, resultado, i, p1, p2.prox);
    }

    // Item 14
    private static void inserirNaMultiplicacao(MatrizEsparsaLista matriz, int i, int coluna, int valor) {
        Elo atual = matriz.linha[i];
        Elo anterior = null;
        while (atual != null && atual.coluna < coluna) {
            anterior = atual;
            atual = atual.prox;
        }
        if (atual != null && atual.coluna == coluna) {
            atual.dado += valor;
        } else {
            Elo novo = new Elo(coluna, valor);
            if (anterior == null) {
                novo.prox = matriz.linha[i];
                matriz.linha[i] = novo;
            } else {
                novo.prox = anterior.prox;
                anterior.prox = novo;
            }
        }
    }

    public static MatrizEsparsaLista multiplicarIterativo(MatrizEsparsaLista m1, MatrizEsparsaLista m2) {
        if ((m1 == null || m2 == null) || (m1.dimensao != m2.dimensao)) {
            return null;
        }
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(m1.dimensao);
        for (int i = 0; i < m1.dimensao; i++) {
            Elo p1 = m1.linha[i];
            while (p1 != null) {
                Elo p2 = m2.linha[p1.coluna];
                while (p2 != null) {
                    int novoValor = p1.dado * p2.dado;
                    resultado.inserirNaMultiplicacao(resultado, i, p2.coluna, novoValor);
                    p2 = p2.prox;
                }
                p1 = p1.prox;
            }
        }
        return resultado;
    }

    // Item 15
    public MatrizEsparsaLista gerarTransposta() {
        MatrizEsparsaLista transposta = new MatrizEsparsaLista(dimensao);
        gerarTransposta(transposta, 0); // Inicia a recursão na linha 0
        return transposta;
    }

    // Item 15
    private void gerarTransposta(MatrizEsparsaLista transposta, int i) {
        if (i >= dimensao) return;
        Elo atual = linha[i];
        gerarTranspostaAux(transposta, i, atual);

        gerarTransposta(transposta, i + 1);
    }

    // Item 15
    private void gerarTranspostaAux(MatrizEsparsaLista transposta, int i, Elo elo) {
        if (elo == null) return;

        transposta.inserirElemento(elo.coluna, i, elo.dado);
        gerarTranspostaAux(transposta, i, elo.prox);
    }

    public MatrizEsparsaLista gerarTranspostaIterativo() {
        MatrizEsparsaLista transposta = new MatrizEsparsaLista(dimensao);
        for (int i = 0; i < dimensao; i++) {
            Elo atual = linha[i];
            while (atual != null) {
                transposta.inserirElemento(atual.coluna, i, atual.dado);
                atual = atual.prox;
            }
        }
        return transposta;
    }

    public static MatrizEsparsaLista criarMatrizAleatoria(int dimensao) {
        MatrizEsparsaLista novaMatriz = new MatrizEsparsaLista(dimensao);

        int lotacao = (int) ((novaMatriz.dimensao * novaMatriz.dimensao) * 0.4) ;

        novaMatriz.preencherMatrizAleatoria(lotacao);
        return novaMatriz;
    }


    private void preencherMatrizAleatoria(int lotacao) {
        Random random = new Random();
        int x = 0, y = 0;
        do {
            x = random.nextInt(this.dimensao);
            y = random.nextInt(this.dimensao);
        } while (this.buscarElementoPorCoordenada(x, y) != 0);
        this.inserirElemento(x, y, random.nextInt(100));
        lotacao--;
        if (lotacao <= 0) {
            return;
        }
        this.preencherMatrizAleatoria(lotacao);

    }

    public static MatrizEsparsaLista criarMatrizAleatoriaIterativo(int dimensao) {
        MatrizEsparsaLista novaMatriz = new MatrizEsparsaLista(dimensao);
        int lotacao = (int) ((novaMatriz.dimensao * novaMatriz.dimensao) * 0.4);
        novaMatriz.preencherMatrizAleatoriaIterativo(lotacao);
        return novaMatriz;
    }

    private void preencherMatrizAleatoriaIterativo(int lotacao) {
        Random random = new Random();
        while (lotacao > 0) {
            int x, y;
            do {
                x = random.nextInt(this.dimensao);
                y = random.nextInt(this.dimensao);
            } while (this.buscarElementoPorCoordenada(x, y) != 0);

            this.inserirElementoIterativo(x, y, random.nextInt(100));
            lotacao--;
        }
    }

}
