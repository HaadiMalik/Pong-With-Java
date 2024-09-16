import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel panel;
	
	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("PONG");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
