package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.faction.TileTown;

public class GameObjectTown extends GameObject {

	public GameObjectTown(Color color, Faction owner) {
		super(new TileTown(color), owner);
	}
	
	public String[] getStringDesc(){
		String[] desc = new String[1];
		desc[0] = "Property of the " + owner.toString() + " family";
		return desc;
	}
	
	public String toString(){
		return "Town";
	}

}
