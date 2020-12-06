package control.Commands;

import logic.Game;

public class SuperCoinsCommand extends Command {

	public SuperCoinsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.getPlayer().setMoney(game.getPlayer().getMoney() + 1000);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
