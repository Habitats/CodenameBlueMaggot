package baseGame;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

import entity.Entity;
import entity.Tank;

public class testGame extends BaseGame {
	
	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static int ALPHA_MASK = 0xFF0077;
	
	private class Circle{
		double x;
		double y;
		double dx;
		double dy;
		Color color;
	}
	
	ArrayList<Circle> circles;
	Random randomc = new Random();
	
	int circleImg[] = {0xFF0077};
	RGBImage img;
	RGBImage ground = new RGBImage(new File("./res/temp_ground.png"));
	RGBImage tank;
	public testGame(){
		

		setBackgroundColor(Color.BLACK);
		Random random = new Random();
		setBackgroundColor(Color.BLACK);
		
		img = new RGBImage(new File("./res/temp_background.png"));
		tank = new RGBImage(new File("./res/temp_tank.png"));
		
		System.out.println(ground.getPixels()[0]);
		System.out.println(0xFF0077);
		
		
		img.DrawRGBImage(tank.getPixels(), 100, 100, tank.getWidth(), tank.getHeight());
		
		ground.DrawCircle(Color.gray.getRGB(), 700, 500, 137);
		
		circles = new ArrayList<Circle>();
		
		
		//Lager 400000 sirkler
		for (int i = 0;i<10000;i++){
			Circle circle = new Circle();
			circle.x = 400;
			circle.y = 300;
			double rand = random.nextDouble()*2;
			circle.dx = Math.cos(6.28*rand)*random.nextDouble()*5;
			circle.dy = Math.sin(6.28*rand)*random.nextDouble()*5;
			circles.add(circle);
			
			circle.color = new Color(random.nextInt(0xFFFFFF));
		}
		
	}
	
	public void onUpdate(long deltaTime) {
	
		for(Circle circle:circles){
			circle.x += circle.dx*deltaTime*0.01;
			circle.y += circle.dy*deltaTime*0.01;
		
		}
		
	}

	@Override
	public void onDraw(Renderer renderer) {
		renderer.clearAllPixelData(Color.BLACK.getRGB());
		renderer.DrawImage(img, 0, 0, WIDTH, HEIGHT);
		
		renderer.DrawImage(ground, 0, 0, WIDTH, HEIGHT);
		
		for(Circle circle:circles){
			renderer.DrawPixelArrayRGB(circleImg,ALPHA_MASK, (int)circle.x,(int) circle.y, 1, 1);
		}
		renderer.DrawPixelArrayRGB(this.tank.getPixels(),ALPHA_MASK, 780,500, tank.getWidth(), tank.getHeight());
	//renderer.makeTransparent(ALPHA_MASK);
		
	}

}
