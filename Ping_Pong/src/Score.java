import java.awt.*;

public class Score extends Rectangle {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	Font font = new Font("Arial", Font.PLAIN, 50);			// Create a font for the player scores
	
	
	public Score(int width, int height) {
		Score.GAME_WIDTH = width;
		Score.GAME_HEIGHT = height;
	}
	
	// Draw the game score at the top of the screen and the white line dividing the middle
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);			// Draw a line in the center to mark the half-way
		g.drawString(String.valueOf(player1), GAME_WIDTH/2 - 60, 60);	// Display player 1 score
		g.drawString(String.valueOf(player2), GAME_WIDTH/2 + 30, 60);	// Display player 2 score
	}
}
