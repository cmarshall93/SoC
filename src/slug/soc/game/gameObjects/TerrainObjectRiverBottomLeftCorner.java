package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.GameTile;
import slug.soc.game.gameObjects.tiles.roadAndRiver.TileBottomLeftCornerLine;

public class TerrainObjectRiverBottomLeftCorner extends TerrainObject {

	public TerrainObjectRiverBottomLeftCorner() {
		super(new TileBottomLeftCornerLine(Color.BLUE));
	}

	public String toString(){
		return "River";
	}
}
