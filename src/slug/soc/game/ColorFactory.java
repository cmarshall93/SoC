package slug.soc.game;

import java.awt.Color;
import java.util.ArrayList;

public class ColorFactory {

	private static ColorFactory instance = null;
	
	private int factionColorsIndex = 0;
	private Color[] factionColors = {
			Color.RED,
			Color.CYAN,
			Color.YELLOW,
			Color.GRAY,
			Color.MAGENTA,
			new Color(124,252,0)
	};

	protected ColorFactory(){
	}

	public static ColorFactory getInstance(){
		if(instance == null){
			instance = new ColorFactory();
		}
		return instance;
	}	

	private Color getNextFactionColor(){
		if(factionColorsIndex == factionColors.length){
			factionColorsIndex = 0;
		}
		factionColorsIndex += 1;
		return factionColors[factionColorsIndex - 1];
	}
}
