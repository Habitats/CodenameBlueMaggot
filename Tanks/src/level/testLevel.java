package level;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import entity.Animations;
import entity.Entity;
import entity.Grenade;
import entity.Rocket;
import entity.ScoreBubble;
import entity.Shell;
import entity.Tank;
import entity.weapon.Rocketlauncher;

import inputhandler.InputHandler;
import Networking.NetworkObject;
import baseGame.BaseGame;
import baseGame.testGame;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import baseGame.animations.Animation;
import baseGame.animations.AnimationFactory;

public class testLevel extends BasicLevel {

	private RGBImage tank1;
	private RGBImage tank2;
	private RGBImage muzzle1;
	private RGBImage crossHair1;
	private RGBImage crossHair2;

	private RGBImage shell;
	private RGBImage grenade;
	private RGBImage rocket;

	private RGBImage scoreBubble;
	private int muzzle1Rotation;

	private RGBImage muzzle2;
	private int index = 0;;
	private Tank tankEntity1;
	private Tank tankEntity2;

	public Tank getTankEntity1() {
		return tankEntity1;
	}

	public void setTankEntity1(Tank tankEntity1) {
		this.tankEntity1 = tankEntity1;
	}

	public Tank getTankEntity2() {
		return tankEntity2;
	}

	public void setTankEntity2(Tank tankEntity2) {
		this.tankEntity2 = tankEntity2;
	}

	private RGBImage backGround;

	Random rand = new Random();

	public testLevel(BaseGame game, InputHandler handler) {
		super(game, handler);
		tankEntity1 = new Tank(10, 10, 1, handler, this);
		addEntity(tankEntity1);
		tankEntity2 = new Tank(1000, 10, 2, handler, this);
		addEntity(tankEntity2);

		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion1.png"), Animations.EXPLOSIONS, 50, 50);
		AnimationFactory.getInstance().addSpriteSheet(new File("./res/Explosion2.png"), Animations.EXPLOSIONS, 100, 100);

		scoreBubble = new RGBImage(new File("./res/Scorebubble.png"));
		grenade = new RGBImage(new File("./res/Grenade_temp.png"));
		rocket = new RGBImage(new File("./res/Rocket.png"));
		shell = new RGBImage(new File("./res/Shell_temp.png"));
		tank1 = new RGBImage(new File("./res/Tank_Flat.png"));
		tank2 = new RGBImage(new File("./res/Tank2.png"));

		// terrain = new Terrain(new File("./res/testlvl.png"));
		terrain = new Terrain(new File("./res/Cityscape_terrain.png"));
		backGround = new RGBImage(new File("./res/Cityscape_background.png"));
		crossHair1 = new RGBImage(new File("./res/Crosshair.png"));
		crossHair2 = new RGBImage(new File("./res/Crosshair.png"));

	}

	//
	public void tick(double dt) {
		super.tick(dt);
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// addEntity(new ScoreBubble(rand.nextInt(1000), 10, 5, this, 0.3, 0,
		// 100));
		// System.out.println("Objects: " + entities.size() );
	}

	public void onDraw(Renderer renderer) {
		super.render(renderer);

		renderer.DrawImage(backGround, 0, 0, testGame.WIDTH, testGame.HEIGHT);
		renderer.DrawImage(terrain, -1, 0, 0, terrain.getWidth(), terrain.getHeight());

		for (Entity ent : entities) {
			ent.render(renderer);

		}

		renderer.DrawImage(terrain, -1, 0, 0, terrain.getWidth(), terrain.getHeight());

		renderer.DrawImage(tank1, -1, (int) tankEntity1.getLocation()[0] - (int) tankEntity1.getXr(),
				(int) tankEntity1.getLocation()[1] - (int) tankEntity1.getYr() + 1, tank1.getWidth(), tank1.getHeight());
		renderer.DrawImage(tank2, -1, (int) tankEntity2.getLocation()[0] - (int) tankEntity2.getXr(),
				(int) tankEntity2.getLocation()[1] - (int) tankEntity2.getYr() + 1, tank2.getWidth(), tank2.getHeight());
		renderer.DrawImage(crossHair1, -1, (int) tankEntity1.getCrosshairLocation().getX() - crossHair1.getWidth() / 2, (int) tankEntity1
				.getCrosshairLocation().getY() - crossHair1.getHeight() / 2, crossHair1.getWidth(), crossHair1.getHeight());
		renderer.DrawImage(crossHair2, -1, (int) tankEntity2.getCrosshairLocation().getX() - crossHair2.getWidth() / 2, (int) tankEntity2
				.getCrosshairLocation().getY() - crossHair2.getHeight() / 2, crossHair2.getWidth(), crossHair2.getHeight());

	}

}
