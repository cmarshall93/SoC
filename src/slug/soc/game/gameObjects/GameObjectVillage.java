package slug.soc.game.gameObjects;

import java.awt.Color;
import slug.soc.game.gameObjects.tiles.faction.TileVillage;

public class GameObjectVillage extends GameObject {

	public GameObjectVillage(Color color, Faction owner) {
		super(new TileVillage(color), owner);
	}
	
	public String[] getStringDesc(){
		String[] desc = new String[1];
		desc[0] = "Property of " + owner.toString();
		return desc;
	}
	
	public String toString(){
		return "Village";
	}

}
