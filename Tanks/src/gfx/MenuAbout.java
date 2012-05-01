package gfx;

import java.awt.Dimension;

import gfx.GBC.Align;
import gfx.Labels;

import blueMaggot.Game;

public class MenuAbout extends Menu {

	int x, y;

	public MenuAbout(Game game) {

		setVisible(false);
		add(new MenuLabel(Labels.WELCOME), new GBC(x, y, Align.MID));
		add(new MenuLabel(Labels.ABOUT_MSG ), new GBC(x, ++y, Align.MID));
		add(new MenuButton(Labels.RETURN, this, game), new GBC(x, ++y, Align.MID).setIpad(150, 0));
	}
}
