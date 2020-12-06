package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0]) || commandWords[0].isEmpty()) {
			return this;
		}
		return null;
	}

}
