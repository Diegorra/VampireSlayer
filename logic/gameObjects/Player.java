package logic.gameObjects;
import java.util.Random;

public class Player {
	private int money=50;
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int a) {
		this.money = a;
	}
	
	//si compra un slayer se le reduce el dinero
	public void buySlayer() {
		this.money -= Slayer.COST;
	}
	
	//de forma aleatoria da al jugador 10 monedas
	public void recieveMoney(Random random) {
		if(random.nextInt(2) == 1){
			this.money += 10;
		}
	}
}
