package control.Commands;

import logic.Game;

public class AddCommand extends Command {

	private int x, y;

	public AddCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);	
	}

	@Override
	public boolean execute(Game game) {
		game.addSlayer(x, y);
		game.update();
		return true;
	}

	@Override
	//comprobamos que la longitud es igual a 3 y que el nombre es correcto
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0]) && commandWords.length == 3) {
			x = Integer.parseInt(commandWords[2]);
			y = Integer.parseInt(commandWords[1]);
			return this;
		}
		return null;
	}

}
