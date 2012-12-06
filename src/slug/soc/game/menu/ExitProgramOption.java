package slug.soc.game.menu;

public class ExitProgramOption extends AbstractMenuOption {

	public ExitProgramOption(){
		super("Exit(Close program)");
	}

	@Override
	public void act() {
		System.exit(0);
	}
	
}
