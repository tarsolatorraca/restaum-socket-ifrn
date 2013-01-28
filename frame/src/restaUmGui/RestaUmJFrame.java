package restaUmGui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import dominio.ControleJogador;
import dominio.Images;

public class RestaUmJFrame extends JFrame implements MouseListener {
	private Images image;
	private Container container;
	private JLabel peca;
	private ControleJogador cJogador;
	private String[] menuPrincipalStr = { "Jogo", "Sobre" };
	private JMenu[] menuPrincipal;
	
	

	public RestaUmJFrame() {
		 
		super(".:: Resta 1 - POO ::.");

		this.container = getContentPane();
		getContentPane().setBackground(Color.BLACK);
		FlowLayout localFlowLayout = new FlowLayout();
		localFlowLayout.setAlignment(1);
		this.container.setLayout(localFlowLayout);

		//imprimir a quantidade de pe�as = 32
		this.peca = new JLabel(
				"                Restam: 32 pe�as                  ");
		this.peca.setFont(new Font("Verdana", 1, 20));
		this.peca.setForeground(Color.red);

		this.container.add(this.peca);

		this.image = new Images();
		this.container.add(this.image);
		this.image.addMouseListener(this);

		this.cJogador = new ControleJogador(this.image);

		JMenuBar localJMenuBar = new JMenuBar();
		setJMenuBar(localJMenuBar);

		this.menuPrincipal = new JMenu[this.menuPrincipalStr.length];
		for (int i = 0; i < this.menuPrincipal.length; i++) {
			this.menuPrincipal[i] = new JMenu(this.menuPrincipalStr[i]);
			localJMenuBar.add(this.menuPrincipal[i]);
		}

		JMenuItem localJMenuItem1 = new JMenuItem("Novo Jogo");
		this.menuPrincipal[0].add(localJMenuItem1);
		localJMenuItem1.addActionListener(new ActionListener() 
		 {
			
			public void actionPerformed(ActionEvent paramActionEvent) {
				RestaUmJFrame.this.novoJogo();
			}
		});

		JMenuItem localJMenuItem2 = new JMenuItem("Sair");
		localJMenuItem2.addActionListener(new ActionListener() 
		{
		
			public void actionPerformed(ActionEvent paramActionEvent) {
				RestaUmJFrame.this.fechar();
			}
		});
		this.menuPrincipal[0].add(localJMenuItem2);

		
		JMenuItem localJMenuItem5 = new JMenuItem("Sobre");
		this.menuPrincipal[1].add(localJMenuItem5);
		
		localJMenuItem5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				RestaUmJFrame.this.sobre();
				
			}
			
		});
		
		
		Image localImage = Toolkit
				.getDefaultToolkit()
				.getImage("src/imagens/icon.gif");
		setIconImage(localImage);
		setSize(720, 670);
		setLocation(200, 0);
		//colocar metodo para n�o poder alterar o frame
		setResizable(false);
		
		// Nonononononononono
		
		//Abrir janela
		show();
	}

	//realiza um processo de clique passando os par�metros
	private void processClick(int paramInt1, int paramInt2) {
		//se � tem FimJogo
		if (!this.cJogador.FimJogo()) {
			//realiza a jogada - lembrar de atulizar a jogada dos par�metros
			this.cJogador.jogar(paramInt1, paramInt2);
			//mostrar a qnt de pe�as restantes
			mostrarPecasRestantes();
		}
	}

	private void verificarStatus() {
		String str = new String();

		if (this.cJogador.FimJogo()) {
			if (this.cJogador.getNumPecas() < 7) {
				if (this.cJogador.getNumPecas() == 6) {
					str = "Game Over...\nVoc� deixou 6 pe�as.";
				} else if (this.cJogador.getNumPecas() == 5) {
					str = "Game Over.\nVoc� deixou 5 pe�as.";
				} else if (this.cJogador.getNumPecas() == 4) {
					str = "Game Over...\nVoc� deixou 4 pe�as.";
				} else if (this.cJogador.getNumPecas() == 3) {
					str = "Game Over...\nVoc� deixou 3 pe�as.";
				} else if (this.cJogador.getNumPecas() == 2) {
					str = "Game Over...\nVoc� deixou 2 pe�as.";
				} else if ((this.cJogador.getNumPecas() == 1)
						&& (!this.cJogador.PecaNoCentro())) {
					str = "Game Over...\nVoc� deixou 1 pe�a.";
				} else {
					str = "Parab�ns!\nVoc� deixou 1 pe�a e no centro.";
				}

			} else {
				str = "Game Over...";
			}
			JOptionPane.showMessageDialog(this, str, "Fim do Jogo", 1);

		}
	}

	private void mostrarPecasRestantes() {
		this.peca.setText("                Restam: " + this.cJogador.getNumPecas()
				+ " pe�as                  ");
	}

	private void fechar() {
		System.exit(0);
	}

	//Criar m�todo novo jogo 
	private void novoJogo() {
		JOptionPane.showMessageDialog(this, "Um novo Jogo ser� iniciado.",
				"Novo Jogo", 1);

		this.cJogador = null;
		this.container.remove(this.image);
		this.image = null;
		this.image = new Images();
		this.container.add(this.image);
		this.image.addMouseListener(this);
		this.cJogador = new ControleJogador(this.image);
		this.peca.setText("                Restam: 32 pe�as                  ");
	}

	private void sobre() {
		String str = new String("Desenvolvido por:\r\n   Alessandro Oliveira dos Anjos  mat: 201114040444\r\n   Rafael Oliveira Moura                  mat: 201114040290\r\n\r\nIFRN - Instituto Federal de Educa��o Ciencia e Tecnologia do RN\r\n\r\nDisciplina:  Programa��o Orientada a Objetos\r\nProfessor: Alexandre Gomes de Lima");

		JOptionPane.showMessageDialog(this, str, "Sobre", 1, new ImageIcon(
				"src/imagens/icon.gif"));
	}

	public void mousePressed(MouseEvent paramMouseEvent) {
		processClick(paramMouseEvent.getX(), paramMouseEvent.getY());
	}

	public void mouseReleased(MouseEvent paramMouseEvent) {
		//mostrar o status de partida qnuando acabar o jogo.
		verificarStatus();
	}

	public void mouseClicked(MouseEvent paramMouseEvent) {
	}

	public void mouseEntered(MouseEvent paramMouseEvent) {
	}

	public void mouseExited(MouseEvent paramMouseEvent) {
	}
	
	
public static void main(String[] paramArrayOfString) {
		
		RestaUmJFrame restaum = new RestaUmJFrame();

		restaum.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent paramWindowEvent) {
				System.exit(0);
			}
		});
	}
}
