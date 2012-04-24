//Hovedklasse for alt som puttes p� brettet,
//skal bli abstrakt

package entity;

import java.awt.Point;
import java.util.Random;

import level.BasicLevel;

import baseGame.testGame;

public abstract class Entity {

	protected final Random rand = new Random();
	protected double x, y;
	protected double xr, yr;
	protected  BasicLevel level;
	protected int angle = 0;
	protected double dx = 0, dy = 0;
	protected double frictionConstant = 0.05;
	public boolean removed = false;

	public Entity(double x, double y, double xr, double yr, BasicLevel level) {
		this.level = level;
		setLocation(x, y);
		this.xr = xr;
		this.yr = yr;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double[] getLocation() {
		double[] location = {x,y};
		return location;
	}

	public double getXr() {
		return xr;
	}

	public double getYr() {
		return yr;
	}

	public void setSpeed(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getAngle() {
		return this.angle;
	}

	public void accelerate(double ddx, double ddy) {
		this.dx += ddx;
		this.dy += ddy;
	}

	public  void gravitate(){
		accelerate(0, 0.1);
	};
	public void applyFriction(){
		accelerate(-dx*frictionConstant,0);
	}
	public void move() {
		this.setLocation((this.x + this.dx), (this.y + this.dy));
	}
	
	public void tick() {
		move();
		gravitate();
		applyFriction();
		if (x > testGame.WIDTH + 100 || x < -100 || y > testGame.HEIGHT + 100 || y < -800)
			remove();
	}

		public boolean intersectsEntity(Entity ent) {
				double[] p = ent.getLocation();
				double xLeft = p[0] - ent.xr;
				double xRight = p[0] + ent.xr;
				double yTop = p[1] - ent.yr;
				double yBot = p[1] + ent.yr;
				if (!(x + xr < xLeft || y + yr < yTop || x - xr > xRight || y - yr > yBot)){
					return true;
		}
			return false;
	}
	
	public void remove() {
		this.removed = true;
	}
}
