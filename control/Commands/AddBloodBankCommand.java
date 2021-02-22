package control.Commands;

import exceptions.*;
import logic.Game;
import logic.GameObjects.BloodBank;


public class AddBloodBankCommand extends Command {

	private int x, y, z;

	public AddBloodBankCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);	
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(game.getPlayer().getMoney() >= BloodBank.COST) {
				game.addBloodBank(x, y, z);
				game.update();
				return true;
			}
			throw new NotEnoughCoinsException("[ERROR]: BloodBank command cost is " + BloodBank.COST + ": Not enough coins");
		}
		catch(NotEnoughCoinsException notCoins) {
			System.out.println(notCoins.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add BloodBank", notCoins));
		}
		catch(UnvalidPositionException unvalid) {
			System.out.println(unvalid.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add BloodBank", unvalid));
		}
		
	}

	@Override
	//comprobamos que la longitud es igual a 3 y que el nombre es correcto
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 4) {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
				z = Integer.parseInt(commandWords[3]);
				return this;
			}
			throw new CommandParseException("[ERROR]: Command " + super.name + ": " + Command.incorrectArgsMsg);
		}
		return null;
	}
}
