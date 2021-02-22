package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


import logic.GameObjects.BloodBank;
import logic.GameObjects.Dracula;
import logic.GameObjects.ExplosiveVampires;
import logic.GameObjects.GameObject;
import logic.GameObjects.IAttack;
import logic.GameObjects.Player;
import logic.GameObjects.Slayer;
import logic.GameObjects.Vampire;
import exceptions.*;

public class Game implements view.IPrintable {
	
	private Long seed;
	private Level level;
	private GameObjectBoard gameOB;
	private int cycles;
	private Random random;
	private Player player;
	private String winner;
	private boolean userExit;
	
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
		this.random = new Random(seed);
		this.gameOB = new GameObjectBoard(this.level, this.random, this);
		this.cycles = 0;
		this.player = new Player(random);
		this.winner = "none";
		this.userExit = false;
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
	 * @return the gameOB
	 */
	public GameObjectBoard getGameOB() {
		return gameOB;
	}

	/**
	 * @param gameOB the gameOB to set
	 */
	public void setGameOB(GameObjectBoard gameOB) {
		this.gameOB = gameOB;
	}

	
	/**
	 * @return the cycles
	 */
	public int getCycles() {
		return cycles;
	}

	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * @return the userExit
	 */
	public boolean isUserExit() {
		return userExit;
	}

	/**
	 * @param userExit the userExit to set
	 */
	public void setUserExit(boolean userExit) {
		this.userExit = userExit;
	}
	
	@Override
	//codificamos el tablero para mostar la vida de los objetos
	public String getPositionToString(int x, int y) {
		int pos = gameOB.getList().searchObject(x, y);
		String box = "";
		if(pos != -1) {
			if(gameOB.getBoard()[y][x].equals("V")) {
				box = "V" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x].equals("EV")) {
				box = "EV" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x].equals("D")) {
				box = "D" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x].equals("S")){
				box = "S" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x].equals("B")) {
				box = "B" + "[" + gameOB.getList().getGameobjectsList().get(pos).getZ() + "]";
			}
		}
		return box;
	}

	@Override
	//mostramos la info de la partida
	public String getInfo() {
		String numberOfCycles = "Number of cycles: " + cycles;
		String coins = "Coins: " + player.getMoney();
		String remainingV = "Remaining vampires: " + (level.getNumberOfVampires() - (gameOB.getContVampiresOnBoard() + gameOB.getContVampiresDead()));
		String vampires = "Vampires on the board: "  + (gameOB.getContVampiresOnBoard());
		String dracula = "";
		if(gameOB.getContDracula() == 1) {
			dracula = "Dracula is alive\n";
		}
		
		return (numberOfCycles + "\n" + coins + "\n" + remainingV + "\n" + vampires + "\n" + dracula);
	}
	
	
	//------------------------------------------
	//------------------------------------------
	
	public void initObjects() {
		//inicializamos el tablero a null
		for(int j=0; j < level.getDimY(); j++) {
			for(int i=0; i < level.getDimX(); i++) {
				gameOB.getBoard()[j][i] = null;
			}
		}
		
		//las monedas del jugador son 50
		player.setMoney(50);
		
		//el turno comienza en 0
		cycles = 0;
	}
	
	public void update() {
		player.recieveMoney();//se actualizan las monedas del jugador
		for(int i=0; i < gameOB.getList().getGameobjectsList().size(); i++) {//los objetos atacan 
			gameOB.getList().getGameobjectsList().get(i).attack();
			player.setMoney(player.getMoney() + (int)(gameOB.getList().getGameobjectsList().get(i).getZ()*0.1));//comprobamos el blood bank y actualizamos monedas al jugador
		}
		gameOB.update(cycles);//se actualiza el tablero
		cycles++;//se actualiza el numero de ciclos
		
	}
	
	//restauramos valores iniciales
	public void reset() {
		gameOB = new GameObjectBoard(level, random, this);
		initObjects();
	}
	
	//añade un slayer, si la posicion esta detro de los limites del tablero, tiene dinero y no hay objetos en dicha posicion
	public void addSlayer(int x, int y) throws UnvalidPositionException {
		if(x < level.getDimX()-1 && y < level.getDimY() && gameOB.getBoard()[y][x] == null ) {
			gameOB.getList().addObject(new Slayer(x, y,this));
			gameOB.getBoard()[y][x] = "S";
			player.setMoney(player.getMoney()-Slayer.COST);//actualizamos monedas
			update();
		}
		else {
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
	}
	
	//añade un bloodbank, su la posición está dentro de los límites del tablero, tiene dienro y no hay obejtos en dicha posición
	public void addBloodBank(int x, int y, int z) throws UnvalidPositionException {
		if(x < level.getDimX()-1 && y < level.getDimY() && player.getMoney() >= BloodBank.COST && gameOB.getBoard()[y][x] == null ) {
			gameOB.getList().addObject(new BloodBank(x, y, z, this));
			gameOB.getBoard()[y][x] = "B";
			player.setMoney(player.getMoney()-BloodBank.COST);//actualizamos monedas
		}
		else {
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
	}
	
	//añade un vampiro manualmente si se puede
	public void addVampireManually(int x, int y, String iden) throws CommandExecuteException {	
			if(iden.equals("v")) {
				gameOB.getBoard()[y][x] = "V";
				gameOB.getList().addObject(new Vampire(x, y,this, this.cycles));
				
			}
			else if(iden.equals("d")) {
				if(gameOB.getContDracula() !=1) {
					gameOB.getBoard()[y][x] = "D";
					gameOB.getList().addObject(new Dracula(x, y,this, this.cycles));
					gameOB.setContDracula(1);
				}else {
					throw new DraculaIsAliveException("[ERROR]: Dracula is already alive");
				}
			}
			else if(iden.equals("e")) {
				gameOB.getBoard()[y][x] = "EV";
				gameOB.getList().addObject(new ExplosiveVampires(x, y,this, this.cycles));
			}
			else {
				throw new CommandExecuteException("[ERROR]: " + iden + " is not a valid identifier");
			}
			gameOB.setContVampiresOnBoard(gameOB.getContVampiresOnBoard()+1);
	}
	
	//Attack
	//----------------------------------------------------------------------------
	//retorna si hay un objeto en una posicion, en cuyo caso sería atacable
	public IAttack getAttackableInPosition(int x, int y, String ident) {
		int pos = gameOB.getList().searchObject(x, y);
		if(pos != -1 && atacante_Defensivo(gameOB.getBoard()[y][x]) != ident) {
			return (IAttack) gameOB.getList().getGameobjectsList().get(pos);
		}
		return null;
	}
	
	//función que dado un string identificador devuelve si es un objeto atacante o defensivo
	public String atacante_Defensivo(String id) {
		String mode;
		if(id.equals("V") || id.equals("D")|| id.equals("EV")) {
			mode = "atacante";
		}
		else {
			mode = "defensivo";
		}
		return mode;
	}
	
	public void garlicPush() {
			deleteOutofBoard();//eliminamos los desplazados fuera del tablero
			for(int i=gameOB.getList().getGameobjectsList().size() - 1; i >= 0 ; i--) {//los objetos atacantes son empujados a la derecha 
				gameOB.getList().getGameobjectsList().get(i).receiveGarlicPush();
			}
			player.setMoney(player.getMoney()-10);
	}
	
	public void deleteOutofBoard() {
		for(int i=0; i < gameOB.getList().getGameobjectsList().size(); i++) {
			if(gameOB.getList().getGameobjectsList().get(i).getPos_x()== level.getDimX()-1) {
				if(gameOB.getBoard()[gameOB.getList().getGameobjectsList().get(i).getPos_y()][gameOB.getList().getGameobjectsList().get(i).getPos_x()] == "D") {
					gameOB.setContDracula(0);
				}
				gameOB.getBoard()[gameOB.getList().getGameobjectsList().get(i).getPos_y()][gameOB.getList().getGameobjectsList().get(i).getPos_x()] = null;
				gameOB.getList().deleteObject(i);
				deleteOutofBoard();
				gameOB.setContVampiresDead(gameOB.getContVampiresDead()+1);
				gameOB.setContVampiresOnBoard(gameOB.getContVampiresOnBoard()-1);
			}
		}
	}
	
	public void lightFlash() {
			for(int i=0; i < gameOB.getList().getGameobjectsList().size(); i++) {//los objetos atacan 
				gameOB.getList().getGameobjectsList().get(i).receiveLightFlash();
				if(gameOB.getBoard()[gameOB.getList().getGameobjectsList().get(i).getPos_y()][gameOB.getList().getGameobjectsList().get(i).getPos_x()] == "EV") {
					((ExplosiveVampires) gameOB.getList().getGameobjectsList().get(i)).setExplosiveActive(false);
				}
			}
			player.setMoney(player.getMoney()-50);
	}
	
	//Save game status
	//---------------------------------------------------------------
	public StringBuilder serialize() {
		StringBuilder str = new StringBuilder();
		str.append("\nCycles: " + this.cycles +"\n");
		str.append("Coins: " + this.getPlayer().getMoney() + "\n");
		str.append("Level: " + this.getLevel().name() + "\n");
		str.append("Remaining Vampires: " + (level.getNumberOfVampires() - (gameOB.getContVampiresOnBoard() + gameOB.getContVampiresDead())) + "\n");
		str.append("Vampires on board: " + gameOB.getContVampiresOnBoard() + "\n");
		str.append("\nGame Object List: \n");
		for(GameObject go : gameOB.getList().getGameobjectsList()) {
			
			str.append(gameOB.getBoard()[go.getPos_y()][go.getPos_x()] + "; ");
			str.append(go.getPos_x() + "; " + go.getPos_y() + "; " + go.getHealth());
			if(gameOB.getBoard()[go.getPos_y()][go.getPos_x()] == "V" || gameOB.getBoard()[go.getPos_y()][go.getPos_x()] == "D" || gameOB.getBoard()[go.getPos_y()][go.getPos_x()] == "EV") {
				str.append("; " + nextTurn(((Vampire) go).getTurn()));
			}else if(gameOB.getBoard()[go.getPos_y()][go.getPos_x()] == "B") {
				str.append("; " + go.getZ());
			}
			str.append("\n");
		}
		return str;
		
	}
	
	public String nextTurn(int turn) {
		String str = "1";
		if(cycles%2 == turn%2) {
			str = "2";
		}
		return str;
	}
	
	public void save(String name)throws CommandExecuteException {
		try {
			File ficheroSalida = new File(name + ".dat");
			FileWriter file = new FileWriter(ficheroSalida);
			file.write("Buffy the Vampire Slayer v3.0 \n" + serialize());
			file.close();
			System.out.println("Game successfully saved to file");
            
        } catch (IOException e) {
            System.err.println("Algo fue mal al leer o cerrar el fichero.");
            throw new CommandExecuteException("[ERROR]: unable to saved the game");
        } 
	}
	
	//end game
	//---------------------------------------------------------------
	
	//comprueba si el usuario desea salir o se ha acabado el juego
	public boolean isFinished() {
		boolean exit = false;
		int i=0;
		if(this.userExit) {
			exit = true;
		}
		else {
			if(gameOB.getContVampiresDead() == level.getNumberOfVampires())/*cuando no hay vampiros gana jugador*/ {
				winner = "player";
				exit = true;
			}
			else/*cuando un vampiro llega a la columna 0 ganan vampiros*/{
				while(i < gameOB.getList().getGameobjectsList().size() && !exit) {
					if(gameOB.getList().getGameobjectsList().get(i).arriveEnd()) {
						winner = "vampire";
						exit = true;
					}
					i++;
				}	
			}
		}
		return exit;
	}
	
	//muestra ganador
	public String getWinnerMessage() {
		String message="";
		if(winner == "player") {
			message = "Player wins";
		}
		else if(winner == "vampire") {
			message = "Vampires wins";
		}
		return message;
	}
	

}
