package slug.soc.game;

import slug.soc.game.gameState.IGameState;
import slug.soc.game.gameState.MainMenuState;

public class Game {
	
	private static Game instance;
	
	private IGameState currentGame;
	
	public static Game getInstance(){
		if(instance == null){
			instance = new Game();
		}
		return instance;
	}
	
	private Game(){
		currentGame = MainMenuState.getInstance();
	}
	
	public void setCurrentGameState(IGameState gameState){
		currentGame = gameState;
	}
	
	public IGameState getCurrentGameState(){
		return currentGame;
	}

}
