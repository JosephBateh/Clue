import java.util.*;
import java.io.*;

public class Action {

	//create the mansion, characters, rooms, and weapons
	private Mansion mansion;
	private Map<String, Room> roomMap;
	private Set<String> roomStrings;
	private CharacterList characters;
	private Map<String, Character> characterMap;
	private Set<String> characterStrings;
	//hash map that contains what location a character is in
	private Map<String, String> characterLocations;
	private String[] answers;

	//constructor
	public Action() {
		mansion = new Mansion();
		roomMap = mansion.getHashMap();
		roomStrings = mansion.getKeySet();
		characters = new CharacterList();
		characterMap = characters.getHashMap();
		characterStrings = characters.getKeySet();
		answers = new String[3];

		//places characters in locations and null in the rest
		characterLocations = new HashMap<String, String>();
		placeCharacters();
		createAnswers();
		//System.out.println(answers[0]);
		//System.out.println(answers[1]);
		//System.out.println(answers[2]);
	}

	//checks if String is in answers
	public boolean checkAnswers(String x) {
		for(int i=0; i<3; i++) {
			String y = Parser.parseText(answers[i]);
			if (y.equals(x)) {
				return true;
			}
		}
		return false;
	}

	//gets a specific answer
	public String getAnswer(int i) {
		return answers[i];
	}

	//creates a string array with person, room, and weapon
	public void createAnswers() {
		Iterator<String> roomIT = roomStrings.iterator();
		Iterator<String> characterIT = characterStrings.iterator();
		int randomRoom = (int)((Math.random())*(roomStrings.size()));
		int randomCharacter = (int)((Math.random())*(characterStrings.size()));
		int counter = 0;
		while(characterIT.hasNext()) {
			String x = characterIT.next();
			if (counter == randomCharacter) {
				answers[0] = x;
			}
			counter++;
		}
		counter = 0;
		while(roomIT.hasNext()) {
			String y = roomIT.next();
			if(counter == randomRoom) {
				answers[1] = y;
				Room z = mansion.getRoom(y);
				while (true) {
					try {
						y = roomIT.next();
						z = mansion.getRoom(y);
						String w = z.getWeapon();
						break;
					} catch (NullPointerException ex) {

					} catch (NoSuchElementException ex) {
						z = roomMap.get("Ballroom");
						break;
					}
				}
				answers[2] = z.getWeapon();
			}
			counter++;
		}
	}

	//get the current weapon name
	public String getWeaponName() {
		Room x = mansion.getRoom(currentLocation());
		return x.getWeapon();
	}

	//get the current weapon description
	public String getWeaponDescription() {
		Room x = mansion.getRoom(currentLocation());
		return x.getWeaponDescription();
	}

	//change the current room
	public void changeRoom(String newRoom) {
		String input = "placeholder";
		mansion.changeRoom(newRoom);
		Iterator<String> it = roomStrings.iterator();
		while (it.hasNext()) {
			String x = it.next();
			String y = Parser.parseText(x);
			if (y.equals(newRoom)) {
				input = x;
			}
		}
		characters.changeCharacter(characterLocations.get(input));
	}

	//returns the name of the current room
	public String currentLocation() {
		return mansion.currentLocation();
	}

	//returns the description of the room
	public String roomDescription() {
		return mansion.roomDescription();
	}

	//returns the room object
	public Room getRoom(String roomName) {
		return mansion.getRoom(roomName);
	}

	//returns the description of the current character
	public String characterDescription() {
		return characters.characterDescription();
	}

	//get the name of the current character
	public String getName() {
		return characters.getName();
	}

	//gets a specific character
	public Character getCharacter(String character) {
		return characters.getCharacter(character);
	}

	//returns a hash map of room names to the room objects
	public Map<String, Room> getRoomMap() {
		return roomMap;
	}

	//returns a set of strings of room names
	public Set<String> getRoomStrings() {
		return roomStrings;
	}

	//returns a hash map of character names to character objects
	public Map<String, Character> getCharacterMap() {
		return characterMap;
	}

	//returns a set of strings of character names
	public Set<String> getCharacterStrings() {
		return characterStrings;
	}

	private void placeCharacters() {
		//place characters in locations
		Iterator<String> roomIT = roomStrings.iterator();
		Iterator<String> characterIT = characterStrings.iterator();
		while (roomIT.hasNext()) {
			String x = roomIT.next();
			String y = "Character";
			if (characterIT.hasNext()) {
				y = characterIT.next();
				//System.out.println("Something worked.");
			} else {
				//System.out.println("placing null");
				y = null;
			}
			characterLocations.put(x, y);
		}
	}

}