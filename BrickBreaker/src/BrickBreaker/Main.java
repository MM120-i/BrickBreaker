package BrickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Breakout Ball");
		Gameplay gamePlay = new Gameplay();
		
		setupFrame(frame);
		addGameplayToFrame(frame, gamePlay);
	}

	private static void setupFrame(JFrame frame) {
		
		frame.setBounds(10, 10, 700, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void addGameplayToFrame(JFrame frame, Gameplay gamePlay) {
		
		frame.add(gamePlay);
	}

}

