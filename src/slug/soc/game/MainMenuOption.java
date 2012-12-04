package slug.soc.game;

public abstract class MainMenuOption {

	public String desc;
	
	public MainMenuOption(String desc){
		this.desc = desc;
	}
	
	public abstract void act();
	
	public String getDesc(){
		return desc;
	}
}
