package dominio;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Images extends JPanel {
	
	/*
	 * Tem que colocar a imagem num painel, onde vai ficar todas as peças
	 * do tabulerio - Alessandro 31/12/11 
	 * 
	*/
	protected String[][] ImagemTab = {
			{ "1", "1", "2a", "2a", "2a", "1", "1" },
		    { "1", "1", "2a", "2a", "2a", "1", "1" },
		    { "2a", "2a", "2a", "2a", "2a", "2a", "2a" },
		    { "2a", "2a", "2a", "3",  "2a", "2a", "2a" },
			{ "2a", "2a", "2a", "2a", "2a", "2a", "2a" },
			{ "1", "1", "2a", "2a", "2a", "1", "1" },
			{ "1", "1", "2a", "2a", "2a", "1", "1" } };
	//tabuleiro com de 32 peças
	
	private ImageIcon[][] image;

	//inic
	public Images() {
		this.image = new ImageIcon[7][];

		for (int i = 0; i < this.ImagemTab.length; i++) {
			this.image[i] = new ImageIcon[7];
			for (int j = 0; j < 7; j++)
				this.image[i][j] = new ImageIcon("src/imagens/"+ this.ImagemTab[i][j] + ".gif");
		}
	}

	/*
	 * Lembrar de Alterar o por: imagem/
	 * "C:\\Users\\Alessandro\\Desktop\\Resta Um\\imagens\ (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */

	public Dimension getPreferredSize() {
		return new Dimension(560, 560);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	// definir a imagem do tabuleiro peca,
	public void paint(Graphics paramGraphics) {
		for (int i = 0; i < this.ImagemTab.length; i++)
			for (int j = 0; j < 7; j++)
				this.image[i][j].paintIcon(this, paramGraphics, 80 * j, 80 * i);
	}

	public void setPeca(int paramInt1, int paramInt2, String paramString) {
		this.ImagemTab[paramInt2][paramInt1] = null;
		this.image[paramInt2][paramInt1] = null;
		this.ImagemTab[paramInt2][paramInt1] = new String(paramString);
		this.image[paramInt2][paramInt1] = new ImageIcon("src/imagens/"+ paramString + ".gif");
		repaint();
	}

	// retornar na imagem do tabuleiro com os parâmetros.

	public String getPecas(int paramInt1, int paramInt2) {
		return this.ImagemTab[paramInt2][paramInt1];
	}

	public String[][] getTaboo() {
		return this.ImagemTab;
	}
}