package control.Commands;

import logic.Game;

public class AddBloodBankCommand extends Command {

	private int x, y, z;

	public AddBloodBankCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);	
	}

	@Override
	public boolean execute(Game game) {
		game.addBloodBank(x, y, z);
		game.update();
		return true;
	}

	@Override
	//comprobamos que la longitud es igual a 3 y que el nombre es correcto
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0]) && commandWords.length == 4) {
			y = Integer.parseInt(commandWords[1]);
			x = Integer.parseInt(commandWords[2]);
			z = Integer.parseInt(commandWords[3]);
			return this;
		}
		return null;
	}
}
