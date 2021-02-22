
package control.Commands;

import exceptions.CommandParseException;

public class CommandGenerator {
	
	private static Command[] avaiableCommands = {
			new AddCommand("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y"),
			new AddBloodBankCommand("bank", "b", "[b]ank <x> <y> <z>", "add a bloodbank in position x, y"),
			new HelpCommand("help", "h", "[h]elp", "show this help"),
			new ResetCommand("reset", "r", "[r]eset", "reset game"),
			new ExitCommand("exit", "e", "[e]xit", "exit game"),
			new UpdateCommand("none", "n", "[n]one | []", "update"),
			new GarlicPushCommand("garlic", "g",  "[g]arlic", "garlic push attack"),
			new LightFlashCommand("light", "l", "[l]ight", "light flash attack"),
			new SuperCoinsCommand("coins", "c", "[c]oins", "for testing the user can add 1000 coins to the wallet"),
			new AddVampireCommand("vampire", "v", "[v]vampire [<type>] <x> <y>", "for testing the user can create a vampire"),
			new SerializeCommand("serialize", "z", "[z]serialize", "serializes the game status"),
			new SaveCommand("save", "s", "[s]save", "saves into a file the game status")
	};

	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		//buscamos el comando y en caso de encontrarlo lo retornamos sino devolvemos null
		for(Command command : avaiableCommands) {
			if(command.parse(commandWords) != null)
				return command;
		}
		throw new CommandParseException("[ERROR]: Invalid name");
	}
	
	public static String commandHelp() {
		String help = "Available commands:" + "\n";
		for(Command command : avaiableCommands)  {
			help += command.helpText();
		}
		return help;
	}
	
}
