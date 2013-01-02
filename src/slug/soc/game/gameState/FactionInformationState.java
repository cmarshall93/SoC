package slug.soc.game.gameState;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import slug.soc.game.FontProvider;
import slug.soc.game.Game;
import slug.soc.game.gameObjects.Faction;

public class FactionInformationState implements IGameState {
	
	private static FactionInformationState instance;
	
	private Faction faction;
	
	private FactionInformationState(){
		
	}
	
	public static FactionInformationState getInstance(){
		if(instance == null){
			instance = new FactionInformationState();
		}
		return instance;
	}

	public void setFactionToDispaly(Faction faction){
		this.faction = faction;
	}
	
	@Override
	public Image createImage() {
		int gx = 20;
		int gy = 30;
		Image gameImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
		Graphics g = gameImage.getGraphics();
		g.setFont(FontProvider.getInstance().getFont().deriveFont(27f));
		g.drawString("The Great House Of " + faction.toString(), gx, gy);
		g.setFont(FontProvider.getInstance().getFont());
		gy += 30;
		String out = "The great house of " + faction.toString() + " flys " + faction.getSigil().toLowerCase();
		g.drawString(out, gx, gy);
		gy += 20;
		g.drawString("as their sigil.", gx, gy);
		return gameImage;
	}

	@Override
	public void processKey(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Game.getInstance().setCurrentGameState(GameModeState.getInstance());
		}
	}

}
