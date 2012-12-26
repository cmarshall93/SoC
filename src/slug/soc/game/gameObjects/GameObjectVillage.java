package slug.soc.game.gameObjects;

import java.awt.Color;
import slug.soc.game.gameObjects.tiles.faction.TileVillage;

public class GameObjectVillage extends GameObject {

	public GameObjectVillage(Color color, Faction owner) {
		super(new TileVillage(color), owner);
	}

}
