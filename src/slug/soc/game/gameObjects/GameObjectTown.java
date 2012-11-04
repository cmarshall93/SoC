package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.faction.TileTown;

public class GameObjectTown extends GameObject {

	public GameObjectTown(Color color) {
		super(new TileTown(color));
	}

}
