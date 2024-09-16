import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {
	
	int id;						// Which paddle is being considered/controlled/moved
	int yVelocity;				// A middle variable to go in-between "setTDir()" and "move()"
	int speed = 7;				// Controls how fast the paddles move UP and/or DOWN
	String p1Dir = "";			// These two strings are used for simultaneous
	String p2Dir = "";			// button presses to not override the other
	
	// Paddle constructor to make the paddle (not draw)
	public Paddle(int x, int y, int width, int height, int pid) {
		super(x, y, width, height);			// Extends the rectangle class. Uses super() to draw a rectangle with constructor parameters
		this.id = pid;						// Sets the paddle id to id given in constructor parameters
	}
	
	// If the movement keys are pressed.
	// Uses id to only manipulate one paddle with certain keys
	public void keyPressed(KeyEvent e) {
		if (this.id == 1) {		// If paddle has an id of 1
			if (e.getKeyCode() == KeyEvent.VK_W || p1Dir.equals("UP")) {		// If the 'W' key is pressed, move paddle 1 up
				p1Dir = "UP";
				setYDir(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S || p1Dir.equals("DOWN")) {		// If the 'S' key is pressed, move paddle 1 down
				p1Dir = "DOWN";
				setYDir(speed);
				move();
			}
		} else if (this.id == 2) {		// Else if paddle has an id of 2
			if (e.getKeyCode() == KeyEvent.VK_UP || p2Dir.equals("UP")) {		// If the 'UP Arrow' key is pressed, move paddle 2 up
				p2Dir = "UP";
				setYDir(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN || p2Dir.equals("DOWN")) {		// If the 'DOWN Arrow' key is pressed, move paddle 2 down
				p2Dir = "DOWN";
				setYDir(speed);
				move();
			}
		}
	}
	
	// If the movement keys are released
	// Uses id to only manipulate one paddle with certain keys
	public void keyReleased(KeyEvent e) {
		if (this.id == 1) {		// If paddle has an id of 1
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {		// If the 'W' key or 'S' key is released
				p1Dir = "";
				setYDir(0);
				move();
			}
		} else if (this.id == 2) {		// Else if paddle has an id of 2
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {		// If the 'UP Arrow' or 'DOWN Arrow' key is released
				p2Dir = "";
				setYDir(0);
				move();
			}
		}
	}
	
	// Start shifting the paddle along the y-axis
	public void setYDir(int yDir) {
		yVelocity = yDir;
	}
	
	// Move the paddle(s)
	public void move() {
		y += yVelocity;
	}
	
	// Draw the paddles, fill them with color
	public void draw(Graphics g) {
		if (this.id == 1)
			g.setColor(Color.blue);
		if (this.id == 2)
			g.setColor(Color.red);
		
		g.fillRect(x, y, width, height); 
	}
}
