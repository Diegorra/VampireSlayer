package logic.gameObjects;

public class Slayer {
	
	 //creamos una serie de constantes que se mantienen durante toda la ejecucion del progama 
	 private static final int cost=50; 
	 private static final int damage=1;
	  
	 //definimos atributos propios del objeto 
	 private int health; 
	 private int pos_x, pos_y;

	 
	 
	//METODOS
	 
	 //usamos un constructor 
	 public Slayer(int pos_x, int pos_y){ 
		 this.health = 3;
		 this.pos_x =pos_x; 
		 this.pos_y = pos_y;
	 }
	 
	//creamos un getter
	public int getHealth() {
		return this.health;
	}
	
	public int getPosX() {
		return this.pos_x;
	}
	
	public int getPosY() {
		return this.pos_y;
	}
	
	public static int getCost() {
		return cost;
	}
	
	//creamos metodo que actualiza la vida 
	public void updateHealth() {
		this.health--;		
	}
	
	//metodo attack, comprueba si puede atacar y ataca
	/*public void slayerAttack() {
		int pos;
		for(int i= pos_x+1; i < level.getDimX(); i++) {
			if(board[i][pos_y] == 'V') {
				Game.attackSlayer(i, pos_y);
			}
		}
		
	}

	*/
	
	
}
