package slug.soc.view;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 * @author slug
 *
 */
public class GameView extends JPanel {
	
	private Image gameImage;
	
	public GameView(){
	}

	public Dimension getPreferredSize(){
		return new Dimension(1000,500);	
	}
	
	public void drawGameImage(Image gi){
		gameImage = gi;
		repaint();
	}
	
	public void paint(Graphics g){
		g.drawImage(gameImage, 0, 0, this);
	}
}
