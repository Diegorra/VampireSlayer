package logic.GameObjects;

import logic.Game;

public class Dracula extends Vampire{

	public Dracula(int x, int y, Game game, int turn) {
		super(x, y, game, turn);
		// TODO Auto-generated constructor stub
	}
	
	//sobrescritura del metodo attack
	public void attack() {
		if(isAlive()) {
			//comprobamos si hay un slayer en la casilla de su izquierda
			IAttack other = game.getAttackableInPosition(super.pos_x-1,super.pos_y, "atacante");
			if(other != null) {//sin lo encontramos recive el ataque del vampire
				other.receiveDraculaAttack();
				//si elimina un blood bank actualizamos su vida
				/*if(game.getGameOB().getBoard()[super.pos_y][super.pos_x-1] == "B") {
					super.health+=1;
				}*/
			}
		}
	}
	
	///sobrescritura del metodo
	public boolean receiveLightFlash() {
		return true;
	}

}
