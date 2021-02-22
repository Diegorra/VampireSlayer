package control.Commands;

import exceptions.*;
import logic.Game;
import logic.GameObjects.Slayer;

public class AddCommand extends Command {

	private int x, y;

	public AddCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);	
	}

	@Override
	public boolean execute(Game game)throws CommandExecuteException {
		try {
			if(game.getPlayer().getMoney() >= Slayer.COST) {
				game.addSlayer(x, y);
				return true;
			}
			throw new NotEnoughCoinsException("[ERROR]: Add command cost is " + Slayer.COST + ": Not enough coins");
		}
		catch(NotEnoughCoinsException notCoins) {
			System.out.println(notCoins.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add Slayer", notCoins));
		}
		catch(UnvalidPositionException unvalid) {
			System.out.println(unvalid.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add Slayer", unvalid));
		}
		
	}

	@Override
	//comprobamos que la longitud es igual a 3 y que el nombre es correcto
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
				return this;
			}
			throw new CommandParseException("[ERROR] : Command " + super.name + " : " + Command.incorrectArgsMsg);
		}
		return null;
	}

}
