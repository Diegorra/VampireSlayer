package control.Commands;

import exceptions.*;
import logic.Game;

public class GarlicPushCommand extends Command{

	public GarlicPushCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(game.getPlayer().getMoney() >= 10) {
				game.garlicPush();
			//game.update();
			return true;
			}
			throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is 10: Not enough coins");
			
		}
		catch(NotEnoughCoinsException notCoins) {
			System.out.println(notCoins.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to push the vampires", notCoins));
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
