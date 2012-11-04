package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.GameTile;

/**
 * 
 * @author slug
 *
 */
public abstract class GameObject implements GameDrawable {

	private GameTile tile;
	
	public GameObject(GameTile tile){
		this.tile = tile;	
	}
	
	public GameTile getTile(){
		return tile;
	}
}

