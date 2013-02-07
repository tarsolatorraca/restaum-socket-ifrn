package restaum.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RestaUmServidor {

	private static final int MAX_HOSTS = 2;
	private static int numHosts = 0;
	
	//private static Map<Integer, String> mapaJogadores;
	
	private static Map<String, Socket> mapaJogadores;
	
	private ServerSocket server;
	
	public static void main(String[] args) {
		
		// mapaJogadores = new HashMap<Integer, String>();
		 
		mapaJogadores = new HashMap<String, Socket>();
		
		
		new RestaUmServidor().run();	
	}

	private void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8181);
			while(numHosts < MAX_HOSTS){
				establishConnection(serverSocket);
			} 
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void establishConnection(ServerSocket serverSocket) throws IOException {
		System.out.println("\n\nAguardando conexão...");
		Socket socket = serverSocket.accept();
		
		//mapaJogadores.put(socket.getInetAddress().getHostName(), server.accept());
		//System.out.println("Insira ");
		
		
		
		numHosts++;
		System.out.println(socket.getInetAddress().getHostName() + "("+ socket.getInetAddress().getHostAddress() +")" + " se conectou...");
		System.out.println("Restam "+(MAX_HOSTS - numHosts)+" conexões...");
		new Communication(this, socket).start();
	}

	public void sair(Socket socket) {
		System.out.println(socket.getInetAddress().getHostName() + "("+ socket.getInetAddress().getHostAddress() +")" + " se desconectou...");
		numHosts--;
		System.out.println("Restam "+(MAX_HOSTS - numHosts)+" conexões...");		
	}

}
