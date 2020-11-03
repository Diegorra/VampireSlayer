package logic.gameObjects;
import java.util.Random;

public class Player {
	private int money=50;
	
	public int getMoney() {
		return this.money;
	}
	
	//si compra un slayer se le reduce el dinero
	public void buySlayer() {
		this.money -= Slayer.getCost();
	}
	
	//de forma aleatoria da al jugador 10 monedas
	public void recieveMoney() {
		Random random = new Random();
		if(random.nextInt(2) == 1){
			this.money += 10;
		}
	}
}
