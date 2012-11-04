package slug.soc.game;

import java.awt.Image;
import java.awt.event.KeyEvent;

public interface IGameState {

	public Image createImage();
	
	public void processKey(KeyEvent e);
	
}
