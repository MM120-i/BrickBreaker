package BrickBreaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	// Variables to keep track of game state and values
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8; // The delay (in milliseconds) between timer ticks, controlling the speed of the game.
    private int playerX = 310; // The initial X-coordinate of the player's paddle.
    private int ballPosX = 120; // The initial X-coordinate of the ball.
    private int ballPosY = 350; // The initial Y-coordinate of the ball.
    private int ballXDir = -1; // The initial X-direction of the ball (-1 = left, 1 = right).
    private int ballYDir = -2; // The initial Y-direction of the ball (-1 = up, 1 = down).
    private MapGenerator map; // The map that contains the bricks.
    private int currentBrickValue;    
    private int highestScore = 0;

    public Gameplay() {
    	
    	initGame();
    }

 // Initialize the game components
    private void initGame() {
    	
        map = new MapGenerator(3, 7); // Create a new map with 3 rows and 7 columns.
        addKeyListener(this); // Add a KeyListener to handle player input.
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this); // Create a timer with the specified delay and attach this Gameplay instance as the ActionListener.
        timer.start(); // Start the timer to trigger actionPerformed() method at the specified interval.
    }
    
    // Render the game components and handle the graphics
    @Override
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g); // Call the parent's paintComponent() to ensure proper rendering.

        // Draw the game background and borders
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Highest Score: " + highestScore, getWidth() - 200, 30); // Display the highest score on the top right side.
        
        // Render the player's paddle and the ball on the screen
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        // Render the bricks on the screen
        for (int i = 0; i < map.map.length; i++) {
        	
            for (int j = 0; j < map.map[0].length; j++) {
            	
                int brickValue = map.map[i][j];
                
                if (brickValue > 0) {
                	
                    // Different colors for different brick values
                	switch (brickValue) {
                    case 1:
                        g.setColor(Color.red);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(Color.green);
                        break;
                    case 4:
                        g.setColor(Color.orange);
                        break;
                    case 5:
                        g.setColor(Color.cyan);
                        break;
                    default:
                        g.setColor(Color.yellow);
                        break;
                    }
                	
                    g.fillRect(j * map.brickWidth + 80, i * map.brickHeight + 50, map.brickWidth, map.brickHeight);
                    ((Graphics2D) g).setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * map.brickWidth + 80, i * map.brickHeight + 50, map.brickWidth, map.brickHeight);
                }
            }
        }

        // Check for game-over conditions and display appropriate messages
        if (totalBricks <= 0) 
            gameOver("You Won!", "Press Enter to Restart", g);
        
        if (ballPosY > 570) 
            gameOver("Game Over!, Scores: " + score, "Press Enter to Restart", g);

        g.dispose(); // Release any system resources associated with the graphics context.
    }


 // Handle game-over condition and display the appropriate messages
    private void gameOver(String message1, String message2, Graphics g) {
    	
    	if(score > highestScore)
    		highestScore = score;
    	
        play = false;
        ballXDir = 0;
        ballYDir = 0;

        g.setColor(Color.RED);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString(message1, 190, 300);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Highest Score: " + highestScore, 300, 60);

        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(message2, 230, 350);
    }
 // Handle the game logic and ball movement
    @Override
    public void actionPerformed(ActionEvent e) {
    	
        timer.start(); // Restart the timer to continue triggering actionPerformed() method at the specified interval.
        
        if (play) { // Check if the game is in play state.
        	
            Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20); // Create a rectangle representing the ball.
            Rectangle paddleRect = new Rectangle(playerX, 550, 100, 8); // Create a rectangle representing the player's paddle.

            if (ballRect.intersects(paddleRect)) {
                ballYDir = -ballYDir; // If the ball hits the paddle, reverse its Y-direction.
            }

            int i = 0;
            boolean foundCollision = false;

            // Loop through the map's bricks and check for collisions with the ball
            while (i < map.map.length && !foundCollision) {
                int j = 0;
                while (j < map.map[0].length && !foundCollision) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                        if (ballRect.intersects(brickRect)) {
                        	
                        	currentBrickValue = map.map[i][j]; // Update the current brick value to the value of the collided brick.
                            score += currentBrickValue;
                            map.setBrickValue(0, i, j); // Set the collided brick's value to 0 to remove it.
                            totalBricks--;

                            boolean flag2 = (ballPosX + 19 <= brickRect.x) || (ballPosX + 1 >= brickRect.x + brickRect.width);

                            if (flag2) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }

                            foundCollision = true;
                        }
                    }
                    j++;
                }
                i++;
            }

            // Move the ball based on its current direction and speed
            ballPosX += ballXDir;
            ballPosY += ballYDir;

            // Handle ball bouncing off the screen edges
            if (ballPosX < 0) {
            	
                ballXDir = -ballXDir;
            }
            if (ballPosY < 0) {
            	
                ballYDir = -ballYDir;
            }
            if (ballPosX > 670) {
            	
                ballXDir = -ballXDir;
            }
        }

        repaint(); // Redraw the game components on the screen.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not used in the game, as it is only invoked when a key is typed (pressed and released).
        // We can leave it empty, as there is no specific action to take on key typing in this game.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // This method is called whenever a key is pressed on the keyboard.
        // It handles the player's input for moving the paddle and starting the game.

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight(); // If the right arrow key is pressed, move the paddle to the right.
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft(); // If the left arrow key is pressed, move the paddle to the left.
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // If the Enter key is pressed, check if the game is not already in play (game over or not started).
            // If so, reset the game to its initial state.
            if (!play) {
            	
                resetGame();
            }
        }
    }

    private void moveRight() {
        // This method is responsible for moving the paddle to the right.

        play = true; // Set the game state to "play" to ensure the game is running.
        playerX += 20; // Move the paddle 20 pixels to the right.
    }

    private void moveLeft() {
        // This method is responsible for moving the paddle to the left.

        play = true; // Set the game state to "play" to ensure the game is running.
        playerX -= 20; // Move the paddle 20 pixels to the left.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is not used in the game, as it is only invoked when a key is released (after being pressed).
        // We can leave it empty, as there is no specific action to take on key release in this game.
    }

    private void resetGame() {
        play = true;
        ballPosX = 120;
        ballPosY = 350;
        ballXDir = -1;
        ballYDir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;

        // Create a new map with a different brick structure (you can define a new 2D array for the brick configuration)
        int[][] newBrickConfig = { {1, 2, 3, 4, 5, 1, 2}, {3, 4, 5, 1, 2, 3, 4}, {5, 1, 2, 3, 4, 5, 1} };
        map = new MapGenerator(3, 7, newBrickConfig);

        repaint();
    }

}