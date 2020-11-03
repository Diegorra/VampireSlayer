package logic.gameObjects;

public class Vampire {
	 
	//creamos una serie de constantes que se mantienen durante toda la ejecucion del progama  
	 private static final int frequency = 2; 
	 private static final int damage=1;
	  
	 //definimos atributos propios del objeto 
	 private int health; 
	 private int pos_x, pos_y;
	 private int turn;

	 //METODOS
	 
	 //usamos un constructor 
	 public Vampire(int pos_x, int pos_y, int turn){ 
		 this.health = 5;
		 this.pos_x =pos_x; 
		 this.pos_y = pos_y;
		 this.turn = turn;
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
	
	 public int getTurn() {
		 return this.turn;
	 }
	 
	 //creamos un metodo que actualiza la vida 
	 public void updateHealth() {
		 this.health--;		
	 }
	 
	 public void updatePosX() {
		 this.pos_x--;
	 }
		
		//mira si hay un slayer en  la siguiente pos, si es asi ataca
		/*public void vampireAttack(int posx, int posy) {
			int pos;
			if(board[posx-1][posy] == 'S') {
				Game.attackVampire(posx-1, posy);
				
			}
		}*/
		
		//metodo moverse
		
		
		
		
		
		
}