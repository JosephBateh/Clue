public class Parser {
	public static String parseText(String command) {
		command = command.toLowerCase();
		command = command.replace("the ", "");
		command = command.replace("a ", "");
		command = command.replace("an ", "");
		command = command.replace("to ", "");
		command = command.replace(" room", "");
		command = command.replace("mr. ", "");
		command = command.replace("miss ", "");
		command = command.replace("mrs. ", "");
		command = command.replace("colonel ", "");
		command = command.replace("lead ", "");
		command = command.replace("mr ", "");
		command = command.replace("mrs ", "");
		command = command.replace("bleach ", "");
		command = command.replace("pool ", "");
		return command;
	}
}