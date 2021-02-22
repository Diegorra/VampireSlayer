package control.Commands;

import exceptions.*;
import logic.Game;


public class LightFlashCommand extends Command {

	public LightFlashCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(game.getPlayer().getMoney() >= 50) {
				game.lightFlash();
				game.update();
				return true;
				}
				throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is 50: Not enough coins");
		}
		catch(NotEnoughCoinsException notCoins) {
			System.out.println(notCoins.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to light flash", notCoins));
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
