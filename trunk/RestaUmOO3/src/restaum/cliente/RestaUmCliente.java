package restaum.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import restaum.dominio.RestaUm;
import restaum.erros.ErroConexaoCliente;

public class RestaUmCliente {

	private Socket cliente;

	DataOutputStream out;
	DataInputStream in;
	
	

	public void iniciarCliente(){

		try {
			// 10.17.2.115
			cliente = new Socket("localhost", 8181);

			// pegar os Streams
			//in = new DataInputStream(cliente.getInputStream());// canal para a
																// entrada de
																// dados

			out = new DataOutputStream(cliente.getOutputStream());// canal para
																	// a saída
																	// de dados

		} catch (UnknownHostException e) {
		//	e.printStackTrace();

			System.out.println("Problema ao conexão com o socket.");  
		} catch (IOException e) {
			//e.printStackTrace();
			 System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo socket.");  
		}

	}

	public void fechaConexao() {

		// fecha os objetos
		try {
			
			in.close();
			out.close();
			cliente.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Conexao dados pelo socket.");  
		}

		// ler.close();
	}
	
	public static void main(String[] args) {
		

		
		 
		try {

			Socket socket = new Socket("localhost", 8181);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());// canal para a saída de dados
			
			Scanner ler = new Scanner(System.in);
			
			String comando = "-1";
			String msg;
			
			while(comando.trim().equals("-1")){
				System.out.println("1- Jogar / 0 - Sair do Jogo");
				comando = ler.nextLine();
				
				if(comando.trim().equals("1")){
					System.out.println("Insira a mensagem");
					
					//
					Scanner scan = new Scanner(System.in);
					//RestaUm jogo = new RestaUm(cliente1, cliente2);
					RestaUm jogo = new RestaUm();
					
					int vencedor;
					int posx1;
					int posy1;
					int posx2;
					int posy2;

					do {
						System.out.println("  ***Jogo Resta 1*** /-/ IFRN ~~~ TADS /-/Versão 1.2/ ");
						System.out.println();
						System.out.println(jogo.toString()); // imprimir o tabuleiro
						System.out.println("Peças Restantes: " + jogo.getPecas()); // imprimi
																					// a
																					// quantidade
																					// de
																					// peças
																					// restantes
						System.out.println();
						System.out.println("Jogador: " + "("
								+ socket.getInetAddress().getHostName() + ")"
								+ " Insira as duas posições iniciais: ");
						posx1 = scan.nextInt(); // LER POSI. x INICIAL
						posy1 = scan.nextInt(); // LER POSI. y INICIAL
						System.out.println("Ok, " + "("
								+ socket.getInetAddress().getHostName() + ")"
								+ " Agora insira as duas posições finais: ");
						posx2 = scan.nextInt(); // LER POSI. x FINAL
						posy2 = scan.nextInt(); // LER POSI. y FINAL
						boolean ok = jogo.jogar(posx1, posy1, posx2, posy2);
						if (!ok) {
							System.out.println("\n\t***JOGADA INVÁLIDA!!!***\n");
						}
						vencedor = jogo.getJogadorVencedor();
					} while (vencedor == 0);

					System.out.println();
					System.out.println(jogo.toString());

					if (vencedor == -1) {
						System.out.println("\n***Game Over!!!***");
						System.out.println("Restando apenas: " + jogo.getPecas()
								+ " Peças.");
						System.out
								.println("Total de Jogadas Válidas: " + jogo.getJogadas());
					} else {
						System.out.println("Jogador " + vencedor + " venceu!!!");
						System.out.println("Com : " + jogo.getPecas()
								+ " Peça Sobrando no tabuleiro.");
						System.out.println("Total de Jogadas Válidas: " + jogo.getJogadas());
					}
					
					
					msg = ler.nextLine();
					comando = "-1";
				}else{
					comando = "0";
					out.writeUTF(comando+"");
				}
				
			}
			
			
			
			
			out.close();
			socket.close();
			ler.close();
				
		} catch (UnknownHostException e) {
			 e.printStackTrace();
			System.out.println("AA");
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo socket.\n Pedimos desculpas =(");  
		}
	}

}
