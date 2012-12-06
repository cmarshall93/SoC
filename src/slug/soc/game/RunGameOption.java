package slug.soc.game;

public class RunGameOption extends AbstractMenuOption {

	public RunGameOption(){
		super("Start Game");
	}
	
	@Override
	public void act() {
		Game.getInstance().setCurrentGameState(GameModeState.getInstance());
	}

}
