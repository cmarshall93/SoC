package slug.soc.game.gameObjects;

import java.util.ArrayList;

import slug.soc.game.ColorFactory;
import slug.soc.game.FactionColor;
import slug.soc.game.HouseSigilGenerator;
import slug.soc.game.WordGenerator;

public class Faction {

	private FactionColor factionColor;
	private String sigil;
	private ArrayList<GameObject> holdings;
	private String name;
	
	public Faction(){
		factionColor = ColorFactory.getInstance().getRandomFactionColor();
		sigil = HouseSigilGenerator.getInstance().createNewSigilString(factionColor);
		name = WordGenerator.getInstance().getRandomFactionName();
		
		holdings = new ArrayList<GameObject>();
		holdings.add(new GameObjectCastle(factionColor.getColor(), this));
		holdings.add(new GameObjectPerson(factionColor.getColor(), this));
	}
	
	public ArrayList<GameObject> getHoldings(){
		return holdings;
	}
	
	public String getSigil(){
		return sigil;
	}
	
	public FactionColor getFactionColor(){
		return factionColor;
	}
	
	public String toString(){
		return name;
	}
}
