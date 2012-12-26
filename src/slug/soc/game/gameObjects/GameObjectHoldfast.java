package slug.soc.game.gameObjects;

import java.awt.Color;
import slug.soc.game.gameObjects.tiles.faction.TileHoldfast;

public class GameObjectHoldfast extends GameObject {

	public GameObjectHoldfast(Color color, Faction owner) {
		super(new TileHoldfast(color), owner);
	}

}
