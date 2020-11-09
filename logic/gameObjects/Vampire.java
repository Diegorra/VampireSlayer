package logic.gameObjects;

public class Vampire {
	 
	//creamos una serie de constantes que se mantienen durante toda la ejecucion del progama  
	 public static final int FREQUENCY = 2; 
	 public static final int DAMAGE=1;
	  
	 //definimos atributos propios del objeto 
	 private int health; 
	 private int pos_x, pos_y;
	 private int turn;

	 //METODOS
	 
	 //usamos un constructor 
	 public Vampire(int pos_y, int pos_x, int turn){ 
		 this.health = 5;
		 this.pos_x =pos_x; 
		 this.pos_y = pos_y;
		 this.turn = turn;
	 }
	
	 //creamos métodos getter	
	 public int getHealth() {
		 return this.health;
	}
	
	 public int getPosY() {
		 return this.pos_y;
	 }
	
	 public int getPosX() {
		 return this.pos_x;
	 }
	
	 public int getTurn() {
		 return this.turn;
	 }
	 
	 //creamos métodos setter
	 public void setHealth(int a) {
		 this.health = a;
	 }
	 
	 public void setPosX(int x) {
		 this.pos_x = x;
	 }
	 
	 public void setPosY(int y) {
		 this.pos_y = y;
	 }
	 
	 public void setTurn(int a) {
		 this.turn = a;
	 }
	 
	 //creamos un metodo que actualiza la vida 
	 public void updateHealth() {
		 this.health--;		
	 }
	 
	 //creamos un método que actualiza la pos
	 public void updatePosX() {
		 this.pos_x--;
	 }
		
		
		
		
		
		
		
		
}