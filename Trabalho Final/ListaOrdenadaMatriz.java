public class ListaOrdenadaMatriz extends ListaMatriz
{
    public void insere(int coluna, int novo)
    {
        Elo p, q;
        Elo ant = null;

        q = new Elo(coluna, novo);

        for (p = prim; ((p != null) && (p.coluna <=  coluna)); p = p.prox)
            ant = p;

        if (ant == null)
            prim = q;
        else
            ant.prox = q;

        numElos++;
        q.prox = p;
    }

    public boolean remove(int coluna)
    {
        Elo p;
        Elo ant = null; /* refer�ncia para anterior */

        for(p = prim; ((p != null) && (p.coluna < coluna)); p = p.prox)
            ant = p;

        /* Se p � null ou p.dado != elem, ent�o n�o encontrou elemento. */
        if ((p == null) || (p.coluna != coluna))
            return false;

        if (p == prim)
            prim = prim.prox; /* Remove elemento do in�cio. */
        else
            ant.prox = p.prox;  /* Remove elemento do meio. */

        /* Remove a �ltima refer�ncia para o elo a ser removido. Dessa forma,
         * o Garbage Collector ir� liberar essa mem�ria. */
        p = null;

        numElos--;

        return true;
    }

    public int calculaElementosComuns(ListaOrdenadaMatriz lista2)
    {
        Elo p1 = prim, p2 = lista2.prim;
        int total = 0;

        while ( (p1 != null) && (p2 != null) )
        {
            if (p1.dado < p2.dado)
            {
                p1 = p1.prox;
            }
            else if(p2.dado < p1.dado)
            {
                p2 = p2.prox;
            }
            else
            {
                total++;

                p1 = p1.prox;
                p2 = p2.prox;
            }
        }

        return total;
    }
}