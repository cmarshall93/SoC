package slug.soc.game.gameObjects;

import slug.soc.game.gameObjects.tiles.faction.TileCursor;

public class GameObjectCursor extends GameObject {

	public GameObjectCursor() {
		super(new TileCursor(), null);
	}
	
	public String[] getStringDesc(){
		String desc[] = new String[1];
		desc[0] = "";
		return desc;
	}
	
	public String toString(){
		return "";
	}

}
