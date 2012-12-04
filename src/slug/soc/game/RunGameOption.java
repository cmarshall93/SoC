package slug.soc.game;

public class RunGameOption extends MainMenuOption {

	public RunGameOption(){
		super("Start Game");
	}
	
	@Override
	public void act() {
		Game.getInstance().setCurrentGameState(new GameModeState());
	}

}
