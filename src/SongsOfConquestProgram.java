
import java.awt.FlowLayout;

import javax.swing.JFrame;
import slug.soc.engine.GameEngine;
import slug.soc.view.GameView;

public class SongsOfConquestProgram {

	public static void main(String[] args) {
		
		GameView view = new GameView();
		GameEngine eng = new GameEngine(view);
		
		JFrame jf = new JFrame("Songs Of Conquest");
		jf.setLayout(new FlowLayout());
		jf.add(view);
		view.setFocusable(true);
		jf.setSize(view.getPreferredSize());
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		
		eng.startGame();
	}

}