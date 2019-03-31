import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.text.html.ImageView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class GamePanel extends JPanel implements KeyListener { // 實作Keyboard Listener
	BufferedImage image; // main image
	Graphics2D g2; // draw tool

	BackgroundImage background;
	DD dd;
	
	boolean finish = false;
	static final int FRESH = 10; // refresh time, ms
	
	List<Obstacle> list = new ArrayList<Obstacle>();
	int addObstacleTimer = 0;
	
	static int score = 0;
	int addScoreTimer = 0;

	GamePanel() {
		image = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		dd = new DD();
		background = new BackgroundImage();
		list.add(new Obstacle());
		
		FreshThread t = new FreshThread(this);
		t.start();
	}

	void paintImage() {
		background.roll();
		dd.walk();
		g2.drawImage(background.image, 0, 0, this);
		
		if(addObstacleTimer >= 800) {
			int temp = (int) (Math.random()*100);
			if(temp > 40) list.add(new Obstacle());
			addObstacleTimer = 0;
		}
		//Rectangle r;
		for(int i=0;i<list.size();i++) {
			Obstacle freeman = list.get(i);
			freeman.walk();
			/*r = freeman.getGroundBounds();
			g2.fillRect(r.x, r.y, r.width, r.height);*/
			g2.drawImage(freeman.image, freeman.x, freeman.y, this);
			
			if(freeman.image == freeman.image4) {
				if(freeman.getGroundBounds().intersects(dd.getHandBounds()) || freeman.getGroundBounds().intersects(dd.getFeedBounds())) score += 1;
			}
			else if(freeman.image == freeman.image1 || freeman.image == freeman.image2 || freeman.image == freeman.image3) {
				if(freeman.getGroundBounds().intersects(dd.getHandBounds()) || freeman.getGroundBounds().intersects(dd.getFeedBounds())) gameOver();
			}
		}
		
		
		//r = dd.getHandBounds();
		g2.setColor(Color.BLACK);
		/*g2.fillRect(r.x, r.y,r. width, r.height);
		r = dd.getFeedBounds();
		g2.fillRect(r.x, r.y, r.width, r.height);*/
		g2.drawImage(dd.image, dd.x, dd.y, this); 	// this 告訴自己DD畫完了
		
		if(addScoreTimer >= 500) {
			score += 1;
			addScoreTimer = 0;
		}
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("黑體", Font.BOLD, 24));
		g2.drawString(String.format("%07d", score), 1100, 80);
		
		addObstacleTimer += FRESH;
		addScoreTimer += FRESH;

	}
	boolean isFinish() { return finish;}
	void gameOver() { 
		finish = true; 
		score = 0;
		BackgroundImage.speed = 10;
	}
	@Override
	public void paint(Graphics g) {
		paintImage();
		g.drawImage(image, 0, 0, this); // Paste to GamePanel
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 按鍵類型
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 按鍵按下
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_SPACE) dd.jump();
		if(keycode == KeyEvent.VK_Z) dd.doublejump();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 按鍵放開
	}

}
