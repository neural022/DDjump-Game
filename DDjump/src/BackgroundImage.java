import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class BackgroundImage {
	BufferedImage image;
	BufferedImage image1, image2, image3;
	Graphics2D g2;
	int x1, x2;	//	background ¾î®y¼Ð
	static int speed = 10;
	
	BackgroundImage() {
		try {
			image1 = ImageIO.read(new File("image\\background.png"));
			image2 = ImageIO.read(new File("image\\background.png"));
		}
		catch(IOException e) {
			e.getStackTrace();
		}
		image = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		x1 = 0;
		x2 = 1280;
		g2.drawImage(image1, x1, 0, null);
		g2.drawImage(image1, x2, 0, null);
	}
	
	void roll() {
		if(GamePanel.score == 15) speed = 15;
		else if(GamePanel.score == 35) speed = 20;
		else if(GamePanel.score == 55) speed = 25;
		else if(GamePanel.score == 75) speed = 30;
		
		x1 -= speed;
		x2 -= speed;
		
		if(x1 <= -1280) x1 = 1280;
		if(x2 <= -1280) x2 = 1280;
		g2.drawImage(image1, x1, 0, null);
		g2.drawImage(image1, x2, 0, null);
	}
}
