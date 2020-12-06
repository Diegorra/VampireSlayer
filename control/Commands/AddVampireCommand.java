package control.Commands;

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
	public boolean execute(Game game) {
		if(game.getGameOB().getContVampiresDead() + game.getGameOB().getContVampiresOnBoard() < game.getLevel().getNumberOfVampires() && x < game.getLevel().getDimX() && y < game.getLevel().getDimY() && game.getGameOB().getBoard()[y][x] == null) {
			game.addVampireManually(this.x, this.y, this.iden);
		}else {
			System.err.println("ERROR, invalid position");
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0]) && (commandWords.length == 3 ||commandWords.length == 4)) {
			if(commandWords.length == 3) {
				this.iden = "v";
				this.y = Integer.parseInt(commandWords[1]);
				this.x = Integer.parseInt(commandWords[2]);
			}
			else{
				this.iden = commandWords[1];
				this.y = Integer.parseInt(commandWords[2]);
				this.x = Integer.parseInt(commandWords[3]);
			}
			return this;
		}
		return null;
	}

}
