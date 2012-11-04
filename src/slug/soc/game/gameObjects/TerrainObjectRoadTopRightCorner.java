package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.roadAndRiver.TileTopRightCornerLine;

public class TerrainObjectRoadTopRightCorner extends TerrainObject {

	public TerrainObjectRoadTopRightCorner() {
		super(new TileTopRightCornerLine(new Color(99,33,00)));
	}

	public String toString(){
		return "Road";
	}
}
