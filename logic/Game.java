package logic;

import java.util.Random;

import logic.GameObjects.BloodBank;
import logic.GameObjects.Dracula;
import logic.GameObjects.ExplosiveVampires;
import logic.GameObjects.GameObject;
import logic.GameObjects.IAttack;
import logic.GameObjects.Player;
import logic.GameObjects.Slayer;
import logic.GameObjects.Vampire;

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
		this.random = new Random();
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
			if(gameOB.getBoard()[y][x] == "V") {
				box = "V" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x] == "EV") {
				box = "EV" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x] == "D") {
				box = "D" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x] == "S"){
				box = "S" + "[" + gameOB.getList().getGameobjectsList().get(pos).getHealth() + "]";
			}
			else if(gameOB.getBoard()[y][x] == "B") {
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
		String remainingV = "Reamining vampires: " + (level.getNumberOfVampires() - (gameOB.getContVampiresOnBoard() + gameOB.getContVampiresDead()));
		String vampires = "Vampires on board: "  + (gameOB.getContVampiresOnBoard());
		
		return (numberOfCycles + "\n" + coins + "\n" + remainingV + "\n" + vampires + "\n" );
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
	public void addSlayer(int x, int y) {
		if(x < level.getDimX()-1 && y < level.getDimY() && player.getMoney() >= Slayer.COST && gameOB.getBoard()[y][x] == null ) {
			gameOB.getList().addObject(new Slayer(x, y,this));
			gameOB.getBoard()[y][x] = "S";
			player.setMoney(player.getMoney()-Slayer.COST);//actualizamos monedas
		}
		else {
			System.err.println("ERROR creating slayer");
		}
	}
	
	//añade un bloodbank, su la posición está dentro de los límites del tablero, tiene dienro y no hay obejtos en dicha posición
	public void addBloodBank(int x, int y, int z) {
		if(x < level.getDimX()-1 && y < level.getDimY() && player.getMoney() >= BloodBank.COST && gameOB.getBoard()[y][x] == null ) {
			gameOB.getList().addObject(new BloodBank(x, y, z, this));
			gameOB.getBoard()[y][x] = "B";
			player.setMoney(player.getMoney()-BloodBank.COST);//actualizamos monedas
		}
		else {
			System.err.println("ERROR creating blood bank");
		}
	}
	
	//añade un vampiro manualmente si se puede
	public void addVampireManually(int x, int y, String iden) {
			if(iden.equals("v")) {
				gameOB.getBoard()[y][x] = "V";
				gameOB.getList().addObject(new Vampire(x, y,this, this.cycles));
			}
			else if(iden.equals("d")) {
				if(gameOB.getContDracula() !=1) {
					gameOB.getBoard()[y][x] = "D";
					gameOB.getList().addObject(new Dracula(x, y,this, this.cycles));
				}else {
					System.err.println("ERROR, Dracula is already alive");
				}
			}
			else if(iden.equals("ev")) {
				gameOB.getBoard()[y][x] = "EV";
				gameOB.getList().addObject(new ExplosiveVampires(x, y,this, this.cycles));
			}
			else {
				System.err.println("ERROR, invalid type");
			}
	}
		
	
	//retorna si hay un objeto en una posicion, en cuyo caso sería atacable
	public IAttack getAttackableInPosition(int x, int y, String ident) {
		int pos = gameOB.getList().searchObject(x, y);
		if(pos != -1 && atacante_Defensivo(gameOB.getBoard()[y][x]) != ident) {
			return (IAttack) gameOB.getList().getGameobjectsList().get(pos);
		}
		return null;
	}
	
	//funcion que dado un string identificador devuelve si es un objeto atacante o defensivo
	public String atacante_Defensivo(String id) {
		String mode;
		if(id == "V" || id=="D" || id == "EV") {
			mode = "atacante";
		}
		else {
			mode = "defensivo";
		}
		return mode;
	}
	
	public void garlicPush() {
		if(player.getMoney() >= 10) {
			for(int i=0; i < gameOB.getList().getGameobjectsList().size(); i++) {//los objetos atacan 
				gameOB.getList().getGameobjectsList().get(i).receiveGarlicPush();
				
				if(gameOB.getBoard()[gameOB.getList().getGameobjectsList().get(i).getPos_y()][gameOB.getList().getGameobjectsList().get(i).getPos_x()] == "EV") {
					gameOB.setExplosiveActive(false);
				}
			}
			player.setMoney(player.getMoney()-10);
		}
		else {
			System.err.println("ERROR, you don't have enough coins");
		}
	}
	
	public void lightFlash() {
		if(player.getMoney() >= 50) {
			for(int i=0; i < gameOB.getList().getGameobjectsList().size(); i++) {//los objetos atacan 
				gameOB.getList().getGameobjectsList().get(i).receiveLightFlash();
				if(gameOB.getBoard()[gameOB.getList().getGameobjectsList().get(i).getPos_y()][gameOB.getList().getGameobjectsList().get(i).getPos_x()] == "EV") {
					gameOB.setExplosiveActive(false);
				}
			}player.setMoney(player.getMoney()-50);
		}
		else {
			System.err.println("ERROR, you don't have enough coins");
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
