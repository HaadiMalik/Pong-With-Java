import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
	
	Random random;
	int randomYDir;
	int randomXDir;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 4;
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		
		// Decide the x direction of the ball
		randomXDir = random.nextInt(2);
		if (randomXDir == 0)
			randomXDir--;
		setXDir(randomXDir * initialSpeed);
		
		// Decide the y direction of the ball
		randomYDir = random.nextInt(2);
		if (randomYDir == 0)
			randomYDir--;
		setYDir(randomYDir * initialSpeed);
	}
	
	public void setXDir(int randXDir) {
		xVelocity = randXDir;
	}
	
	public void setYDir(int randYDir) {
		yVelocity = randYDir;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
		Font font = new Font("Arial", Font.PLAIN, 50);			// Create a font for the player scores
		g.setFont(font);
		g.drawString(String.valueOf(Math.abs(this.xVelocity)), 100, 100);
	}
}
