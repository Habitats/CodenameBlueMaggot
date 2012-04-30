package blueMaggot;

import entity.Tank;
import entity.weapon.Gun;
import gfx.Menu;
import gfx.MenuBackground;
import gfx.MenuLevelSelect;
import gfx.MenuOptions;
import gfx.MenuOptionsLan;
import gfx.MenuTitle;
import gfx.ResourceManager;
import gfx.MenuScoreBoard;
import gfx.MenuAbout;
import inputhandler.InputHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author Habitats * this motherfucker starts the game
 */
public class BlueMaggot extends JFrame implements Runnable {

	public InputHandler inputReal = new InputHandler();

	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel gamePanel;

	public MenuScoreBoard uiScoreBoard;
	public MenuLevelSelect menuLevelSelect;
	public MenuOptions menuOptions;
	public MenuOptionsLan menuOptionsLan;
	public MenuAbout menuAbout;
	public MenuTitle menuTitle;

	Game game;

	private MenuBackground menuBackground;



	public BlueMaggot() {

		ResourceManager.getInstance().initResources();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPreferredSize(new Dimension(GameState.getInstance().getWidth(), GameState.getInstance().getHeight() + 28));
		setFocusable(true);
		setResizable(false);
		
		layeredPane.setBounds(0, 0, GameState.getInstance().getWidth(), GameState.getInstance().getHeight());
		
		layeredPane.setOpaque(false);

		game = new blueMaggot.Game(this);

		menuTitle = new MenuTitle(game, this);
		menuOptions = new MenuOptions(game);
		menuOptionsLan = new MenuOptionsLan(game);
		menuAbout = new MenuAbout(game);
		uiScoreBoard = new MenuScoreBoard(game);
		menuLevelSelect = new MenuLevelSelect(game);
		menuBackground = new MenuBackground(menuTitle);
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.DARK_GRAY);

		layeredPane.add(gamePanel, new Integer(0));
		layeredPane.add(menuBackground, new Integer(9));
		layeredPane.add(menuTitle, new Integer(10));
		layeredPane.add(menuOptions, new Integer(11));
		layeredPane.add(menuOptionsLan, new Integer(11));
		layeredPane.add(menuLevelSelect, new Integer(11));
		layeredPane.add(menuAbout, new Integer(11));
		layeredPane.add(uiScoreBoard, new Integer(12));

		add(layeredPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
	}

	private void setUpGame() {
		game.setPreferredSize(GameState.getInstance().dimension);
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBounds(0, 0, GameState.getInstance().getWidth(), GameState.getInstance().getHeight());

		gamePanel.add(game);
	}

	@Override
	public void run() {
		setUpGame();
	}

	public static void main(String[] args) {
		(new BlueMaggot()).run();
	}

	public void tick() {
		for (Tank tank : GameState.getInstance().getPlayers()) {
			if (tank.getNick() == null)
				tank.setNick("Player");
		}

		if (inputReal.menu.clicked) {
			inputReal.menu.clicked = false;
			inputReal.releaseAll();
			if (!menuTitle.isVisible()) {
				menuTitle.setVisible(true);
				menuBackground.setVisible(true);
				menuTitle.repaint();
				GameState.getInstance().setPaused(true);
			}
		}

		// TODO: Implement scoreboard
		if (GameState.getInstance().isGameOver()) {
			menuTitle.setVisible(true);
			uiScoreBoard.setVisible(true);
			GameState.getInstance().setPaused(true);
			GameState.getInstance().setRunning(false);
			menuBackground.setVisible(true);
			menuTitle.repaint();
		}

		for (Tank tank : GameState.getInstance().getPlayers()) {
			if (tank.getScore() != tank.getOldScore()) {
				tank.setOldScore(tank.getScore());

			}
		}

	}

	public void initResources() {
//		try {
//			ResourceManager.TANK1 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
//			ResourceManager.TANK2 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
//			ResourceManager.TANK3 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
//			ResourceManager.TANK4 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Tank2.png")));
//			ResourceManager.SHELL = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Shell_temp.png")));
//			ResourceManager.SCOREBUBBLE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Scorebubble.png")));
//			ResourceManager.CROSSHAIR1 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
//			ResourceManager.CROSSHAIR2 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
//			ResourceManager.CROSSHAIR3 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
//			ResourceManager.CROSSHAIR4 = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Crosshair.png")));
//			ResourceManager.ROCKET = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Rocket_sheet.png")));
//			ResourceManager.MINE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Mine_sheet.png")));
//			ResourceManager.GRENADE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Grenade_temp.png")));
//			ResourceManager.PACKAGE = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Package.png")));
//			ResourceManager.BUBBLEHEARTH = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/BubbleHearth.png")));
//			ResourceManager.AIRSTRIKEBEACON = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/AirStrikeBeacon.png")));
//			ResourceManager.BULLET = new RGBImage(ImageIO.read(getClass().getResourceAsStream("/graphics/Bullet.png")));
//			ResourceManager.COLORMASK = new Color(0x00FAE1);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
