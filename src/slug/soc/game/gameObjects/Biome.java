package slug.soc.game.gameObjects;

import java.util.ArrayList;

public class Biome {
	
	private ArrayList<TerrainObject> contents;
	private String name;
	
	public Biome(String name){
		contents = new ArrayList<TerrainObject>();
		this.name = name;
	}
	
	public void addTerrianObject(TerrainObject o){
		contents.add(o);
	}
	
	public ArrayList<TerrainObject> getContents(){
		return contents;
	}
	
	public int getSize(){
		return contents.size();
	}
	
	public String toString(){
		return name + " ";
	}

}
