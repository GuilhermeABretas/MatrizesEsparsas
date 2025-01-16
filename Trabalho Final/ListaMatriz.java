public class ListaMatriz
{
	/* Refer�ncia para primeiro elemento */
	protected Elo prim;
	protected int numElos;

	protected class Elo
	{
		protected int coluna;
		protected int dado;
		protected Elo prox;

		public Elo()
		{
			prox = null;
		}

		public Elo(int coluna, int elem)
		{
			coluna = coluna;
			dado = elem;
			prox = null;
		}

		public Elo(int elem, Elo proxElem)
		{
			dado = elem;
			prox = proxElem;
		}
	}

	public ListaMatriz()
	{
		prim = null;
	}

	/* Testa se a lista est� vazia. */
	public boolean vazia()
	{
		return prim == null;
	}

	/* Insere elemento no in�cio da lista. */
	public void insere(int colunaInserida, int novo)
	{
		Elo p = new Elo(colunaInserida, novo);
		p.prox = prim;
		prim = p;
		numElos++;
	}

	/* Verifica se um determinado elemento est� na lista. */
	public boolean busca(int elem)
	{
		Elo p;

		for(p = prim; p != null; p = p.prox)
		{
			if(p.dado == elem)
				return true;
		}

		return false;
	}


	public boolean buscaRecursiva(int elem)
	{
		if(this.vazia())
			return false;

		return buscaRecursiva(elem, prim);
	}

	private boolean buscaRecursiva(int elem, Elo p)
	{
		if(p == null)
			return false;

		if(p.dado == elem)
			return true;

		return buscaRecursiva(elem, p.prox);
	}

	public boolean buscaColunaRecursiva(int coluna)
	{
		if(this.vazia())
			return false;

		return buscaRecursiva(coluna, prim);
	}

	private boolean buscaColunaRecursiva(int coluna, Elo p)
	{
		if(p == null || p.coluna > coluna)
			return false;

		if(p.coluna == coluna)
			return true;

		return buscaRecursiva(coluna, p.prox);
	}

	/* Remove da lista o primeiro elemento com valor igual a �elem". Ret. true se removeu. */
	public boolean remove(int elem)
	{
		Elo p;
		Elo ant = null; /* Refer�ncia para anterior. */

		for(p = prim; ((p != null) && (p.dado != elem)); p = p.prox)
			ant = p;

		/* Se p � null, ent�o n�o encontrou elemento. */
		if (p == null)
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

	/* Imprime todos os elementos da lista. */
	public void imprime()
	{
		Elo p;

		System.out.println("Elementos da lista:");

		for(p = prim; p != null; p = p.prox)
		{
			System.out.print(p.dado + " ");
		}

		System.out.println();
	}

	/* Implementa��o recursiva da fun��o de impress�o. */
	public void imprimeRecursivo()
	{
		System.out.println("Elementos da lista:");

		if(this.vazia())
			return;

		imprimeRecursivo(prim);

		System.out.println();
	}

	private void imprimeRecursivo(Elo p)
	{
		if(p == null)
			return;

		System.out.print(p.dado + " ");

		imprimeRecursivo(p.prox);
	}

	/* Calcula e retorna o tamanho da lista. */
	public int tamanho()
	{
		int tam = 0;
		Elo p = prim;

		while(p != null)
		{
			tam++;
			p = p.prox;
		}

		return tam;
	}

	/* Calcula e retorna o tamanho da lista de maneira recursiva. */
	public int tamanhoRecursivo()
	{
		if(this.vazia())
			return 0;

		return tamanhoRecursivo(prim);
	}

	private int tamanhoRecursivo(Elo p)
	{
		if(p == null)
			return 0;

		return 1 + tamanhoRecursivo(p.prox);
	}

	/* Resolu��o da quest�o 1 da lista auxiliar de Lista Encadeada. Este m�todo
	 * determina e retorna o maior inteiro contido por um elo da lista encadeada. */
	public int max()
	{
		if(this.vazia())
			return 0;

		Elo p;
		int maior = Integer.MIN_VALUE;

		for(p = prim; p != null; p = p.prox)
		{
			if(maior < p.dado)
				maior = p.dado;
		}

		return maior;
	}

	/* Resolu��o da quest�o 2 da lista auxiliar de Lista Encadeada. Esse m�todo
	 * tem o mesmo objetivo do m�todo anterior, por�m o realiza de forma recursiva. */
	public int maxRecursivo()
	{
		if(this.vazia())
			return 0;

		return maxRecursivo(prim);
	}

	/* M�todo privado auxiliar que implementa a recurs�o. */
	private int maxRecursivo(Elo p)
	{
		if(p == null)
			return Integer.MIN_VALUE;

		/* Chamada recursiva para obter o "max" do resto da lista. */
		int maior = maxRecursivo(p.prox);

		/* Compara o maior obtido do resto da lista com o "dado"
		 * do elo corrente. Atualiza se necess�rio. */
		if(maior < p.dado)
			maior = p.dado;

		return maior;
	}

	/* Resolu��o da quest�o 3 da lista auxiliar de Lista Encadeada. */
	public ListaMatriz separa(int n)
	{
		ListaMatriz l2 = new ListaMatriz();

		Elo p;

		/* Loop para encontrar o elo cujo atributo dado tenha valor "n". */
		for(p = prim; (p != null) && (p.dado != n); p = p.prox);

		/* Caso tenha chegado ao fim da lista sem encontrar o elemento. */
		if(p == null)
			return l2;

		/* O "prim" da nova lista � o pr�ximo do elo apontado por p. */
		l2.prim = p.prox;

		/* Faz o elo apontado por p ser o �ltimo elo da lista original. */
		p.prox = null;

		/* Retorna a nova lista constru�da. */
		return l2;
	}

}