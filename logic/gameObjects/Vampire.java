package logic.GameObjects;

import logic.Game;

public class Vampire extends GameObject {

	public static final int FREQUENCY = 2; 
	private int turn;
	
	public Vampire(int x, int y, Game game, int turn) {
		super(x, y, 5, game);
		this.turn = turn;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}


	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	@Override
	public void attack() {
		if(isAlive()) {
			//comprobamos si hay un slayer en la casilla de su izquierda
			IAttack other = game.getAttackableInPosition(super.pos_x-1,super.pos_y, "atacante");
			if(other != null) {//sin lo encontramos recive el ataque del vampire
				other.receiveVampireAttack(damage);
				//si elimina un blood bank actualizamos su vida
				/*if(game.getGameOB().getBoard()[super.pos_y][super.pos_x-1] == "B") {
					super.health+=1;
				}*/
			}
		}
	}
	
	@Override
	//movimiento del vampiro
	public void move() {
		//comprueba si le toca mover y puede moverse y en caso de ser true mueve 
		if(game.getCycles() != this.turn && (game.getCycles() % 2 == this.turn % 2) && game.getGameOB().getList().searchObject(super.pos_x-1, super.pos_y) == -1) {
			game.getGameOB().getBoard()[super.pos_y][super.pos_x-1] = game.getGameOB().getBoard()[super.pos_y][super.pos_x];
			game.getGameOB().getBoard()[super.pos_y][super.pos_x] = null;
			super.pos_x -=1;
		}
	}
	
	@Override
	//si un vampiro llega al final ganan los vampiros
	public boolean arriveEnd() {
		if(super.pos_x == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//cuando un vampiro recive un ataque se actualiza su vida
	public boolean receiveSlayerAttack(int damage) {
		super.updateHealth(damage);
		return true;
	}
	
	//el vampiro se ve empujado a la derecha si está en la última columna muere
	public boolean receiveGarlicPush() {
		/*if(super.pos_x == game.getLevel().getDimX()-1) {
			super.health = 0; //ponemos su vida a 0 para eliminarlo de la lista
		}else*/ 
		if(game.getGameOB().getBoard()[super.pos_y][super.pos_x+1] == null) {
			game.getGameOB().getBoard()[super.pos_y][super.pos_x+1] = game.getGameOB().getBoard()[super.pos_y][super.pos_x];
			game.getGameOB().getBoard()[super.pos_y][super.pos_x] = null;
			super.pos_x++;
		}
		this.turn = game.getCycles();//se quedan aturdidos, se resetea el turno
		return true;
	}
	
	//se eliminan todos los vampiros salvo dracula
	public boolean receiveLightFlash() {
		super.health = 0;
		return true;
	}

}
