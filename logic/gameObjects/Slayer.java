package logic.gameObjects;

public class Slayer {
	
	 //creamos una serie de constantes que se mantienen durante toda la ejecucion del progama 
	 public static final int COST=50; 
	 public static final int DAMAGE=1;
	  
	 //definimos atributos propios del objeto 
	 private int health; 
	 private int pos_x, pos_y;

	 
	 
	//METODOS
	 
	 //usamos un constructor 
	 public Slayer(int pos_y, int pos_x){ 
		 this.health = 3;
		 this.pos_x =pos_x; 
		 this.pos_y = pos_y;
	 }
	 
	//creamos métodos getter
	public int getHealth() {
		return this.health;
	}
	
	public int getPosY() { //Filas
		return this.pos_y;
	}
	
	public int getPosX() { //Columnas
		return this.pos_x;
	}
	
	//creamos métodos setter
	public void setHealth(int a) {
		this.health= a;
	}
	
	public void setPosX(int x) {
		this.pos_x = x;
	}
	
	public void setPosY(int y) {
		this.pos_y = y;
	}
	
	//creamos metodo que actualiza la vida 
	public void updateHealth() {
		this.health--;		
	}
	
	
		
}

