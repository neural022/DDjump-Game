import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Obstacle {
	BufferedImage image;
	BufferedImage image1, image2, image3, image4;
	int x, y;
	int speed;
	final int Min = 580;
	
	Obstacle() {
		try {
			image1 = ImageIO.read(new File("image\\freeman1.png"));
			image2 = ImageIO.read(new File("image\\freeman2.png"));
			image3 = ImageIO.read(new File("image\\freeman3.png"));
			image4 = ImageIO.read(new File("image\\coin.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
				
		int temp = (int) (Math.random()*4);
		if(temp == 0) image = image1;
		else if(temp == 1) image = image2;
		else if(temp == 2) image = image3;
		else image = image4;
		speed = BackgroundImage.speed;
		
		this.x = 1280;
		this.y = 630 - image.getHeight();
	}
	
	void walk() {
		x -= speed;
	}
	Rectangle getGroundBounds() {	
		if(image == image1) return new Rectangle(x, y, image.getWidth(), image.getHeight()); 
		else if(image == image2) return new Rectangle(x+10, y+10, image.getWidth()-20, image.getHeight()-10); 
		else if(image == image3) return new Rectangle(x, y+30, image.getWidth()-15, image.getHeight()-250);
		else return new Rectangle(x+20, y+20, image.getWidth()-40, image.getHeight()-250);
	}
	
}
