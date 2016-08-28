import java.util.*;

public class Mansion {

	private Weapon wrench;
	private Weapon candlestick;
	private Weapon ethernet;
	private Weapon knife;
	private Weapon revolver;
	private Weapon pipe;
	private Room study;
	private Room library;
	private Room billiard;
	private Room conservatory;
	private Room ballroom;
	private Room basement;
	private Room hall;
	private Room lounge;
	private Room dining;
	private Room kitchen;
	private Room currentRoom;
	private Map<String, Room> roomMap;
	private Set<String> roomStrings;

	public Mansion() {
		roomMap = new HashMap<String, Room>();
		roomStrings = roomMap.keySet();
		wrench = new Weapon("Wrench", "The Wrench is worn down by years of usage. It feels dense.");
		candlestick = new Weapon("Candlestick", "The golden Candlestick is thin and light. The melted wax on top is still warm. The candle is nowhere to be found.");
		ethernet = new Weapon("Ethernet Cable", "The Ethernet Cable is nothing special, unless you want to download large video games at breakneck speeds.");
		knife = new Weapon("Knife", "The Knife is an elegant weapon from a more civilized age.");
		revolver = new Weapon("Revolver", "The Revolver is a Colt .45. One of the bullets is missing.");
		pipe = new Weapon("Lead Pipe", "The Lead Pipe is surprisingly heavy.");
		study = new Room("Study", "In the Study there is an antique desk similar to the resolute desk in the Oval Office. On the desk is an ornate lamp shining light on a tattered copy of the book “One Shot.”", ethernet);
		addToMap(study);
		library = new Room("Library", "In the Library are hundreds of books ranging from the Lord of the Rings trilogy to Agatha Christie. The room smells like a used bookstore. There is a reading lamp next to a royal blue desk chair.", null);
		addToMap(library);
		billiard = new Room("Billiard Room", "Your eyes are drawn to a sizable pool table in the center of the Billiard room, on the right there is a rack of pool cues. One is missing.", pipe);
		addToMap(billiard);
		conservatory = new Room("Conservatory", "There is a noticeable temperature difference in the Conservatory. It is humid and filled with exotic plants that create a pleasurable aroma.", wrench);
		addToMap(conservatory);
		ballroom = new Room("Ballroom", "In one corner of the ballroom is a sleek black grand piano. The carpet is crimson, and on a wall is a painting of the original owner of the mansion. He seems to have been of great importance.", revolver);
		addToMap(ballroom);
		basement = new Room("Basement", "The Basement is dimly lit. There is one hanging lightbulb that is swinging as if someone ran into it recently. There is an old phone on the wall with a number for the local police department.", null);
		addToMap(basement);
		hall = new Room("Hall", "The Hall is brightly lit. It is an open space with an ostentatious circular table in the center. The mahogany floor looks immaculate.", null);
		addToMap(hall);
		lounge = new Room("Lounge", "The Lounge has two extravagant, caramel colored couches. A glass coffee table sits between the couches. On the coffee table is a copy of an unknown book. It has a red stain on it.", null);
		addToMap(lounge);
		dining = new Room("Dining Room", "The centerpiece of the dining room is a massive rectangular table. It is surrounded by maroon chairs with gold trim. The table is set for a meal. One of the knives is missing.", candlestick);
		addToMap(dining);
		kitchen = new Room("Kitchen", "The Kitchen smells of spices. There is a pot on the stove. In the center of the kitchen is an island with a cutting board on it. The cabinet above the sink is open, revealing a bottle of bleach.", knife);
		addToMap(kitchen);
		currentRoom = basement;
	}

	public String currentLocation() {
		return currentRoom.getName();
	}

	public void changeRoom(String newRoom) {
		Iterator<String> it = roomStrings.iterator();
		while (it.hasNext()) {
			String x = it.next();
			String y =  Parser.parseText(x);
			if (y.equals(newRoom)) {
				currentRoom = roomMap.get(x);
			}
		}
	}

	public void addToMap(Room x) {
		roomMap.put(x.getName(), x);
	}

	public Room getRoom(String name) {
		return roomMap.get(name);
	}

	public String roomDescription() {
		return currentRoom.getDescription();
	}

	public Map<String, Room> getHashMap() {
		return roomMap;
	}

	public Set<String> getKeySet() {
		return roomStrings;
	}

	public static void main(String[] args) {
		//Mansion mansion = new Mansion();
	}
}