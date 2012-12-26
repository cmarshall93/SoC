package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.faction.TileTown;

public class GameObjectTown extends GameObject {

	public GameObjectTown(Color color, Faction owner) {
		super(new TileTown(color), owner);
	}

}
