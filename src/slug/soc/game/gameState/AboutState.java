package slug.soc.game.gameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import slug.soc.game.FontProvider;
import slug.soc.game.Game;

public class AboutState implements IGameState {

	private static AboutState instance;

	private static final String text = "Songs Of Conquest is an ascii tile based game written entirely by me (Charlie Marshall)." +
										"\nThink of it as Medieval Total War crossed with the politics/backstabbing/drama from " +
										"\nGame Of Thrones." ;

	public static AboutState getInstance(){
		if(instance == null){
			instance = new AboutState();
		}
		return instance;
	}

	private AboutState(){

	}

	@Override
	public void processKey(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Game.getInstance().setCurrentGameState(MainMenuState.getInstance());
		}
	}

	@Override
	public Image createImage() {
		Image gameImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
		Graphics g = gameImage.getGraphics();
		int gx = 30;
		int gy = 30;
		g.setFont(FontProvider.getInstance().getFont());
		for(String line: text.split("\n")){
			g.drawString(line, gx, gy += g.getFontMetrics().getHeight());
		}
		return gameImage;
	}
}
