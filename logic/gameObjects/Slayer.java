package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject{
	
	public static final int COST = 50;
	
	public Slayer(int x, int y, Game game) {
		super(x, y, 3, game);
	}
	
	
	@Override
	public void attack() {
		int i = this.getPos_x() + 1;
		boolean attackable = false;
		if(isAlive()) {
			//recorermos la fila buscando un vampiro al que poder atacar
			while(i < game.getLevel().getDimX() && !attackable) {
				IAttack other = game.getAttackableInPosition(i, super.pos_y, "defensivo"); 
				if(other != null) {//en caso de encontrarlo recive el ataque del slayer
					other.receiveSlayerAttack(damage);
					attackable = true;
				}
				else if(game.getGameOB().getBoard()[super.pos_y][i] == "S") {/*si delante tiene un slayer no puede no podrá atacar*/
					attackable = true;
				}
				i++;
			}
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean arriveEnd() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//si un slayer es atacado se actualiza su vida
	public boolean receiveVampireAttack(int damage) {
		super.updateHealth(damage);
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		super.health = 0;
		return true;
	}
}
