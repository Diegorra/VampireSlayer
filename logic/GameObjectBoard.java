//hace referencia al tablero
package logic;
import logic.Game;
import logic.list.SlayerList;
import logic.list.VampireList;
import java.util.Random;

public class GameObjectBoard {
	
	
	public static void update(int turn) {
		//generar vampiro
		addVampire(turn);
		//mueve vampiro
		for(int i=0; i < VampireList.getVLcont(); i++) {
			moveVampire(turn, i);
		}
	}

	public static void addSlayer(int x, int y) {
		if(Game.player.getMoney() >= 50 && x != Game.level.getDimX() && Game.board[x][y] == "N")/*tiene dinero, no es ultima columna y la casilla está vacia*/{
			Game.board[x][y] = "S"; //añadimos slayer al tablero
			SlayerList.newSlayer(x, y);//añadimos slayer a la lista
			Game.player.buySlayer();//restamos dinero jugador		
		}else {
			System.out.print(control.Controller.invalidPositionMsg);
		}
			
	}

	public static void addVampire(int turn) {
		Random random = new Random();
		int posY=0;
		if((VampireList.getVLcont() <= Game.level.getNumVampires()) && /*OJO*/random.equals(Game.level.getVampireFreq()))/*si no hay el max de vampiros en el tablero y toca introducir vampiro*/ {
				posY = random.nextInt(Game.level.getDimY());
				if(Game.board[Game.level.getDimX()-1][posY] != "V")/*si en la casilla no hay un vampiro*/ {
					Game.board[Game.level.getDimX()-1][posY] = "V";
					VampireList.newVampire(Game.level.getDimX(), posY, turn);
				}
		}
	}

	public static void removeDead(int x, int y)/*pone a null la posicion especificada*/ {
		Game.board[x][y] = "N";
	}
	
	public static void moveVampire(int actualTurn, int i)/*comprobamos que puede mover y mueve*/ {
		if(actualTurn % 2 == VampireList.vampireList[i].getTurn() % 2) {
			VampireList.vampireList[i].updatePosX();
		}
	}
}
