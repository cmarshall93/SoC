package slug.soc.game.gameObjects;

import java.util.ArrayList;

import slug.soc.game.ColorFactory;
import slug.soc.game.FactionColor;
import slug.soc.game.HouseSigilGenerator;

public class Faction {

	private FactionColor factionColor;
	private String sigil;
	private ArrayList<GameObject> holdings;
	
	public Faction(){
		factionColor = ColorFactory.getInstance().getRandomFactionColor();
		sigil = HouseSigilGenerator.getInstance().createNewSigilString(factionColor);
		holdings = new ArrayList<GameObject>();
		holdings.add(new GameObjectHoldfast(factionColor.getColor(), this));
		holdings.add(new GameObjectCastle(factionColor.getColor(), this));
		holdings.add(new GameObjectTown(factionColor.getColor(), this));
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
}
