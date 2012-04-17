package gfx;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import baseGame.BaseGame;
import baseGame.testGame;

public class MenuPanel extends Frame {
	
	public MenuPanel(){
		setVisible(true);
		BaseGame t = new testGame();
		t.setVisible(true);
		add(t);
		validate();
		t.init(60);
	}
	public static void main(String [] args){
		
		new MenuPanel();
		
	}
	
}
