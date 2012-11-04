package slug.soc.game.gameObjects.tiles;

import java.awt.Color;

public abstract class GameTile {

	private Character symbol;
	private Color color;
	
	public GameTile(Character symbol, Color color){
		this.symbol = symbol;
		this.color = color;
	}
	
	public Character getSymbol(){
		return symbol;
	}
	
	public Color getColor(){
		return color;
	}
}
