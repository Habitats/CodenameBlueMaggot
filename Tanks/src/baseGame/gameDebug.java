package baseGame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class gameDebug extends JFrame {
	public gameDebug(){
		setVisible(true);
		testGame game = new testGame();
		add(game);
		this.setPreferredSize(new Dimension(800,600));
		pack();
		
		game.setVisible(true);
		game.init(60);
		
	}
	public static void main(String[] args) {
		new gameDebug();
	}

}