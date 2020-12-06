package logic.GameObjects;
import java.util.Random;

public class Player {
	private int money=50;
	private Random random;
	
	public Player(Random random) {
		money = 50;
		this.random = random;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int a) {
		this.money = a;
	}
	
	//de forma aleatoria da al jugador 10 monedas
	public void recieveMoney() {
		if(random.nextInt(2) == 1){
			this.money += 10;
		}
	}
}
