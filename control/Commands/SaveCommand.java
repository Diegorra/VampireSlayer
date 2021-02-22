package control.Commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class SaveCommand extends Command {
	
	private String fileName;
	
	public SaveCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.save(fileName);
		}
		catch(CommandExecuteException failed) {
			System.out.println(failed.getMessage());
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				this.fileName = commandWords[1];
				return this;
			}
			throw new CommandParseException("[ERROR] : Command " + super.name + " : " + Command.incorrectArgsMsg);
		}
		return null;

	}

}
