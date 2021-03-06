package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuLabel extends Label {

  private Labels label;
  private BufferedImage lblImage;

  public MenuLabel(Labels logo) {
    this.label = logo;
    setPreferredSize(new Dimension(270, 13));
    setUp();
  }

  public MenuLabel(Labels label, Dimension size) {
    this.label = label;
    setMaximumSize(size);
    setMinimumSize(size);
    setPreferredSize(size);
    setUp();
  }

  public void setUp() {
    String img = "/titleMenu/" + label + ".png";
    try {
      lblImage = ImageIO.read(getClass().getResourceAsStream(img));
      setPreferredSize(new Dimension(lblImage.getWidth(), lblImage.getHeight()));
      setMinimumSize(new Dimension(lblImage.getWidth(), lblImage.getHeight()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // setText(label.toString());
    setBackground(Menu.blue);
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Menu.blue);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.drawImage(lblImage, 0, (getHeight() - lblImage.getHeight()) / 2, null);
  }

}
