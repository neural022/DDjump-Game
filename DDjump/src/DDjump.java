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
		c.add(gamePanel); // �[�J�e��
		addKeyListener(gamePanel);	// �[�J��L��ť

		setVisible(true);
	}
	
	void restart() {
		Container c = getContentPane();
		c.removeAll();
		
		GamePanel newGame = new GamePanel();
		c.add(newGame);
		addKeyListener(newGame);
		c.validate();	//	���s����
	}
}

public class DDjump {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DDJFrame ddJFrame = new DDJFrame();
	}
}
