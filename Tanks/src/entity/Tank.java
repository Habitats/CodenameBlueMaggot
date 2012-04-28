package entity;

import java.util.ArrayList;

import networking.*;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

import entity.weapon.Airstrike;
import entity.weapon.GrenadeGun;
import entity.weapon.MineLauncher;
import entity.weapon.Minigun;
import entity.weapon.Rocketlauncher;
import entity.weapon.ShellGun;
import entity.weapon.Weapon;
import gfx.ResourceManager;

import level.BasicLevel;
import inputhandler.InputHandler;

public class Tank extends Entity {

	private InputHandler input;
	private double muzzleAngle;
	private int muzzleLength = 16;
	private int score = 0;
	private int life = 5;

	private int playerNumber;
	private double jetPackFuel = 100;
	private double cannonCharge = 0;
	private boolean canGoLeft = true;
	private boolean canGoRight = true;
	private boolean canGoDown = true;
	private boolean chargingCannon = false;
	private int currentWeapon = 0;
	private double torque = 0.15;

	private ArrayList<Weapon> weaponList;
	private PixelHitbox boxUnderCenter;
	private PixelHitbox boxLeft;
	private PixelHitbox boxRight;
	private PixelHitbox boxUp;

	public Tank(double x, double y, int playerNumber, InputHandler input, BasicLevel level) {
		super(x, y, 11, 6, level);
		this.level.getPlayers().add(this);
		muzzleAngle = 0;
		muzzleLength = 20;
		this.playerNumber = playerNumber;
		this.input = input;
		frictionConstant = 0.12;
		initInventory();

		boxUnderCenter = new PixelHitbox();
		boxUnderCenter.addPoint(new FloatingPoint(-2, yr));
		boxUnderCenter.addPoint(new FloatingPoint(-1, yr));
		boxUnderCenter.addPoint(new FloatingPoint(0, yr));
		boxUnderCenter.addPoint(new FloatingPoint(1, yr));
		boxUnderCenter.addPoint(new FloatingPoint(2, yr));

		boxLeft = new PixelHitbox();
		boxRight = new PixelHitbox();
		boxUp = new PixelHitbox();
		boxUp.addPoint(new FloatingPoint(-2, -yr + 2));
		boxUp.addPoint(new FloatingPoint(+2, -yr + 2));
		for (int i = (int) -yr + 2; i < -2; i++) {
			boxLeft.addPoint(new FloatingPoint(-xr, i));
		}
		for (int ii = (int) -yr + 2; ii < -2; ii++) {
			boxRight.addPoint(new FloatingPoint(xr, ii));
		}

		/*
		 * for(int ii = 0;ii<yr-2;ii++){ boxLeft.addPoint(new FloatingPoint(0,
		 * ii)); }
		 */

	}

	public Tank(FloatingPoint point, int playerNumber, InputHandler input, BasicLevel level) {
		super(point.getX(), point.getY(), 11, 6, level);
		this.level.getPlayers().add(this);
		muzzleAngle = 90;
		muzzleLength = 20;
		this.playerNumber = playerNumber;
		this.input = input;
		frictionConstant = 0.12;
		initInventory();

		boxUnderCenter = new PixelHitbox();
		boxUnderCenter.addPoint(new FloatingPoint(-2, yr));
		boxUnderCenter.addPoint(new FloatingPoint(-1, yr));
		boxUnderCenter.addPoint(new FloatingPoint(0, yr));
		boxUnderCenter.addPoint(new FloatingPoint(1, yr));
		boxUnderCenter.addPoint(new FloatingPoint(2, yr));

		boxLeft = new PixelHitbox();
		boxRight = new PixelHitbox();
		boxUp = new PixelHitbox();
		boxUp.addPoint(new FloatingPoint(-2, -yr + 2));
		boxUp.addPoint(new FloatingPoint(+2, -yr + 2));
		for (int i = (int) -yr + 2; i < -2; i++) {
			boxLeft.addPoint(new FloatingPoint(-xr, i));
		}
		for (int ii = (int) -yr + 2; ii < -2; ii++) {
			boxRight.addPoint(new FloatingPoint(xr, ii));
		}

		/*
		 * for(int ii = 0;ii<yr-2;ii++){ boxLeft.addPoint(new FloatingPoint(0,
		 * ii)); }
		 */

	}

	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getCurrentWeapon() {
		return currentWeapon;
	}

	public void initInventory() {
		weaponList = new ArrayList<Weapon>();
		weaponList.add(new ShellGun());
		weaponList.add(new GrenadeGun());
		weaponList.add(new Rocketlauncher());
		weaponList.add(new MineLauncher());
		weaponList.add(new Airstrike());
		weaponList.add(new Minigun(this));
	}

	public void setMuzzleAngle(double degrees) {
		this.muzzleAngle = degrees;
	}

	public void incrementMuzzleAngle(double degrees) {
		setMuzzleAngle(this.muzzleAngle + degrees);
		muzzleAngle = ((360 + muzzleAngle) % 360);

	}

	public void addScore(int amount) {
		this.score += amount;
	}

	public int getScore() {
		System.out.println("Score: " + score);
		return score;
	}

	public FloatingPoint getCrosshairLocation() {
		return (new FloatingPoint(x + muzzleLength * Math.cos(Math.toRadians(muzzleAngle)), y - muzzleLength * Math.sin(Math.toRadians(muzzleAngle))));
	}

	public void fire(double speedPercent) {
		if (speedPercent < 0.2)
			speedPercent = 0.2;
		weaponList.get(currentWeapon).fire(this.x + muzzleLength * Math.cos(Math.toRadians(muzzleAngle)),
				this.y - muzzleLength * Math.sin(Math.toRadians(muzzleAngle)), this.level, speedPercent, this.muzzleAngle - 4);
	}

	public void handleTerrainIntersection() {
		canGoLeft = true;
		canGoRight = true;
		canGoDown = true;
		for (FloatingPoint point : boxLeft) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {

				canGoLeft = false;
				if (dx < 0)
					dx = 0;
			}
		}
		for (FloatingPoint point : boxRight) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {

				canGoRight = false;
				if (dx > 0)
					dx = 0;
			}
		}
		for (FloatingPoint point : boxUnderCenter) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y + 1))) {
				canGoDown = false;
				break;
			}
		}
		for (FloatingPoint point : boxUnderCenter) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y - 1);
					setSpeed(dx, 0);
				}
				return;
			}
		}
		for (FloatingPoint point : boxUp) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y + 1);
					setSpeed(dx, 0.3);
				}
				break;
			}
		}
	}

	public boolean intersectsTerrain() {
		if (level.getTerrain().hitTestpoint((int) x, (int) (y + yr * 2))) {
			return true;
		}
		return false;
	}

	private void tickWeapons(double dt) {
		for (Weapon wep : weaponList) {
			wep.tick(dt);
		}
	}

	public void jetPack() {
		double fuelTick = 13 * dt;
		if (jetPackFuel >= fuelTick) {
			accelerate(0, -0.45);
			jetPackFuel -= fuelTick;
		}
	}

	public void toggleWeapon() {
		currentWeapon++;
		if (currentWeapon >= weaponList.size())
			currentWeapon = 0;
		if (weaponList.get(currentWeapon).getAmmo() == 0)
			toggleWeapon();
	}

	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}

	private void player1Input() {
		if (input.up1.down)
			jetPack();
		if (input.down1.clicked)
			toggleWeapon();
		if (input.right1.down && canGoRight) {

			if (dx < 2)
				accelerate(torque, 0);

		}
		if (input.left1.down && canGoLeft) {
			if (dx > -2)
				accelerate(-torque, 0);
		}
		if (input.fire1.clicked) {
			chargingCannon = true;
		}
		if (chargingCannon && !input.fire1.down || cannonCharge >= 1) {
			fire(cannonCharge);
			chargingCannon = false;
			cannonCharge = 0;
		}
		if (input.rotateL1.down)
			incrementMuzzleAngle(3);
		if (input.rotateR1.down)
			incrementMuzzleAngle(-3);
	}

	private void player2Input() {
		if (input.up2.down)
			jetPack();
		if (input.down2.clicked)
			toggleWeapon();
		if (input.right2.down && canGoRight) {

			if (dx < 2)
				accelerate(torque, 0);
		}
		if (input.left2.down && canGoLeft) {
			if (dx > -2)
				accelerate(-torque, 0);
		}
		if (input.fire2.clicked) {
			chargingCannon = true;
		}
		if (chargingCannon && !input.fire2.down || cannonCharge >= 1) {
			fire(cannonCharge);
			chargingCannon = false;
			cannonCharge = 0;
		}
		if (input.rotateL2.down)
			incrementMuzzleAngle(3 * dt);
		if (input.rotateR2.down)
			incrementMuzzleAngle(-3 * dt);
	}

	public double getMuzzleAngle() {
		return muzzleAngle;
	}

	@Override
	public void takeDamage(double amount) {
		this.damageTaken += amount;
	}

	public void applyFriction() {
		if (!canGoDown)
			accelerate(-dx * frictionConstant, 0);
	}

	@Override
	public void remove() {
		if (--life == 0) {
			super.remove();
			int scoreWon = 1000;
			for (Tank player : level.getPlayers()) {
				if (player == this)
					continue;
				scoreWon -= 250;
			}
			this.score += scoreWon;
			return;
		}
		setLocation(level.getPlayerSpawns().get(rand.nextInt(level.getPlayerSpawns().size())));
		setSpeed(0, -1);
		damageTaken = 1;
		initInventory();
		currentWeapon = 0;
	}

	@Override
	public void tick(double dt) {
		super.tick(dt);
		if (playerNumber == 1)
			player1Input();
		if (playerNumber == 2)
			player2Input();

		if (chargingCannon)
			cannonCharge += 0.015 * dt;
		if (jetPackFuel + 5 > 100)
			jetPackFuel = 100;
		else
			jetPackFuel += 2 * dt;

		handleTerrainIntersection();
		applyFriction();
		tickWeapons(dt);
		if (this.y < -500)
			remove();

	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		RGBImage img;
		RGBImage crossHair;
		if (playerNumber == 1) {
			img = ResourceManager.TANK1;
			crossHair = ResourceManager.CROSSHAIR1;
		} else {
			img = ResourceManager.TANK2;
			crossHair = ResourceManager.CROSSHAIR2;
		}

		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr() + 1), img.getWidth(), img.getHeight());
		renderer.DrawImage(crossHair, -1, (int) (getCrosshairLocation().getX() - crossHair.getWidth() / 2),
				(int) (getCrosshairLocation().getY() - crossHair.getHeight() / 2), crossHair.getWidth(), crossHair.getHeight());

	}

	@Override
	public String getObject() {
		return super.getObject() + "'" + to5DigitString(this.muzzleAngle);
	}

	@Override
	public void handleMessage(String[] msg) {
		super.handleMessage(msg);
		double muzzleAngle = Double.parseDouble(msg[6]);
		setMuzzleAngle(muzzleAngle);

	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.TANK);

	}
}
