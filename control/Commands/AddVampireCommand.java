package control.Commands;

import exceptions.*;
import logic.Game;

public class AddVampireCommand extends Command {

	private int x, y;
	private String iden;
	
	public AddVampireCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	//si la posción es correcta y no sobrepasa el limite de vampiros del nivel, pasa a validar si lo añade, si no muestra mensaje de erro
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(x < game.getLevel().getDimX() && y < game.getLevel().getDimY() && game.getGameOB().getBoard()[y][x] == null) {
				if(game.getGameOB().getContVampiresDead() + game.getGameOB().getContVampiresOnBoard() < game.getLevel().getNumberOfVampires()) {
					game.addVampireManually(this.x, this.y, this.iden);
					return true;
				}
				throw new NoMoreVampiresException("[ERROR]: All vampires are already on board");
			}
			throw new UnvalidPositionException("[ERROR]: Position (" + this.x + ", " + this.y + "): Unvalid position");
		}
		catch(NoMoreVampiresException notVampires) {
			System.out.println(notVampires.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add this Vampire", notVampires));
		}
		catch(DraculaIsAliveException noDracula) {
			System.out.println(noDracula.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add Dracula", noDracula));
		}
		catch(UnvalidPositionException unvalid) {
			System.out.println(unvalid.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add this Vampire", unvalid));
		}
		catch(CommandExecuteException noIden) {
			System.out.println(noIden.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to add this Vampire", noIden));
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])){
			if(commandWords.length == 3) {
				this.iden = "v";
				this.x = Integer.parseInt(commandWords[1]);
				this.y = Integer.parseInt(commandWords[2]);
			}
			else if(commandWords.length == 4){
				this.iden = commandWords[1];
				this.x = Integer.parseInt(commandWords[2]);
				this.y = Integer.parseInt(commandWords[3]);
			}
			else {
				throw new CommandParseException("[ERROR] : Command " + super.name + " : " + Command.incorrectArgsMsg);
			}
			return this;
		}
		return null;
	}

}
