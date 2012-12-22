package slug.soc.game;

public class HouseSigilGenerator {

	private static HouseSigilGenerator instance;
	
	private HouseSigilGenerator(){
		
	}
	
	public static HouseSigilGenerator getInstance(){
		if(instance == null){
			instance = new HouseSigilGenerator();
		}
		return instance;
	}
	
	public String createNewSigilString(){
		String sigil = "A ";
		sigil += WordGenerator.getInstance().getRandomAdjective().toLowerCase() + " ";
		sigil += WordGenerator.getInstance().getRandomNoun().toLowerCase() + " ";
		sigil += "on a ";
		sigil += ColorFactory.getInstance().getNextFactionColor().toString()+ " ";
		sigil += "background";
		return sigil;
	}
	
}
