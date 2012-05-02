package gfx;

import blueMaggot.GameState;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JLayeredPane;

public abstract class Menu extends Panel {

	protected Panel buttonPanel;
	protected JLayeredPane layeredPane;

	protected Color menuBg;

	protected int width = 490;
	protected int height = 304;
	public int border = 5;

	public static Font font;
	public static Color green = new Color(137, 255, 172);
	public static Color blue = new Color(153, 210, 228);
	public static Color blueDark = new Color(108, 209, 234);
	public static Color pink = new Color(255, 212, 218);
	public static Color pinkDark = new Color(254, 186, 197);

	public Menu() {
		putCenter(this, width, height);
		setLayout(new GridBagLayout());
		setBackground(Menu.blue);
		if (font == null) {
			try {
				InputStream is = getClass().getResourceAsStream("/res/8-BitCustom.TTF");
				File tmpFile = new File("test.TTF");
				OutputStream os = new FileOutputStream(tmpFile);
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = is.read()) != -1)
					os.write(buffer, 0, bytesRead);
				os.close();
				is.close();
				// font = Font.createFont(Font.TRUETYPE_FONT,
				// getClass().getResourceAsStream("/res/8-BitCustom.TTF"));
				font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(tmpFile));
				font = font.deriveFont(15.0F);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void putCenter(Component c, int width, int height) {
		c.setBounds(GameState.getInstance().getWidth() / 2 - width / 2, GameState.getInstance().getHeight() / 2 - height / 2, width, height);
	}
}
