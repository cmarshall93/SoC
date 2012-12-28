package slug.soc.game.gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

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
	public void createImage() {
		/**
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
		}*/
		
		try {
			Texture texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("test.jpg"), true);
			texture.bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 

		GL11.glColor3f(0.5f,0.5f,1.0f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(100,100);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(100+16,100);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(100+16,100+16);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(100,100+16);
	}
}
