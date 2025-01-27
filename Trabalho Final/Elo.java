public class Elo {
    protected int coluna;
    protected int dado;
    protected Elo prox;

    public Elo()
    {
        prox = null;
    }

    public Elo(int cl, int elem)
    {
        coluna = cl;
        dado = elem;
        prox = null;
    }

    public Elo(int cl, int elem, Elo proxElem)
    {
        coluna = cl;
        dado = elem;
        prox = proxElem;
    }
}
