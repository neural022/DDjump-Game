import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class FreshThread extends Thread {
	GamePanel gamePanel;
	FreshThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void run() {
		while (!gamePanel.isFinish()) {
			gamePanel.repaint(); // ���e
			try {
				Thread.sleep(gamePanel.FRESH); // ��v
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Container c = gamePanel.getParent();
		while(!(c instanceof DDJFrame)) c = c.getParent();
		DDJFrame f = (DDJFrame) c;		
		//System.out.println(f.getClass());
		
		// GameOver Message
		JOptionPane.showMessageDialog(f, null, "Game Over", JOptionPane.PLAIN_MESSAGE, new ImageIcon("image\\freeman.png"));
		f.restart();
	}

}
