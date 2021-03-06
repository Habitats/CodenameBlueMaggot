package entity.weapon;

import entity.Rocket;
import level.BasicLevel;
import sound.SoundEffect;

public class Rocketlauncher implements Weapon {

  int cooldownTime = 120;
  int currentCooldown = 0;
  private int ammo = 0;

  @Override
  public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
    if (currentCooldown <= 0 && ammo > 0) {
      level.addEntity(new Rocket(x, y, level, 0.1, angle));
      currentCooldown = cooldownTime;
      ammo--;
      SoundEffect.ROCKETLAUNCH.play();
    }
  }

  @Override
  public void setAmmo() {
    ammo = 0;
  }

  @Override
  public int getAmmo() {
    return ammo;
  }

  @Override
  public void addAmmo() {
    ammo += 4;
  }

  @Override
  public void tick(double dt) {
    if (currentCooldown > 0) {
      currentCooldown -= dt;
    }
  }

}
