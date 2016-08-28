import java.util.*;

public class CharacterList {
	private Character goldberg;
	private Character white;
	private Character green;
	private Character peacock;
	private Character scarlett;
	private Character mustard;
	private Map<String, Character> characterMap;
	private Set<String> characterStrings;
	private Character currentCharacter;

	public CharacterList() {
		characterMap = new HashMap<String, Character>();
		goldberg = new Character("Mr. Goldberg", "Mr. Goldberg is a hipster wannabe. He is a PhD student in computer science. He wears gold rimmed glasses.");
		addToMap(goldberg);
		white = new Character("Mrs. White", "Mrs. White seems like an innocent old lady. Her curly, powder white hair is from a previous generation.");
		addToMap(white);
		green = new Character("Mr. Green", "Mr. Green is a large man who would be scary in a dark alley. He wears a green jacket.");
		addToMap(green);
		peacock = new Character("Mrs. Peacock", "Mrs. Peacock is the wife of a wealthy dignitary. Her blue dress is extravagant and impressive.");
		addToMap(peacock);
		scarlett = new Character("Miss Scarlett", "Miss Scarlett is always the most attractive woman in the room. Her red dress accentuates her tempting curves, and matches her rosy red lips.");
		addToMap(scarlett);
		mustard = new Character("Colonel Mustard", "An old Army Colonel, Colonel Mustard is a disciplined man. His perfectly pressed uniform adds to his aura of authority.");
		addToMap(mustard);
		characterStrings = characterMap.keySet();
		currentCharacter = goldberg;
	}

	public Character getCharacter(String x) {
		return characterMap.get(x);
	}

	public String getName() {
		return currentCharacter.getName();
	}

	public String characterDescription() {
		return currentCharacter.getDescription();
	}

	public void changeCharacter(String newCharacter) {
		Iterator<String> it = characterStrings.iterator();
		while (it.hasNext()) {
			String x = it.next();
			if (x.equals(newCharacter)) {
				currentCharacter = characterMap.get(x);
				break;
			} else {
				currentCharacter = characterMap.get(newCharacter);
			}
		}
	}

	public Map<String, Character> getHashMap() {
		return characterMap;
	}

	public Set<String> getKeySet() {
		return characterStrings;
	}

	public void addToMap(Character x) {
		characterMap.put(x.getName(), x);
	}

	public static void main(String[] args) {
		
	}
}