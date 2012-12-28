package slug.soc.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import slug.soc.game.Game;
import slug.soc.game.gameState.MainMenuState;
import slug.soc.view.GameView;

/**
 * 
 * @author slug
 * 
 */


public class GameEngine {

	public GameEngine(){
		
	}
	/**
	 * Adds the renderTask to the renderTimer, which draws the game every 15ms.
	 */
	public void startGame(){
		Game.getInstance().setCurrentGameState(MainMenuState.getInstance());
	}
	/**
	 * Calls the drawGameImage method in the gameView, passing the image generated from the renderGameImage method in the renderEngine.
	 */
	public void renderGame(){
		Game.getInstance().getCurrentGameState().createImage();
	}
}


