package gfx;

import gfx.GBC.Align;

import java.awt.Dimension;
import blueMaggot.Game;
import blueMaggot.GameState;

public class MenuOptionsLan extends Menu {
	private Dimension btnSize = new Dimension(200, 20);

	private MenuField fieldPlayerTwo;
	private MenuField fieldLanNick;
	private MenuField fieldIp;

	private MenuButton btnStartLan;
	private MenuButton btnReturn;

	private MenuLabel playerOne;
	private MenuLabel playerTwo;
	private MenuLabel connectIp;
	private MenuLabel isHost;

	private MenuCheckBox boxIsHost;

	public MenuOptionsLan(Game game) {
		super();

		setVisible(false);

		fieldIp = new MenuField(20, "ip");
		fieldLanNick = new MenuField(20, "text");
		fieldPlayerTwo = new MenuField(20, "text");

		btnStartLan = new MenuButton(Labels.START_LAN_GAME, this, game, btnSize);
		btnReturn = new MenuButton(Labels.RETURN, this, game, btnSize);

		playerOne = new MenuLabel(Labels.LAN_NICK);
		connectIp = new MenuLabel(Labels.CONNECT_IP);
		isHost = new MenuLabel(Labels.IS_HOST);

		boxIsHost = new MenuCheckBox();

		// left column
		add(playerOne, new GBC(0, 0, Align.RIGHT));
		add(connectIp, new GBC(0, 2, Align.RIGHT));
		add(isHost, new GBC(0, 3, Align.RIGHT));
		add(boxIsHost, new GBC(1, 3, Align.RIGHT));

		// right column
		add(fieldLanNick, new GBC(1, 0, Align.LEFT));
		add(fieldIp, new GBC(1, 2, Align.LEFT));
		add(btnStartLan, new GBC(0, 4, Align.RIGHT));
		add(btnReturn, new GBC(1, 4, Align.LEFT));
	}

	public void apply(Game game) {
		GameState.getInstance().setHost(boxIsHost.getState());
		/*if (fieldLanNick.msg != null)
			if (GameState.getInstance().isHost())
				GameState.getInstance().getPlayers().get(0).setNick(fieldLanNick.msg);
			else
				GameState.getInstance().getPlayers().get(1).setNick(fieldLanNick.msg);*/

		GameState.getInstance().hostIp = fieldIp.msg;
		// game.blueMaggot.ui.repaint();
	}
}