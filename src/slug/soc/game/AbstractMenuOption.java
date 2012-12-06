package slug.soc.game;

public abstract class AbstractMenuOption {

	public String desc;
	
	public AbstractMenuOption(String desc){
		this.desc = desc;
	}
	
	public abstract void act();
	
	public String getDesc(){
		return desc;
	}
}
