package slug.soc.game.gameObjects;

import slug.soc.game.gameObjects.tiles.terrian.TileGrassPlain;

public class TerrainObjectGrassPlain extends TerrainObject {

	public TerrainObjectGrassPlain() {
		super(new TileGrassPlain());
	}

	public String toString(){
		return ("The " + getBiomeString()+ "Plains");
	}
}
