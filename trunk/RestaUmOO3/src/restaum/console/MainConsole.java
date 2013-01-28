package restaum.console;

import java.util.Scanner;

import restaum.dominio.RestaUm;

public class MainConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		RestaUm jogo = new RestaUm();
		
		int vencedor;
		int posx1;
		int posy1;
		int posx2;
		int posy2;
		
		do{
			 System.out.println("  ***Jogo Resta 1*** /-/ IFRN ~~~ TADS ~~~ POO  /-/Versão 1.1/ ");
			 System.out.println();
			 System.out.println(jogo.toString()); //imprimir o tabuleiro			 
			 System.out.println("Peças Restantes: "+jogo.getPecas()); //imprimi a quantidade de peças restantes
			 System.out.println();
			 System.out.println("Jogador: " + jogo.getJogadorAtual() + " Insira as duas posições iniciais: ");
			 posx1 = scan.nextInt(); //LER POSI. x INICIAL
			 posy1 = scan.nextInt(); //LER POSI. y INICIAL
			 System.out.println("Ok, " + jogo.getJogadorAtual() + " Agora insira as duas posições finais: ");
			 posx2 = scan.nextInt(); //LER POSI. x FINAL
			 posy2 = scan.nextInt(); //LER POSI. y FINAL
			boolean ok = jogo.jogar(posx1, posy1, posx2, posy2);
			if(!ok){
				System.out.println("\n\t***JOGADA INVÁLIDA!!!***\n");
			}
			vencedor = jogo.getJogadorVencedor();
		}while(vencedor == 0);
		
		System.out.println();
		System.out.println(jogo.toString());		
		
		if(vencedor == -1){
			System.out.println("\n***Game Over!!!***");
			System.out.println("Restando apenas: "+jogo.getPecas() +" Peças.");
			System.out.println("Total de Jogadas Válidas: "+jogo.getJogadas());
		}
		else{
			System.out.println("Jogador " + vencedor + " venceu!!!");
			System.out.println("Com : "+jogo.getPecas()+" Peça Sobrando no tabuleiro.");
			System.out.println("Total de Jogadas Válidas: "+jogo.getJogadas());
		}

	}

}
