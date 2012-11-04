package slug.soc.game.gameObjects;

import java.awt.Color;
import java.util.ArrayList;

import slug.soc.game.gameObjects.tiles.GameTile;

/**
 * 
 * @author slug
 *
 */
public abstract class TerrainObject implements GameDrawable{

	private int tileIndex;
	private GameTile baseTile;
	private GameTile currentTile;
	private ArrayList<GameObject> gameObjects;
	private boolean isBiome;
	private String biomeString;

	public TerrainObject(GameTile tile){
		baseTile = tile;	
		currentTile = baseTile;
		tileIndex = 0;
		isBiome = false;
		gameObjects = new ArrayList<GameObject>();
	}

	public GameTile getTile(){
		return currentTile;
	}

	public void addGameObject(GameObject o){
		gameObjects.add(o);
	}
	
	public void removeGameObject(GameObject o){
		gameObjects.remove(o);
	}

	public void nextTile(){
		if(tileIndex < gameObjects.size()){
			currentTile = gameObjects.get(tileIndex).getTile();
			tileIndex++;
		}
		else{
			tileIndex = 0;
			currentTile = baseTile;
		}
	}

	public boolean isBiome(){
		return isBiome;
	}
	
	public void setBiomeString(String string){
		biomeString = string;
		isBiome = true;
	}

	public String getBiomeString(){
		if(biomeString != null){
			return biomeString;
		}
		return "";
	}
}
