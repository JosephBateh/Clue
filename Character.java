public class Character {
	
	private String characterName;
	private String characterDescription;

	//constructor
	public Character(String n, String d) {
		characterName = n;
		characterDescription = d;
	}
	//returns the name of the character
	public String getName() {
		return characterName;
	}
	//returns the description of the character
	public String getDescription() {
		return characterDescription;
	}

	public static void main(String[] args) {
		//Character goldberg = new Character("Mr. Goldberg", "Teacher");
		//System.out.println(goldberg.getName());
	}
}