package entity.weapon;

import entity.AirStrikeBeacon;
import level.BasicLevel;
import sound.SoundEffect;

public class Airstrike implements Weapon {

  int ammo = 0;
  int cooldownTime = 90;
  int currentCooldown = 0;

  @Override
  public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
    if (currentCooldown <= 0 && ammo > 0) {
      level.addEntity(new AirStrikeBeacon(x, y, level, speedPercent, angle));
      currentCooldown = cooldownTime;
      SoundEffect.SHOOT.play();
      ammo--;

    }
  }

  @Override
  public void addAmmo() {
    ammo += 3;

  }

  @Override
  public int getAmmo() {
    return ammo;
  }

  @Override
  public void setAmmo() {
    ammo = 0;
  }

  @Override
  public void tick(double dt) {
    if (currentCooldown > 0) {
      currentCooldown -= dt;
    }
  }


}
