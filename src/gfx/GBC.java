package gfx;

import java.awt.*;

public class GBC extends GridBagConstraints {

  public enum Align {
    LEFT, RIGHT, MID, TIGHT;
  }

  public GBC(int gridx, int gridy, Align align) {
    this.gridx = gridx;
    this.gridy = gridy;
    if (align != null) {
      if (align == Align.LEFT) {
        setInsets(5, 5, 5, 60);
      } else if (align == Align.MID) {
        setInsets(5, 5, 5, 5);
      } else if (align == Align.RIGHT) {
        setInsets(5, 60, 5, 5);
      } else if (align == Align.TIGHT) {
        setInsets(3, 5, 3, 5);
      }
    }
    setFill(HORIZONTAL);
  }

  public GBC setSpan(int gridwidth, int gridheight) {
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
    return this;
  }

  public GBC setFill(int fill) {
    this.fill = fill;
    return this;
  }

  public GBC setWeight(double weightx, double weighty) {
    this.weightx = weightx;
    this.weighty = weighty;
    return this;
  }

  public GBC setInsets(int top, int left, int bottom, int right) {
    this.insets = new Insets(top, left, bottom, right);
    return this;
  }

  public GBC setIpad(int ipadx, int ipady) {
    this.ipadx = ipadx;
    this.ipady = ipady;
    return this;
  }
}
