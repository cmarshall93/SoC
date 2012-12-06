package slug.soc.game;

public class ExitProgramOption extends AbstractMenuOption {

	public ExitProgramOption(){
		super("Exit");
	}

	@Override
	public void act() {
		System.exit(0);
	}
	
}
