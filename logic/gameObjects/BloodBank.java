package logic.GameObjects;

import logic.Game;

public class BloodBank extends GameObject {

	public static final int COST = 10;
	private int z;
	
	public BloodBank(int x, int y,int z, Game game) {
		super(x, y, 1, game);
		this.z = z;
	}
	
	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
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
	
	//si un blood bank es atacado se decrementa su vida
	public boolean receiveVampireAttack(int damage) {
		super.updateHealth(damage);
		return true;
	}

	public boolean receiveDraculaAttack() {
		super.health = 0;
		return true;
	}

	
}
