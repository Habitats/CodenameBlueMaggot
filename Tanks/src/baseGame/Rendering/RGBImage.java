package baseGame.Rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RGBImage {
	private BufferedImage rgbBufferedImage;
	private int[] pixels;
	
	public RGBImage(File file){
		
		
		BufferedImage img = null;
		
		try {
			img= ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(img.getType() != BufferedImage.TYPE_INT_RGB){
		rgbBufferedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		rgbBufferedImage.getGraphics().drawImage(img ,0, 0, img.getWidth(), img.getHeight(),Color.white,null);
		}else{
			rgbBufferedImage = img;
		}
		
		pixels = ((DataBufferInt)rgbBufferedImage.getRaster().getDataBuffer()).getData();
		
		
		
	}

	public BufferedImage getRgbBufferedImage() {
		return rgbBufferedImage;
	}

	public int[] getPixels() {
		return pixels;
	}
	

}