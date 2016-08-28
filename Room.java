public class Room {
	
	private String name;
	private String description;
	private Weapon weapon;

	public static void main(String[] args) {
		//Room study = new Room("Study", "Candlestick");
		//String test = study.getWeapon();
		//System.out.println(test);
	}
	//constructor
	public Room(String n, String d, Weapon w) {
		name = n;
		description = d;
		weapon = w;
	}
	//returns the weapon for the room
	public String getWeapon() {
		return weapon.getName();
	}
	//gets weapon description
	public String getWeaponDescription() {
		return weapon.getDescription();
	}
	//returns the name of the room
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return "The " + name + " has the " + weapon + ".";
	}

}