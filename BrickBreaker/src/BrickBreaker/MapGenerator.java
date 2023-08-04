package BrickBreaker;

import java.awt.*;
import java.util.Random;

public class MapGenerator {

    protected int[][] map;
    protected int brickWidth;
    protected int brickHeight;

    // Constructor to create the map with specified rows and columns
    public MapGenerator(int row, int col) {
    	
        map = new int[row][col];
        initMapWithRandomValues(); // Initialize the map with random brick values
        brickWidth = 540 / col; // Calculate the width of each brick
        brickHeight = 150 / row; // Calculate the height of each brick
    }

    // Constructor to create the map with specified rows, columns, and custom brick configuration
    public MapGenerator(int row, int col, int[][] brickConfig) {
    	
        map = new int[row][col];
        initMapWithCustomValues(brickConfig); // Initialize the map with custom brick values
        brickWidth = 540 / col; // Calculate the width of each brick
        brickHeight = 150 / row; // Calculate the height of each brick
    }

    // Private method to initialize the map with random brick values
    private void initMapWithRandomValues() {
    	
        Random random = new Random();
        
        for (int i = 0; i < map.length; i++) {
        	
            for (int j = 0; j < map[0].length; j++) {
            	
                map[i][j] = random.nextInt(5) + 1; // Generates random values between 1 and 5.
            }
        }
    }

    // Private method to initialize the map with custom brick values
    private void initMapWithCustomValues(int[][] brickConfig) {
    	
        for (int i = 0; i < map.length; i++) {
        	
            for (int j = 0; j < map[0].length; j++) {
            	
                map[i][j] = brickConfig[i][j];
            }
        }
    }


    // Method to draw the entire map on the screen
    public void draw(Graphics2D g) {
    	
        for (int i = 0; i < map.length; i++) {
        	
            for (int j = 0; j < map[0].length; j++) {
            	
                if (map[i][j] > 0) {
                	
                    drawBrick(g, i, j); // Call the drawBrick method to draw individual bricks
                }
            }
        }
    }

    // Private method to draw an individual brick at the specified row and column
    private void drawBrick(Graphics2D g, int row, int col) {
    	
        int brickX = col * brickWidth + 80; // Calculate the x-coordinate of the brick
        int brickY = row * brickHeight + 50; // Calculate the y-coordinate of the brick

        // Draw the brick as a filled rectangle with a color based on its value
        int brickValue = map[row][col];
        
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

        g.fillRect(brickX, brickY, brickWidth, brickHeight);

        // Draw the brick's outline with a black color
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.black);
        g.drawRect(brickX, brickY, brickWidth, brickHeight);
    }

    // Method to set the value of a brick at the specified row and column
    public void setBrickValue(int value, int row, int col) {
    	
        map[row][col] = value;
    }
}
