import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class DDJFrame extends JFrame {
	GamePanel gamePanel;

	DDJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 1280, 800);
		setTitle("DD jump");

		gamePanel = new GamePanel();
		Container c = getContentPane();
		c.add(gamePanel); // 加入容器
		addKeyListener(gamePanel);	// 加入鍵盤監聽

		setVisible(true);
	}
	
	void restart() {
		Container c = getContentPane();
		c.removeAll();
		
		GamePanel newGame = new GamePanel();
		c.add(newGame);
		addKeyListener(newGame);
		c.validate();	//	重新驗證
	}
}

public class DDjump {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DDJFrame ddJFrame = new DDJFrame();
	}
}
