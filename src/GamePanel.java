import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.55556));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_SIZE = 20;
	static final int PADDLE_WIDTH = 20;
	static final int PADDLE_HEIGHT = 80;
	static final int BALL_DIAMETER = 10;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	public Ball ball;
	Paddle paddle1;
	Paddle paddle2;
	Score score;
	
	// Creates the initial instances of the game
	public GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// Create the new ball
	public void newBall() {
		random = new Random();	// Pass "random.nextInt()" to the second parameter of new Ball(GAME_HEIGHT - BALL_DIAMETER) constructor for random y-axis ball spawn
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	// Create the new paddles
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);		
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	// Draw the paddles (respective rectangles), the ball (oval), and the score (text) on to the GamePanel
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	// Smooth out the sluggish movements of the involved instances
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	// Keeps the paddles and ball from leaving the screen/panel
	public void boundary() {
		if (paddle1.y < 0)		// Stops paddle 1 from moving up and off the screen
			paddle1.y = 0;
		if (paddle1.y > GAME_HEIGHT - PADDLE_HEIGHT)
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if (paddle2.y < 0)
			paddle2.y = 0;
		if (paddle2.y > GAME_HEIGHT - PADDLE_HEIGHT)
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		if (ball.y < 0 || ball.y > (GAME_HEIGHT - BALL_DIAMETER))
			ball.setYDir(-(ball.yVelocity));
	}
	
	// Checks to see if the ball has collided with either paddle
	public void collide() {
		if (ball.intersects(paddle1) || ball.intersects(paddle2)) {		// If the ball has collided
			ball.setXDir(-(ball.xVelocity));			// Reverse the ball direction
			double speed = (Math.random()*5)+5;
			if (ball.xVelocity > 0) ball.xVelocity += speed;			// Increase the ball speed with every
			else if (ball.xVelocity < 0) ball.xVelocity += -speed;		// collision for increasing difficulty
			paddle1.speed += speed/2;
			paddle2.speed += speed/2;
		}
	}
	
	// Checks if the the ball has passed the paddle to score a point. Resets the ball and paddles if so.
	public void scorePoint() {
		// A "+ 10" is added to the "if" statement to try and prevent the ball getting stuck with the paddle
		if (ball.x < -10) {		// Player 2 scores
			score.player2++;
			newPaddles();
			newBall();
		}
		if (ball.x > GAME_WIDTH + 10) {		// Player 1 scores
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	// Game loop
	public void run() {
		long lastTime = System.nanoTime();
		double nanoSec = 1000000000 / 60.0;
		double delta = 0;
		
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSec;
			lastTime = now;
			
			if(delta >= 1) {
				move();
				boundary();
				collide();
				scorePoint();
				repaint();
				delta--;
			}
		}
	}
	
	// Methods to listen to keyboards presses and releases
	public class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}

	}
}
