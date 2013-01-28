package dominio;

public class RestaUm {
	private int[][] tabuleiro; //tabuleiro (grade) do jogo	
	//indica de quem é a vez de jogar. 
	//1 para primeiro jogador e 2 para segundo jogador
	private int jogadorAtual;
	//ver método getJogadorVencedor()
	private int jogadorVencedor;
	//armazena a quantidade de jogadas realizadas
	private int	qtdJogadas;
	//quantidade de pecas
	private int pecas;

	public RestaUm(){
		
		jogadorAtual = 1;
		jogadorVencedor = 0;
		qtdJogadas = 0;
		pecas = 24;
		
		tabuleiro = new int[5][5];
		tabuleiro[0][0]=1;
		tabuleiro[0][1]=1;
		tabuleiro[0][2]=1;
		tabuleiro[0][3]=1;
		tabuleiro[0][4]=1;
		tabuleiro[1][0]=1;
		tabuleiro[1][1]=1;
		tabuleiro[1][2]=1;
		tabuleiro[1][3]=1;
		tabuleiro[1][4]=1;
		tabuleiro[2][0]=1;
		tabuleiro[2][1]=1;
		tabuleiro[2][2]=0; //peça vazia = 0
		tabuleiro[2][3]=1;
		tabuleiro[2][4]=1;
		tabuleiro[3][0]=1;
		tabuleiro[3][1]=1;
		tabuleiro[3][2]=1;
		tabuleiro[3][3]=1;
		tabuleiro[3][4]=1;
		tabuleiro[4][0]=1;
		tabuleiro[4][1]=1;
		tabuleiro[4][2]=1;
		tabuleiro[4][3]=1;
		tabuleiro[4][4]=1;		
	}
	
	/* Indica a marcação em determinada posição da grade. Zero significa 
    posição livre.
    i - linha da posição que se quer a marca
    j - coluna da posição que se quer a marca */
	public int getMarcacao(int i, int j){
		return tabuleiro[i][j];
	}
	
	//Indica se o jogo deu empate
	private int empate(){
		
		if(getPecas() <= 12)
		{
			if (       tabuleiro[3][4] == 1 && tabuleiro[2][4] == 1 
					|| tabuleiro[2][4] == 1 && tabuleiro[1][4] == 1 
					|| tabuleiro[2][4] == 1 && tabuleiro[2][3] == 1					    
					|| tabuleiro[1][0] == 1 && tabuleiro[2][0] == 1					    
					|| tabuleiro[2][0] == 1 && tabuleiro[2][1] == 1					    
					|| tabuleiro[3][0] == 1 && tabuleiro[2][0] == 1					    
					|| tabuleiro[4][2] == 1 && tabuleiro[4][3] == 1
					|| tabuleiro[4][2] == 1 && tabuleiro[3][2] == 1
					|| tabuleiro[4][2] == 1 && tabuleiro[4][1] == 1					
					|| tabuleiro[0][2] == 1 && tabuleiro[0][3] == 1
					|| tabuleiro[0][2] == 1 && tabuleiro[0][1] == 1
					|| tabuleiro[0][2] == 1 && tabuleiro[1][2] == 1
					
					|| tabuleiro[2][2] == 1 && tabuleiro[2][3] == 1				  
					|| tabuleiro[2][2] == 1 && tabuleiro[2][1] == 1
				    || tabuleiro[2][2] == 1 && tabuleiro[3][2] == 1
					|| tabuleiro[2][2] == 1 && tabuleiro[1][2] == 1
					|| tabuleiro[1][1] == 1 && tabuleiro[1][2] == 1
				    || tabuleiro[1][1] == 1 && tabuleiro[1][0] == 1
				    || tabuleiro[1][1] == 1 && tabuleiro[2][1] == 1
				    || tabuleiro[1][1] == 1 && tabuleiro[0][1] == 1    
				    || tabuleiro[3][3] == 1 && tabuleiro[3][4] == 1
				    || tabuleiro[3][3] == 1 && tabuleiro[3][2] == 1
				    || tabuleiro[3][3] == 1 && tabuleiro[4][3] == 1
				    || tabuleiro[3][3] == 1 && tabuleiro[2][3] == 1    
				    || tabuleiro[4][4] == 1 && tabuleiro[3][4] == 1
				    || tabuleiro[4][4] == 1 && tabuleiro[4][3] == 1
				        
				    || tabuleiro[0][0] == 1 && tabuleiro[0][1] == 1
				    || tabuleiro[0][0] == 1 && tabuleiro[1][0] == 1
				    || tabuleiro[4][0] == 1 && tabuleiro[4][1] == 1 
				    || tabuleiro[4][0] == 1 && tabuleiro[3][0] == 1 
				    || tabuleiro[3][1] == 1 && tabuleiro[3][2] == 1
				    || tabuleiro[3][1] == 1 && tabuleiro[3][0] == 1
 					|| tabuleiro[3][1] == 1 && tabuleiro[2][1] == 1
 					|| tabuleiro[3][1] == 1 && tabuleiro[4][1] == 1
 					|| tabuleiro[1][3] == 1 && tabuleiro[1][4] == 1
 					|| tabuleiro[1][3] == 1 && tabuleiro[1][2] == 1 
 					|| tabuleiro[1][3] == 1 && tabuleiro[0][3] == 1
 					|| tabuleiro[1][3] == 1 && tabuleiro[2][3] == 1   
 					|| tabuleiro[0][4] == 1 && tabuleiro[1][4] == 1
 					|| tabuleiro[0][4] == 1 && tabuleiro[0][3] == 1 )
			{
					return jogadorVencedor=0;
			}
			else 
				return -1;
			}
		return jogadorVencedor=0;	
		}		
		
	public boolean jogar(int i1, int j1, int i2, int j2){

		if(i1 < 5 && i2 < 5 && j1 < 5 && j1 < 5 
				&& i1 >= 0 && i2 >= 0 && j1 >= 0 && j1 >= 0 )
		{
			 if (       j1 < j2 && i1 != i2 //verifica as diagonais
					 || j1 > j2 && i1 > i2 
					 || j1 < j2 && i1 < i2
					 || j1 > j2 && i1 < i2)	{ return false;	}			
			 
			/* verifica na horizontal jogada vizinha (esq -> dir)
			verifica na hotizontal jogada vizinha (dir -> esq)
			*/
			 if ( i1==i2 && j1-j2==-1 || i1==i2 && j2-j1==-1 )  
			 { return false; }
			 
			 //se marcar no tabuleiro com peça = 0 e deslocar pra outro lugar gera jogada inválida			 
			 if (tabuleiro[i1][j1] != 1 || tabuleiro[i2][j2] != 0 )
				{	return false;	}	
			
			 if(j1 == j2 && i1 < i2)
				{
				 if(j2-j1==0 && i2-i1>=3 )
				 {return false;}
					if(tabuleiro[i1+1][j2] == 0)
					{
						return false;				
					}else
				    tabuleiro[i1+1][j2] = 0;
				}
				if(j1 == j2 && i1 > i2)
				{
					if(i1-i2>=3 && j1-j2==0 )
					{ return false; }
					
					if(tabuleiro[i1-1][j2] == 0)
					{
						return false;				
					}else
					tabuleiro[i1-1][j2] = 0;
				}
				if(j1 > j2 && i1 == i2)
				{
					
					if(j1-j2>=3 && i1-i2==0)
						{ return false; }
					
					if(tabuleiro[i1][j2+1] == 0)
					{
						return false;				
					}else
					tabuleiro[i1][j2+1] = 0;
				}
				
				if(j1 < j2 && i1 == i2)
				{
					if(i1-i2==0 && j2-j1>=3)
					{ return false; }
					if(tabuleiro[i1][j2-1] == 0)
					{
						return false;				
					}else
					tabuleiro[i1][j2-1] = 0;
				}
				tabuleiro[i2][j2] = 1; //marca no tbuleiro as duas ultimas coordenadas: (i2, j2)
				tabuleiro[i1][j1] = 0; //marca no tabuleiro as duas primeiras coordenadas (i1, j1)
			 
			qtdJogadas++;
		    pecas--;
			
		    if(getPecas() == 1){ //o jogador que ficou com a última peça
				jogadorVencedor = jogadorAtual;
			}
		    else if(empate() == -1) //deu empate ou game over
		    {
		    	jogadorVencedor = -1;
		    }
			else{ //a partida não acabou
				//troca jogador atual
				if(jogadorAtual == 1){
					jogadorAtual = 2;
				}
				else{
					jogadorAtual = 1;
				}
			}
			return true; //indica que a jogada é válida
		}		
		else
		{ //jogada inválida
			return false;
		}
	}
	
	/* Retorna o número do jogador atual, ou seja, que jogador tem a vez. 
	   1 indica primeiro jogador  e 2 indica segundo jogador. */
	public int getJogadorAtual(){
		return jogadorAtual;
	}
	
	/* Retorna o número do jogador que venceu partida. 1 indica primeiro jogador, 
    2 indica segundo jogador, zero indica que a partida ainda não acabou e 
    -1 indica que a partida terminou empatada */
	public int getJogadorVencedor(){
		return jogadorVencedor;
	}
	
	public int getPecas(){
		// colocar dentro do metodo jog "pecas--;"
		return pecas; //retorna a qnt de peças restantes
	}
	
	public int getJogadas(){
		return qtdJogadas; //retorna a qnt de jogadas válidas.
	}
	
	public String toString(){
		
		String str = "";
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				str += getMarcacao(i, j) + " ";
			}
			str += "\n";
		}
		return str;
	}

}
