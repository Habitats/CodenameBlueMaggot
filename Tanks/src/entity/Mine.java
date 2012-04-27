package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class Mine extends Projectile {

	RGBImage mineSheet = ResourceManager.MINE;
	private boolean armed = false;
	private boolean triggered = false;
	private double liveTime = 0;
	private double armTime = 80;
	private boolean explode = false;
	private boolean touchingGround = false;
	private int explosionPower = 100;
	private int explosionRadius = 25;

	public Mine(double x, double y, BasicLevel level, double angle) {
		super(x, y, 6, 4, level, 1, angle);
	}

	public void handleIntersections() {
		if (armed) {
			triggered = false;
			for (Tank player : level.getPlayers()) {
				if (intersectsEntity(player)) {
					triggered = true;
					explode = true;
				}
			}
		}
		touchingGround = false;
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))) {
				while (level.getTerrain().hitTestpoint((int) (x), (int) (y))) {
					setLocation(x - dx, y - dy);
				}
				setSpeed(0, 0);
				touchingGround = true;
			}
		}
	}

	@Override
	public void explode() {
		level.addEntity(new Explosion(x, y, explosionRadius, level, explosionPower));
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		this.remove();
	}

	@Override
	public void render(Renderer renderer) {
		int subimageIndex = 0;
		if (armed)
			subimageIndex = 1;

		RGBImage img = mineSheet.getSubImage(14 * subimageIndex, 0, 14, 8);
		renderer.DrawImage(img, -1, (int) (x - 7), (int) (y - 4), img.getWidth(), img.getHeight());
	}

	@Override
	public void gravitate() {
	}

	@Override
	public void tick(double dt) {
		super.tick(dt);

		handleIntersections();
		if (liveTime < armTime) {
			liveTime += dt;
		} else if (!armed)
			armed = true;
		if (explode && !triggered)
			explode();
		if (!touchingGround) {
			accelerate(0, 0.08);
		}
	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub

	}
}
