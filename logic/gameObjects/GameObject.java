package logic.GameObjects;

import logic.Game;

public abstract class GameObject implements IAttack {
	//referencia a Game
	//metodos/atributos basicos controlar posiciones tablero
	
	protected int pos_x, pos_y;
	protected int health;
	protected Game game;
	protected int damage;
	
	public GameObject (int x, int y, int health, Game game) {
		this.pos_x = x;
		this.pos_y = y;
		this.health = health;
		this.game = game;
		this.damage = 1;
	}

	/**
	 * @return the pos_x
	 */
	public int getPos_x() {
		return pos_x;
	}

	/**
	 * @param pos_x the pos_x to set
	 */
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * @return the pos_y
	 */
	public int getPos_y() {
		return pos_y;
	}

	/**
	 * @param pos_y the pos_y to set
	 */
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	//---------------------------------------
	//---------------------------------------
	
	//método que actualiza la vida
	public void updateHealth(int damage) {
		this.health -= damage;
	}
	
	//método que chequea que está vivo
	public boolean isAlive() {
		return (this.health > 0);
	}

	public abstract void move();
	public abstract boolean arriveEnd();
	public abstract int getZ();

	

}
