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
	private Faction owner;

	public TerrainObject(GameTile tile){
		baseTile = tile;	
		currentTile = baseTile;
		tileIndex = 0;
		isBiome = false;
		gameObjects = new ArrayList<GameObject>();
		owner = null;
	}

	public GameTile getTile(){
		return currentTile;
	}

	public void addGameObject(GameObject o){
		if(owner == null){
			gameObjects.add(o);
			if(o.getOwner() != null){
				owner = o.getOwner();
			}
		}
		else if(owner == o.getOwner() || o.getOwner() == null){
			gameObjects.add(o);
		}
	}

	public void removeGameObject(GameObject o){
		gameObjects.remove(o);
		if(gameObjects.size() == 0 && o.getOwner() != null){
			owner = null;
		}
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

	public void setOwner(Faction owner){
		if(this.owner == null){
			this.owner = owner;
		}
	}

	public Faction getOwner(){
		return owner;
	}
}
