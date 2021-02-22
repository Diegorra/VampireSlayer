package logic.GameObjects;

import logic.Game;

public class ExplosiveVampires extends Vampire {

	private boolean explosiveActive;
	
	public ExplosiveVampires(int x, int y, Game game, int turn) {
		super(x, y, game, turn);
		this.explosiveActive = true;
	}

	/**
	 * @return the explosiveActive
	 */
	public boolean isExplosiveActive() {
		return explosiveActive;
	}

	/**
	 * @param explosiveActive the explosiveActive to set
	 */
	public void setExplosiveActive(boolean explosiveActive) {
		this.explosiveActive = explosiveActive;
	}

}
