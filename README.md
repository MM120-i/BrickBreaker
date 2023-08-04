# BrickBreaker
BrickBreaker - Game

The Brick Breaker game is a classic arcade game where players control a paddle to bounce a ball and break bricks arranged at the top of the screen. The objective is to clear all the bricks by hitting them with the ball using the paddle. Players score points for each brick destroyed, and the game features bricks with different colors and point values. The player's score is displayed on the screen, and the game ends when all bricks are destroyed or the player runs out of lives.

**Description:**
The game consists of three main classes: `Gameplay.java`, `Main.java`, and `MapGenerator.java`.

1. **Gameplay.java:** This class handles the game logic and rendering. It extends `JPanel` to create a custom panel for the game. `Gameplay` implements `KeyListener` to capture player input from the keyboard and `ActionListener` to handle game events triggered by a timer.

   - The game starts with the player's paddle and a ball positioned at the bottom center of the screen.
   - Players can move the paddle left and right using the arrow keys on the keyboard to control the ball's direction.
   - The ball bounces off the paddle and the walls of the game area, breaking bricks upon collision.
   - Each brick has a specific color and point value. The player's score increases based on the value of the brick destroyed.
   - The game has a map generator (`MapGenerator.java`) that creates and maintains the arrangement of bricks on the screen.
   - The game ends when all bricks are destroyed (player wins) or when the ball falls below the paddle (player loses).

2. **Main.java:** This class contains the main method and is responsible for creating the game window and initializing the `Gameplay` class. It sets up the game environment and starts the game loop.

3. **MapGenerator.java:** This class handles the creation and drawing of the brick layout. It uses a 2D array to represent the bricks' arrangement, where each element stores the brick's value (color and point value). The `MapGenerator` class allows easy manipulation of the brick layout, such as breaking bricks and updating their values.

**How to Play:**
1. Compile and run the game from the `Main.java` class.
2. Use the left and right arrow keys to move the paddle and prevent the ball from falling below it.
3. Hit the ball with the paddle to bounce it and break the bricks at the top of the screen.
4. Each brick has a color and a point value. The score increases as you destroy bricks.
5. The game is won when all the bricks are destroyed, and it is lost when the ball falls below the paddle (out of lives).
6. Press the "Enter" key to restart the game after winning or losing.

I drew inspiration from a YouTube video by Awais Mirza, titled "Brick Breaker Game in Java." 
The video link is https://www.youtube.com/watch?v=K9qMm3JbOH0&ab_channel=AwaisMirza. 
I utilized the provided source code as a starting point and further developed the game by implementing various modifications of my own.
I am open to receiving any additional suggestions or feedback to improve the game further.
