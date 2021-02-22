package control.Commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class SerializeCommand extends Command{

	public SerializeCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);	
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println(game.serialize());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
