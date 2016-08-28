import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Game {
	private static boolean gameOver;
	private static String victim;

	public static void main(String[] args) {
		victim = "Mr. Body";
		gameOver = false;
		GameProcessor gp = new GameProcessor();
		for (int i = 0; i<10; i++) {
			System.out.println();
		}
		System.out.println("What is your name?");
		Scanner input = new Scanner(System.in);
		victim = input.nextLine();
		for (int i = 0; i<10; i++) {
			System.out.println();
		}
		System.out.println("Welcome to the Mansion.");
		System.out.println();
		System.out.println(victim + " has been murdered! If you correctly report the culprit, the location, and the weapon to the police, your reward is $5,000.");
		System.out.println("Look around the house for clues. You can make a guess at any time, but you only have " + gp.getGuesses() + " guesses and one accuasation.");
		System.out.println("You can type 'help' or 'map' for useful information.");
		System.out.println();
		System.out.println("You are in the basement.");
		//creates scanner to take in user commands
		String command = "";
		//Scanner input = new Scanner(System.in);
		//stops the game if command = terminate
		while (!gameOver) {
			//ask user for a command
			System.out.println();
			System.out.println("///////////////////////");
			System.out.println("What do you want to do?");
			command = input.nextLine();
			if ((command.equals("terminate"))) {
				break;
			}
			if (command.equals("map")) {
				paintMap();
			}
			if ((command.equals("help"))) {
				System.out.println(help());
			}
			System.out.println();
			gp.run(command);
		}
		System.out.println();
		System.out.println("Game Over.");
	}

	public static void endGame() {
		gameOver = true;
	}

	public static String help() {
		String text = "To make an accuasation you must be in the basement, and call the police. You can be in any room to make a guess. You can show the map by typing 'map'. You can go to another room by typing 'go' followed by the name of the room. You can examine objects and rooms. You can look around rooms for a description.";
		return text;
	}

	public static void paintMap() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 800);
        frame.setTitle("Breakout");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		ImageIcon map;

		public GamePanel() {
			setFocusable(true);
			map = new ImageIcon("clue_board.jpg");
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;

			g2.drawImage(map.getImage(), 0, 0, null);
		}
	}


}

/* things left to do

2. jar file

*/