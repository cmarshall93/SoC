package slug.soc.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import slug.soc.game.Game;
import slug.soc.view.GameView;

/**
 * 
 * @author slug
 * 
 */


public class GameEngine {

	private GameView gameview;
	private Timer renderTimer;
	private boolean running;

	public GameEngine(GameView view){
		gameview = view;
		gameview.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				Game.getInstance().getCurrentGameState().processKey(arg0);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}});
	}
	/**
	 * Adds the renderTask to the renderTimer, which draws the game every 15ms.
	 */
	public void startGame(){
		running = true;
		renderTimer = new Timer();
		renderTimer.schedule(new RenderTask(), 7, 7);
		while(running){
		
		}
	}
	/**
	 * Calls the drawGameImage method in the gameView, passing the image generated from the renderGameImage method in the renderEngine.
	 */
	private void renderGame(){
		gameview.drawGameImage(Game.getInstance().getCurrentGameState().createImage());
	}
	
	private class RenderTask extends TimerTask{
		public void run() {
			renderGame();
			}
	}
}


