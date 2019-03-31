import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class DD {
	BufferedImage image;
	BufferedImage image1, image2, image3;
	// DD 座標	x+:→ x-:←	y+:↓ y-:↑
	int x, y; 
	int countTimer = 0;
	int fresh = GamePanel.FRESH;
	
	boolean jumpState = false;
	boolean doublejumpState = false;
	int jumpHeight = 220;	//	跳躍高度
	final int Max = 110;
	final int Min = 420;
	int jumpValue = 0;
	int jumpTimer = 0;

	DD() {
		try {
			image1 = ImageIO.read(new File("image\\D1.png")); // 讀取載入DD
			image2 = ImageIO.read(new File("image\\D2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.x = 50;
		this.y = Min;
	}

	void step() {
		// 1 seconds change 4 images
		int temp = countTimer / 100 % 3; // (第N個 1/4) % 3
		switch (temp) {
		case 0:
			image = image1;
			break;
		case 1:
			image = image2;
			break;
		default:
			break;
		}
		countTimer += fresh;
	}
	void walk() {  	//	walk
		step(); 
		if(jumpState == true) {
			if(y == Min && y > jumpHeight) jumpValue = -10;
			if(doublejumpState == true) {
				if(y == jumpHeight && y > Max) jumpValue = -10;
				if(y == Max) doublejumpState = false;
			}
			else if(y == jumpHeight && y < Min) jumpValue = 10;
			if(y == Max && y < Min) jumpValue = 10;
			y += jumpValue;
			
			System.out.println("Y = " + y);
			if(y == Min) jumpState = false;
		}
		
	}
	void jump() { jumpState = true; }
	void doublejump() {	doublejumpState = true; }
	
	Rectangle getHandBounds() { return new Rectangle(x+20, y + 30, 155, 76); }
	Rectangle getFeedBounds() { return new Rectangle(x + 60, y + 152, 82, 30); }
}
