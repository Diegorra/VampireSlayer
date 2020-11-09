//hace referencia al tablero
package logic;

import logic.list.SlayerList;
import logic.list.VampireList;
import java.util.Random;

public class GameObjectBoard {
	
	private Level level;
	private SlayerList sl;
	private VampireList vl;
	private String [][] board;
	
	public GameObjectBoard(Level level) {
		this.level = level;
		this.sl = new SlayerList(this.level);
		this.vl = new VampireList(this.level);
		this.board = new String[this.level.getDimY()][this.level.getDimX()];
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @return the sl
	 */
	public SlayerList getSl() {
		return sl;
	}

	/**
	 * @return the vl
	 */
	public VampireList getVl() {
		return vl;
	}

	/**
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	//---------------------------------------------------------------------	
	
	public void update(int turn, Random random) {

		//mueve vampiro
		for(int i=0; i < vl.getVLcont(); i++) {
			moveVampire(turn, i);
		}
		//generar vampiro
		addVampire(turn, random);
	}

	public void addSlayer(int y, int x) {
		if(x != level.getDimX()-1 && board[y][x] == "N")/*tiene dinero, no es ultima columna y la casilla está vacia*/{
			board[y][x] = "S"; //añadimos slayer al tablero
			sl.newSlayer(y, x);//añadimos slayer a la lista		
		}else {
			System.out.println(control.Controller.invalidPositionMsg);
		}
			
	}

	public void addVampire(int turn, Random random) {
		//Random random = new Random();
		if((vl.getVLcontmuertos()+ vl.getVLcont() < level.getNumVampires()) &&	random.nextDouble() < level.getVampireFreq())/*si no hay el max de vampiros en el tablero y toca introducir vampiro*/ {
				int posY = random.nextInt(level.getDimY());
				if(board[posY][level.getDimX()-1] != "V")/*si en la casilla no hay un vampiro*/ {
					board[posY][level.getDimX()-1] = "V";
					vl.newVampire(posY,level.getDimX() - 1, turn);
				}
		}
	}

	public void removeDead(int y, int x)/*pone a null la posicion especificada*/ {
		board[y][x] = "N";
	}
	
	public void moveVampire(int actualTurn, int i)/*comprobamos que puede mover y mueve*/ {
		if((actualTurn != vl.getVampireList()[i].getTurn()) && ((actualTurn % 2) == (vl.getVampireList()[i].getTurn() % 2)) && (board[vl.getVampireList()[i].getPosY()][vl.getVampireList()[i].getPosX()-1] == "N")) {
			board[vl.getVampireList()[i].getPosY()][vl.getVampireList()[i].getPosX()] = "N";//actualizamos el tablero
			vl.getVampireList()[i].updatePosX();//actualizamos la info del vampiro
			board[vl.getVampireList()[i].getPosY()][vl.getVampireList()[i].getPosX()] = "V";
		}
	}
	
	public String returnBox(int y, int x)/*dadas unas coordenadas retorna el contenido de la casilla*/ {
		return this.board[y][x];
	}

}
