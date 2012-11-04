package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.faction.TilePerson;

public class GameObjectArmy extends GameObject {

	public GameObjectArmy(Color color) {
		super(new TilePerson(color));
	}

}
