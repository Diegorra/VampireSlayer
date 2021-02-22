package logic;

import java.util.Random;

import logic.GameObjects.Dracula;
import logic.GameObjects.ExplosiveVampires;
import logic.GameObjects.GameObject;
import logic.GameObjects.Vampire;
import logic.list.GameObjectList;

public class GameObjectBoard {
	
	private Level level;
	private Random random;
	private GameObjectList list;
	private String [][] board;
	private Game game;
	private int contVampiresOnBoard;
	private int contVampiresDead;
	private int contDracula;
	
	
	public GameObjectBoard(Level level, Random random, Game game) {
		this.level = level;
		this.random = random;
		list = new GameObjectList();
		this.board = new String[this.level.getDimY()][this.level.getDimX()];
		this.game = game;
		this.contVampiresOnBoard = 0;
		this.contVampiresDead =0;
		this.contDracula = 0;
	
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * @return the list
	 */
	public GameObjectList getList() {
		return list;
	}

	/**
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(String[][] board) {
		this.board = board;
	}
	
	/**
	 * @return the contVampires
	 */
	public int getContVampiresOnBoard() {
		return contVampiresOnBoard;
	}

	/**
	 * @param contVampires the contVampires to set
	 */
	public void setContVampiresOnBoard(int contVampires) {
		this.contVampiresOnBoard = contVampires;
	}

	/**
	 * @return the contVampiresDead
	 */
	public int getContVampiresDead() {
		return contVampiresDead;
	}

	/**
	 * @param contVampiresDead the contVampiresDead to set
	 */
	public void setContVampiresDead(int contVampiresDead) {
		this.contVampiresDead = contVampiresDead;
	}
	
	/**
	 * @return the contDracula
	 */
	public int getContDracula() {
		return contDracula;
	}

	/**
	 * @param contDracula the contDracula to set
	 */
	public void setContDracula(int contDracula) {
		this.contDracula = contDracula;
	}

	//-----------------------------
	//-----------------------------
	
	public void update(int turn) {
		//mover vampiro y eliminar muertos
		deleteDead();
		for(int i =0; i < list.getGameobjectsList().size(); i++) {
			list.getGameobjectsList().get(i).move();	
		}
		//añadir vampiros
		addVampire(turn, "V");
		addVampire(turn, "EV");
		if(this.contDracula == 0) {
			addVampire(turn, "D");
		}
	}
	
	//eliminamos de forma recursiva aquellos objetos muertos
	public void deleteDead() {
		String actual_object;
		for(int i =0; i < list.getGameobjectsList().size(); i++) {
			actual_object = board[list.getGameobjectsList().get(i).getPos_y()][list.getGameobjectsList().get(i).getPos_x()];
			if(!list.getGameobjectsList().get(i).isAlive()) {
				if(actual_object == "V"){
					this.contVampiresDead++;
					this.contVampiresOnBoard--;
				}
				else if(actual_object == "EV") {
					this.contVampiresDead++;
					this.contVampiresOnBoard--;
					explosiveVampire(list.getGameobjectsList().get(i).getPos_x(), list.getGameobjectsList().get(i).getPos_y(),(ExplosiveVampires) list.getGameobjectsList().get(i));
				}
				else if(actual_object == "D") {
					this.contDracula--;
					this.contVampiresDead++;
					this.contVampiresOnBoard--;
				}
				board[list.getGameobjectsList().get(i).getPos_y()][list.getGameobjectsList().get(i).getPos_x()] = null;
				list.deleteObject(i);
				deleteDead();
			}
		}
	}
	
	//añade un vampiro a la lista
	public void addVampire(int turn, String iden) {
		if(this.contVampiresOnBoard + this.contVampiresDead < level.getNumberOfVampires() && random.nextDouble() < level.getVampireFrequency()) {
			int posY = random.nextInt(level.getDimY());
			if(board[posY][level.getDimX()-1] == null)/*si en la casilla no hay un vampiro*/ {
				board[posY][level.getDimX()-1] = iden;
				if(iden == "D") {/* si creamos un dracula mostramos que esta vivo por consola y actualizamos su contador*/
					list.addObject(new Dracula(level.getDimX()-1, posY, this.game, turn));
					this.contDracula++;
				}
				else if(iden == "EV") {
					list.addObject(new ExplosiveVampires(level.getDimX()-1, posY, this.game, turn));
				}
				else {
					list.addObject(new Vampire(level.getDimX()-1, posY, this.game, turn));
				}
				this.contVampiresOnBoard++;
			}
		}
	}
	
	public void explosiveVampire(int x, int y, ExplosiveVampires gameO) {
		int pos;
		if(gameO.isExplosiveActive()) {
			//recorremos una submatriz en la posiciones alrededor de la dada
			for(int i= y-1; i < y+2; i++) {
				for(int j= x-1; j < x+2; j++){
					if(i >= 0 && i < game.getLevel().getDimY() && j >= 0 &&  j < game.getLevel().getDimX() && (board[i][j] == "V" || board[i][j] == "EV")){/*si el objeto es atacante*/
						pos = list.searchObject(j, i);
						list.getGameobjectsList().get(pos).setHealth(list.getGameobjectsList().get(pos).getHealth()-1); //decrementamos su vida						//list.getGameobjectsList().get(pos).setDamage(list.getGameobjectsList().get(pos).getDamage()+1); //incrementamos su daño
					}
				}
			}
		}
	}

}
