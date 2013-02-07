package restaum.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Communication extends Thread {

	private RestaUmServidor server;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private static final String FAIL = "0";
	private static final String SUCCESS = "1";
	private boolean sair = false;

	public Communication(RestaUmServidor server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			in = new DataInputStream(this.getSocket().getInputStream());
			out = new DataOutputStream(this.getSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// new VerifyConnection(this).run();
		String command[] = { "", "" };
		while (!isSair()) {
			try {
				command = in.readUTF().trim().split(":");
				executeCommand(command);
			} catch (Exception e) {
				command[0] = SUCCESS;
			}
		}
	}

	public void sair() {
		try {
			in.close();
			out.close();
			getSocket().close();
			this.server.sair(getSocket());
			this.interrupt();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void executeCommand(String[] command) {
		switch (command[0]) {
		case FAIL:
			imprimieMSG("saindo...");
			response(SUCCESS);
			sair = true;
			sair();
			break;
		case SUCCESS:
			imprimieMSG(command[1]);
			response(SUCCESS);
			break;
		default:
			imprimieMSG("Commando inválido");
			response(FAIL);
			break;
		}
	}

	private void response(String i) {
		try {
			out.writeUTF(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void imprimieMSG(String string) {
		System.out.println(getSocket().getInetAddress().getHostName() + "("
				+ getSocket().getInetAddress().getHostAddress() + ")" + ": "
				+ string);
	}

	public boolean isSair() {
		return sair;
	}

	public Socket getSocket() {
		return socket;
	}
}
