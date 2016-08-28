public class Weapon {
	
	private String weaponName;
	private String weaponDescription;

	//constructor
	public Weapon(String n, String d) {
		weaponName = n;
		weaponDescription = d;
	}
	//returns the name of the weapon
	public String getName() {
		return weaponName;
	}
	//returns the description of the weapon
	public String getDescription() {
		return weaponDescription;
	}

	public static void main(String[] args) {
		//Weapon revolver = new Weapon("Revolver", "Description");
		//System.out.println(revolver.getName());
	}
}