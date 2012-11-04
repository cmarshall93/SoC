package slug.soc.game;

public class Game {
	
	private IGameState currentGame;
	
	public Game(){
		currentGame = new GameModeState();
	}
	
	public IGameState getCurrentGameState(){
		return currentGame;
	}

}
