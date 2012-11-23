package slug.soc.game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FontProvider {

	private static FontProvider instance;

	private Font gameFont;

	private FontProvider(){
		/*FileInputStream fontFile;
		try {
			fontFile = new FileInputStream("Ubuntu-M.ttf");
			gameFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			gameFont.deriveFont(19);
		} catch (Exception e) {
			System.out.println("failed to load font");*/
			gameFont = new Font("Monospaced", Font.PLAIN,19);
		//}


	}

	public static FontProvider getInstance(){
		if(instance == null){
			instance = new FontProvider();
		}
		return instance;
	}

	public Font getFont(){
		return gameFont;
	}

}
