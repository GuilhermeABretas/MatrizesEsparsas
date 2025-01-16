public class MatrizEsparsaLista {
    private ListaOrdenadaMatriz[] linha;
    private int dimensao;

    public MatrizEsparsaLista(int tamanho) {
        this.linha = new ListaOrdenadaMatriz[tamanho];
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
            linha[i] = new ListaOrdenadaMatriz();
        }
        linha[i].insere(j, valor);
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

        return linha[i].remove(j);
    }

    // Item 3
    public boolean buscarElemento(int valor) {
        return buscarElemento(valor, 0);
    }

    // Item 3
    private boolean buscarElemento(int valor, int i) {
        if (i >= dimensao) {
            return false;
        }

        if (linha[i].buscaRecursiva(valor)) {
            return true;
        }

        return buscarElemento(valor, i + 1);
    }

    // Item 4
    public void printarMatriz() {
        printarMatriz(0);
    }


    // Item 4 -- NÃO ESTÁ CERTA - TEM QUE IMPLEMENTAR REGRA PRA PRINTAR ZEROS. EX: SE COLUNA É 3 E ULTIMA COLUNA VISITADA FOR NULL, IMPRIME 3 ZEROS E DEPOIS O 3
    private void printarMatriz(int i) {
        if (i >= dimensao) return;

        linha[i].imprimeRecursivo();

        printarMatriz(i + 1);
    }

    // Item 6
    public boolean isVazia() {
        return isVazia(0);
    }

    // Item 6
    private boolean isVazia(int i) {
        if (i >= dimensao) return false;
        if (linha[i] == null || linha[i].vazia()) {
            return isVazia(i + 1);
        } else {
            return true;
        }
    }

    // Item 7
    public boolean isDiagonal() {
        return isDiagonal(0);
    }

    // Item 7
    private boolean isDiagonal(int i) {
        if (i >= dimensao) return true;

        if (linha[i].buscaColunaRecursiva(i)) {
            return isDiagonal(i + 1);
        }

        return false;
    }

    // Item 8
    public boolean isLinha() {
        return isLinha(0, false);
    }

    // Item 8
    private boolean isLinha(int i, boolean achouNoEmLinha) {
        if (i >= dimensao) return achouNoEmLinha;

        if (!(linha[i].vazia()) && achouNoEmLinha) {
            return false;
        }
        else if (!(linha[i].vazia()) && !achouNoEmLinha) {
            achouNoEmLinha = true;
        }

        return isLinha(i + 1, achouNoEmLinha);
    }

    // Item 9
    public boolean isColuna() {
        return isColuna(0, -1);
    }

    // Item 9
    private boolean isColuna(int i, int colunaEncontrada) {
        if (i >= dimensao) {
          if (colunaEncontrada == -1) {
              return false;
          }
          return true;
        }
        if (linha[i].numElos > 1) {
            return false;
        }

        if (!linha[i].vazia() && linha[i].numElos == 1) {
            if (colunaEncontrada == -1) {
                colunaEncontrada = linha[i].prim.coluna;
            } else if (linha[i].prim.coluna != colunaEncontrada) {
                return false;
            }
        }

        return isColuna(i + 1, colunaEncontrada);
    }
}
