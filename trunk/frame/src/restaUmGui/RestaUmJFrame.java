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

		//imprimir a quantidade de peças = 32
		this.peca = new JLabel(
				"                Restam: 32 peças                  ");
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
		//colocar metodo para não poder alterar o frame
		setResizable(false);
		
		// Nonononononononono
		
		//Abrir janela
		show();
	}

	//realiza um processo de clique passando os parâmetros
	private void processClick(int paramInt1, int paramInt2) {
		//se ñ tem FimJogo
		if (!this.cJogador.FimJogo()) {
			//realiza a jogada - lembrar de atulizar a jogada dos parâmetros
			this.cJogador.jogar(paramInt1, paramInt2);
			//mostrar a qnt de peças restantes
			mostrarPecasRestantes();
		}
	}

	private void verificarStatus() {
		String str = new String();

		if (this.cJogador.FimJogo()) {
			if (this.cJogador.getNumPecas() < 7) {
				if (this.cJogador.getNumPecas() == 6) {
					str = "Game Over...\nVocê deixou 6 peças.";
				} else if (this.cJogador.getNumPecas() == 5) {
					str = "Game Over.\nVocê deixou 5 peças.";
				} else if (this.cJogador.getNumPecas() == 4) {
					str = "Game Over...\nVocê deixou 4 peças.";
				} else if (this.cJogador.getNumPecas() == 3) {
					str = "Game Over...\nVocê deixou 3 peças.";
				} else if (this.cJogador.getNumPecas() == 2) {
					str = "Game Over...\nVocê deixou 2 peças.";
				} else if ((this.cJogador.getNumPecas() == 1)
						&& (!this.cJogador.PecaNoCentro())) {
					str = "Game Over...\nVocê deixou 1 peça.";
				} else {
					str = "Parabéns!\nVocê deixou 1 peça e no centro.";
				}

			} else {
				str = "Game Over...";
			}
			JOptionPane.showMessageDialog(this, str, "Fim do Jogo", 1);

		}
	}

	private void mostrarPecasRestantes() {
		this.peca.setText("                Restam: " + this.cJogador.getNumPecas()
				+ " peças                  ");
	}

	private void fechar() {
		System.exit(0);
	}

	//Criar método novo jogo 
	private void novoJogo() {
		JOptionPane.showMessageDialog(this, "Um novo Jogo será iniciado.",
				"Novo Jogo", 1);

		this.cJogador = null;
		this.container.remove(this.image);
		this.image = null;
		this.image = new Images();
		this.container.add(this.image);
		this.image.addMouseListener(this);
		this.cJogador = new ControleJogador(this.image);
		this.peca.setText("                Restam: 32 peças                  ");
	}

	private void sobre() {
		String str = new String("Desenvolvido por:\r\n   Alessandro Oliveira dos Anjos  mat: 201114040444\r\n   Rafael Oliveira Moura                  mat: 201114040290\r\n\r\nIFRN - Instituto Federal de Educação Ciencia e Tecnologia do RN\r\n\r\nDisciplina:  Programação Orientada a Objetos\r\nProfessor: Alexandre Gomes de Lima");

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
