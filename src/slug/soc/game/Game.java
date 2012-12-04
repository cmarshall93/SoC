package slug.soc.game;

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
		currentGame = new MainMenuState();
	}
	
	public void setCurrentGameState(IGameState gameState){
		currentGame = gameState;
	}
	
	public IGameState getCurrentGameState(){
		return currentGame;
	}

}
