package slug.soc.game.gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import slug.soc.game.FontProvider;
import slug.soc.game.menu.AbstractMenuOption;
import slug.soc.game.menu.ExitProgramOption;
import slug.soc.game.menu.AboutOption;
import slug.soc.game.menu.RunGameOption;

public class MainMenuState implements IGameState {

	private static MainMenuState instance;
	
	private AbstractMenuOption[] options;
	private int currentOption;
	
	public static MainMenuState getInstance(){
		if(instance == null){
			instance = new MainMenuState();
		}
		return instance;
	}
	
	private MainMenuState(){
		options = new AbstractMenuOption[3];
		options[0] = new RunGameOption();
		options[1] = new AboutOption();
		options[2] = new ExitProgramOption();
		currentOption = 0;
	}
	
	@Override
	public void processKey(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			options[currentOption].act();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			nextOption();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			previousOption();
		}
	}
	
	public void nextOption(){
		if(currentOption + 1 < options.length){
			currentOption++;
		}
		else{
			currentOption = 0;
		}
	}
	
	public void previousOption(){
		if(currentOption - 1 >= 0){
			currentOption--;
		}
		else{
			currentOption = options.length - 1;
		}
	}
	
	@Override
	public Image createImage() {
		Image gameImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
		Graphics g = gameImage.getGraphics();
		int gx = 30;
		int gy = 30;
		g.setFont(FontProvider.getInstance().getFont());
		for(int i = 0;i < options.length; i++){
			g.setColor(Color.GRAY);
			if(i == currentOption){
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i].getDesc(), gx, gy);
			gy += 30;
		}
		return gameImage;
	}
}
