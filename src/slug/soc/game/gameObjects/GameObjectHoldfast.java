package slug.soc.game.gameObjects;

import java.awt.Color;
import slug.soc.game.gameObjects.tiles.faction.TileHoldfast;

public class GameObjectHoldfast extends GameObject {

	public GameObjectHoldfast(Color color, Faction owner) {
		super(new TileHoldfast(color), owner);
	}

	public String[] getStringDesc(){
		String[] desc = new String[1];
		desc[0] = "Property of " + owner.toString();
		return desc;
	}
	
	public String toString(){
		return "Holdfast";
	}
}
