import java.io.*;
import java.util.*;

public class GameProcessor {

	private Action actions;
	private Map<String, Room> roomMap;
	private Set<String> roomStrings;
	private int guesses;

	public void run(String command) {

		//format command
		command = Parser.parseText(command);

		//split command
		String[] parsedCommand = command.split(" ");
		
		//determine action
		String action = determineAction(parsedCommand);
		//System.out.println(action);

		//change game
		if (isRoom(action)) {
			actions.changeRoom(action);
		}

		//if the action isn't a room, then show the action
		if (!isRoom(action)) {
			if (action == null) {
				//needed case to not end the game
			} else if (action.equals("win") || action.equals("nope")) {
				if (action.equals("win")) {
					System.out.println("You win! I lied about the money.");
					Game.endGame();
				} else {
					System.out.println("You lose! No money for you.");
					Game.endGame();
				}
			} else {
				if (action != null) {
					//prints out the description of the current room
					System.out.println(actions.roomDescription());
					System.out.println();
					//Prints out the weapon
					System.out.println(action);
					System.out.println();
					try {
						//prints out the name of the character and their description
						System.out.println(actions.getName() + " is there.");
						System.out.println(actions.characterDescription());
					} catch (NullPointerException ex) {
						//occurs if nobody is in the room
						System.out.println("There is nobody in the " + actions.currentLocation() + ".");
					}
				}
			}
		}

		//return printout
		System.out.println("You are in the " + actions.currentLocation() + ".");
	}

	public String determineAction(String[] parsedCommand) {
		//try catch thing...
		String command = parsedCommand[0];
		String output = "";

		if (command.equals("examine") || command.equals("drink") || command.equals("eat") || command.equals("read") || command.equals("play") || command.equals("use") ) {
			output = examine(parsedCommand);
			return output;
		}
		if (command.equals("help")) {
			return null;
		}

		//calling and guessing
		try {
			int counter;
			if(command.equals("call") || command.equals("guess")) {
				//need character, room, weapon
				if (command.equals("call") && actions.currentLocation().equals("Basement")) {
					counter = 0;
					if (actions.currentLocation().equals("Basement")){
						for (int i = 1; i < 4; i++) {
							String x = Parser.parseText(parsedCommand[i]);
							if (actions.checkAnswers(x)) {
								counter++;
							}
						}
					}
					if (counter == 3) {
						output = "win";
					}
					if (counter != 3) {
						output = "nope";
					}
					return output;
				} else {
					counter = 0;
					Boolean[] tracker = {false, false, false};
					if (guesses==0) {
						System.out.println("You cannot make anymore guesses. You can only make an accusation.");
						return null;
					}
					for (int i = 1; i < 4; i++) {
						String x = Parser.parseText(parsedCommand[i]);
						if (actions.checkAnswers(x)) {
							counter++;
							tracker[i-1] = true;
						}
					}
					if (counter > 0) {
						int rand = (int)(Math.random()*3);
						while (tracker[rand] == false) {
							rand = (int)(Math.random()*3);
						}
						System.out.println(actions.getAnswer(rand));
						output = null;
					}
					if (counter == 0) {
						System.out.println("None of these are correct.");
						output = null;
					}
					if(guesses > 0) {
						guesses--;
						System.out.println("You have " + guesses + " guesses left.");
					}
					return output;
				}
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("The format to make an accusation or guess must be: 'call character room weapon' or 'guess character room weapon'.");
		}

		if (command.equals("go") || command.equals("look")) {
			try {
				//If first command is "go"
				if (command.equals("go")) {
					//check if next command is a room
					if (isRoom(parsedCommand[1])) {
						//go to room
						output = parsedCommand[1];
					}
					//System.out.println(output);
					return output;
				//if first command is "examine"
				} else if (command.equals("look")) {
					Room x = actions.getRoom(actions.currentLocation());
					output = x.getWeaponDescription();
					return output;
				} else {
					System.out.println();
					System.out.println("You can't do that right now.");
					return null;
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
				return "There was a problem.";
			} catch (NullPointerException ex) {
				return "There is no weapon.";
			}
		}
		System.out.println("You can't do that.");
		return null;
	}

	//returns a string with the name of the room.
	public String determineRoom(String room) {
		if (room.equals("billiard")) {
			return room + " room";
		} else if (room.equals("dining")) 
			return room + " room";
		else {
			return room;
		} 
	}

	//determines if the string is a room
	public boolean isRoom(String test) {
		Iterator<String> it = roomStrings.iterator();
		while (it.hasNext()) {
			String x = it.next();
			String y = Parser.parseText(x);
			//System.out.println(y);
			if (y.equals(test)) {
				//System.out.println(test);
				return true;
			}
		}
		return false;
	}

	public String examine(String[] parsedCommand) {
		String command = parsedCommand[0];
		String getBack = "Shouldn't you get back to solving the murder?";
		try{
			//examine the kitchen
			if (actions.currentLocation().equals("Kitchen")) {
				if (command.equals("examine") && parsedCommand[1].equals("bottle")) {
					System.out.println("The bottle does not include bleach. It contains the nectar of the Gods.");
					return null;
				} else if (command.equals("drink")) {
					System.out.println("You are now omniscient.");
					System.out.println("You discover that " + actions.getAnswer(0) + " committed the crime in the " + actions.getAnswer(1) + " with the " + actions.getAnswer(2));
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Study")) {
				if (command.equals("examine") && parsedCommand[1].equals("book")) {
					System.out.println("One Shot is written by Lee Child. It is a good book, you should read it sometime.");
					return null;
				} else if (command.equals("read")) {
					System.out.println(getBack);
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Billiard Room")) {
				if (command.equals("examine") && parsedCommand[1].equals("table")) {
					System.out.println("The pool table is set up for a game.");
					return null;
				} else if (command.equals("play")) {
					System.out.println(getBack);
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Library")) {
				if (command.equals("examine") && parsedCommand[1].equals("chair")) {
					System.out.println("It is blue, and rather royal.");
					return null;
				} else if (command.equals("read")) {
					System.out.println(getBack);
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Ballroom")) {
				if (command.equals("examine") && parsedCommand[1].equals("painting")) {
					System.out.println("The man in the painting has dark brown hair, fair skin, and is dressed well.");
					return null;
				} else if (command.equals("play")) {
					System.out.println(getBack);
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Basement")) {
				if (command.equals("examine") && parsedCommand[1].equals("phone")) {
					System.out.println("This could be useful later.");
					return null;
				} else if (command.equals("use")) {
					System.out.println("To make an accusation you must call the police.");
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Lounge")) {
				if (command.equals("examine") && parsedCommand[1].equals("book")) {
					System.out.println("You open the book and discover the title is Dracula.");
					return null;
				} else if (command.equals("read")) {
					System.out.println(getBack);
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else if(actions.currentLocation().equals("Dining Room")) {
				if (command.equals("examine") && parsedCommand[1].equals("meal")) {
					System.out.println("The meal includes a succulent filet with some mashed potatoes.");
					return null;
				} else if (command.equals("eat")) {
					System.out.println("You eat the meal. It was glorious. Now get back to solving the murder.");
					return null;
				} else {
					System.out.println("You can't do that.");
					return null;
				}
			} else {
				System.out.println("You can't do that right now.");
				return null;
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Examine what?");
		}
		return null;
	}

	public GameProcessor() {
		//create mansion
		actions = new Action();
		//get the roomMap
		roomMap = actions.getRoomMap();
		//get the roomStrings
		roomStrings = actions.getRoomStrings();

		guesses = 10;
	}

	public int getGuesses() {
		return guesses;
	}

	public static void main(String[] args) {
		
	}
}