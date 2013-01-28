package dominio;

public class ControleJogador {
	private int controleClique;
	private int lX;
	private int lY;
	private Images taboo;
	private int numPecas;
	private boolean fim;
	private boolean centro;

	public ControleJogador(Images paramImages) {
		this.controleClique = 1;
		this.taboo = paramImages;
		this.numPecas = 32;
		this.fim = false;
		this.centro = false;
	}

	public void setControleClique(int paramInt) {
		this.controleClique = paramInt;
	}

	public void jogar(int paramInt1, int paramInt2) {
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 0;
		
		String str = new String();

		i = paramInt1 / 80;
		j = paramInt2 / 80;
		if (isValid(i, j))
			if (this.controleClique == 1) {
				this.lX = i;
				this.lY = j;
				setControleClique(2);
			} else {
				k = (i + this.lX) / 2;
				m = (j + this.lY) / 2;
				if (this.taboo.getPecas(k, m).equals("2a")) {
					processarJogada(k, m, i, j);
				}
				setControleClique(1);
			}
	}

	private boolean isValid(int paramInt1, int paramInt2) {
		boolean i = false;
		String str = new String();
		int j = 0;
		int k = 0;

		str = this.taboo.getPecas(paramInt1, paramInt2);
		if (!str.equals("1")) {
			if (this.controleClique == 1) {
				if (str.equals("2a")) {
					i = true;
				}
			} else if (str.equals("3")) {
				j = Math.abs(this.lX - paramInt1);
				k = Math.abs(this.lY - paramInt2);
				if (((j == 0) && (k == 2)) || ((j == 2) && (k == 0))) {
					i = true;
				} else {
					j = k = 0;
					setControleClique(1);
				}
			} else {
				i = false;
				setControleClique(1);
			}
		}

		return i;
	}

	private void processarJogada(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		this.taboo.setPeca(this.lX, this.lY, "3");
		this.taboo.setPeca(paramInt1, paramInt2, "3");
		this.taboo.setPeca(paramInt3, paramInt4, "2a");
		this.numPecas -= 1;
	}

	public int getNumPecas() {
		return this.numPecas;
	}

	public boolean FimJogo() {
		String[][] localObject = new String[7][];

		localObject = this.taboo.getTaboo();
		if ((this.numPecas < 20) && (!this.fim)) {
			if (this.numPecas != 1) {
				if (!jogadaValida(localObject))
					this.fim = true;
			} else {
				this.fim = true;
				int i = 0;
				int j = 0;
				int k = 0;
				for (j = 0; (j < localObject.length) && (i == 0); j++) {
					for (k = 0; k < localObject.length; k++) {
						if (localObject[j][k].equals("2a")) {
							i = 1;
							break;
						}
					}
				}

				//local de posição da peça no centrp
				if ((j == 4) && (k == 3)) {
					this.centro = true;
				}
			}
		}

		return this.fim;
	}

	public boolean jogadaValida(String[][] paramArrayOfString) {
		boolean i = false;

		for (int j = 0; (j < paramArrayOfString.length) && (i == false); j++) {
			for (int k = 0; k < paramArrayOfString.length; k++) {
				if ((!paramArrayOfString[j][k].equals("1"))
						&& (!paramArrayOfString[j][k].equals("3"))) {
					if ((acharLinha(j, k, paramArrayOfString))
							|| (acharColuna(j, k, paramArrayOfString))) {
						i = true;
						break;
					}
					
					//jogada inv
					if ((j == 6) && (k == 4)) {
						i = false;
						break;
					}
				}
			}

		}

		return i;
	}
	
	// verificar as linhas e as colunas
	
	//se a imagem está na posição na linha

	private boolean acharLinha(int paramInt1, int paramInt2,
			String[][] paramArrayOfString) {
		boolean i = false;

		if ((paramInt2 > 1) && (paramInt2 < 5)) {
			if (((paramArrayOfString[paramInt1][(paramInt2 - 1)].equals("2a")) && (paramArrayOfString[paramInt1][(paramInt2 - 2)]
					.equals("3")))
					|| ((paramArrayOfString[paramInt1][(paramInt2 + 1)]
							.equals("2a")) && (paramArrayOfString[paramInt1][(paramInt2 + 2)]
							.equals("3")))) {
				i = true;
			}
		} else if ((paramInt2 == 0) || (paramInt2 == 1)) {
			if ((paramArrayOfString[paramInt1][(paramInt2 + 1)].equals("2a"))
					&& (paramArrayOfString[paramInt1][(paramInt2 + 2)]
							.equals("3"))) {
				i = true;
			}
		} else if (((paramInt2 == 5) || (paramInt2 == 6))
				&& (paramArrayOfString[paramInt1][(paramInt2 - 1)].equals("2a"))
				&& (paramArrayOfString[paramInt1][(paramInt2 - 2)].equals("3"))) {
			i = true;
		}

		return i;
	}

	//se a imagem está na posição na coluna
	private boolean acharColuna(int paramInt1, int paramInt2,
			String[][] paramArrayOfString) {
		boolean i = false;

		if ((paramInt1 > 1) && (paramInt1 < 5)) {
			if (((paramArrayOfString[(paramInt1 - 1)][paramInt2].equals("2a")) && (paramArrayOfString[(paramInt1 - 2)][paramInt2]
					.equals("3")))
					|| ((paramArrayOfString[(paramInt1 + 1)][paramInt2]
							.equals("2a")) && (paramArrayOfString[(paramInt1 + 2)][paramInt2]
							.equals("3")))) {
				i = true;
			}
		} else if ((paramInt1 == 0) || (paramInt1 == 1)) {
			if ((paramArrayOfString[(paramInt1 + 1)][paramInt2].equals("2a"))
					&& (paramArrayOfString[(paramInt1 + 2)][paramInt2]
							.equals("3"))) {
				i = true;
			}
		} else if (((paramInt1 == 5) || (paramInt1 == 6))
				&& (paramArrayOfString[(paramInt1 - 1)][paramInt2].equals("2a"))
				&& (paramArrayOfString[(paramInt1 - 2)][paramInt2].equals("3"))) {
			i = true;
		}

		return i;
	}

	
	//metodo que faca com que armazene se o jogo acabou
	//para colocar na classe 
	public boolean getStatus() {
		return this.fim;
	}

	public boolean PecaNoCentro() {
		return this.centro;
	}
}