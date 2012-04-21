package entity;

import java.util.ArrayList;
import java.util.List;

import inputhandler.InputHandler;

public class Tank extends Entity {

	private int muzzleAngle;
	private int muzzleLength;
	private int hitpoints;
	private int maxHitpoints = 200;
	private InputHandler input;
	private int playerNumber;
	private int jetPackFuel = 100;
	private List<Projectile> weaponList = new ArrayList<Projectile>();
	private int currentWeapon = 0;
	
	public Tank(double x, double y, int playerNumber, InputHandler input) {
		super(x, y, 100, 100);
		muzzleAngle = 0;
		muzzleLength = 20;
		hitpoints = 200;
		this.playerNumber = playerNumber;
		this.input = input;
	}
	
	public void setMuzzleAngle(int degrees){
		this.muzzleAngle = degrees;
	}
	
	public void incrementMuzzleAngle(int degrees) {
		setMuzzleAngle(this.muzzleAngle + degrees);
		muzzleAngle = muzzleAngle%60;
	}

	public void changeHp(int amount) {
		if (hitpoints + amount > maxHitpoints)
			hitpoints = maxHitpoints;
		else if (hitpoints + amount < 0) {
			hitpoints = 0;
			remove();
		} else
			hitpoints += amount;
	}
	
	public void fire(double speedPercent){
	}
	
	@Override
	public boolean intersectsEntity(Entity other) {
		double[] p = other.getLocation();
		double xLeft = p[0] - other.xr;
		double xRight = p[0] + other.xr;
		double yTop = p[1] - other.yr;
		double yBot = p[1] + other.yr;
		return !(x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot);
	}
	
	@Override
	public void intersectsTerrain() {
		// TODO Auto-generated method stub
	}
	
	public void jetPack(){
		int fuelTick = 10;
		if(jetPackFuel >= fuelTick){
			accelerate(0, 8);
			jetPackFuel -= fuelTick;
		}
	}
	
	public void toggleWeapon(){
		if(currentWeapon == weaponList.size() - 1)
			currentWeapon = 0;
		else
			currentWeapon++;
	}
	
	@Override
	public void gravitate() {
	}
	
	private void player1Input(){
		if(input.up1.down)
			jetPack();
		if(input.down1.down)
			toggleWeapon();
		if(input.left1.down){
			if(dx < 4)
				accelerate(0.5, 0);
		}
		if(input.left1.down){
			if(dx > -4)
				accelerate(-0.5, 0);
		}
	}
	
	private void player2Input(){
		
	}
	
	@Override
	public void tick() {
		super.tick();
		if(playerNumber == 1)
			player1Input();
		if (playerNumber == 2)
			player2Input();
		
		if(jetPackFuel + 5 > 100)
			jetPackFuel = 100;
		else
			jetPackFuel += 5;
	}
}
