package gfx;

import blueMaggot.GameState;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Panel;
import javax.swing.JLayeredPane;

public abstract class Menu extends Panel {

	protected Panel buttonPanel;
	protected JLayeredPane layeredPane;

	protected Color menuBg;

	protected int width = 490;
	protected int height = 304;
	public int border = 5;

	public static Color green = new Color(137, 255, 172);
	public static Color blue = new Color(153, 210, 228);
	public static Color blueDark = new Color(108, 209, 234);
	public static Color pink = new Color(255, 212, 218);
	public static Color pinkDark = new Color(254, 186, 197);

	public Menu() {
		putCenter(this, width, height);
		setLayout(new GridBagLayout());
		setBackground(Menu.blue);
	}

	public void putCenter(Component c, int width, int height) {
		c.setBounds(GameState.getInstance().width / 2 - width / 2, GameState.getInstance().height / 2 - height / 2, width, height);
	}
}
