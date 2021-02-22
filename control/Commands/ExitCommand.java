package control.Commands;

import exceptions.CommandParseException;
import logic.Game;

public class ExitCommand extends Command {

	public ExitCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.setUserExit(true);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
