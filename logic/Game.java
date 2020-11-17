//hace referencia al juego(jugador, turnos...)
package logic;

import java.util.Random;

import logic.gameObjects.Player;

public class Game {
	
	long seed;
	private Level level;
	private GameObjectBoard gameOB ;
	private Player player;
	private int cycles;
	private Random random;
	
	public Game(Long seed, Level level)/*constructor de game*/ {
		this.seed = seed;
		this.level = level;
		this.gameOB = new GameObjectBoard(this.level);
		this.player = new Player();
		this.random = new Random();
	}
	
	public int getCycles() {
		return this.cycles;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public GameObjectBoard getGameOB() {
		return this.gameOB;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void initObjects()/*funci�n inicializa objetos del juego*/ {
		
		//inicializamos a null todas las posiciones del tablero
		for(int i=0; i < level.getDimY() ; i++) {
			for(int j =0; j < level.getDimX(); j++){
				gameOB.getBoard()[i][j] = "N";
			}
		}
		
		//inicializamos listas vacias, el dinero del jugador
		player.setMoney(50);
		gameOB.getSl().sefSLcont(0);
		gameOB.getVl().setVLcont(0);
		this.cycles = 0;
		
		
	}

	public void update() {
		player.recieveMoney(this.random);//se actualiza el dinero del jugador
		gameOB.update(cycles, this.random);//se generan y mueven vampiros
		if(gameOB.getVl().getVLcont() != 0 && gameOB.getSl().getSLcont() != 0) {
			for(int i=0; i < gameOB.getSl().getSLcont(); i++) {//los slayer atacan
				attackSlayer(i);
			}
			for(int j=0; j < gameOB.getVl().getVLcont(); j++) {//los vampiros atacan
				attackVampire(j);
			}
		}
		cycles++;//se actualiza el turno
	}

	public void reset() {
		gameOB = new GameObjectBoard(this.level);
		initObjects();
	}

	public void attackSlayer(int num) {
		//buscamos un vampiro en su fila y en caso de encontrarlo actualizamos vida vampire
		int i = gameOB.getSl().getSlayerList()[num].getPosX()+1;
		int y = gameOB.getSl().getSlayerList()[num].getPosY();
		int posV=0;
		
		while(gameOB.getBoard()[y][i] == "N" && i < level.getDimX()-1) {
			i++;
		}
		if(gameOB.getBoard()[y][i] == "V") {
			posV = gameOB.getVl().searchVampire(y, i);			
			gameOB.getVl().getVampireList()[posV].updateHealth();
			//miramos si est� muerto
			if(gameOB.getVl().getVampireList()[posV].getHealth() == 0) {
				gameOB.removeDead(y, i);
				gameOB.getVl().deleteVampire(posV);
			}
		}
	}

	public void attackVampire(int num) {
		//si tiene delante un slayer le quita un punto de vida
		int posS;
		int x = gameOB.getVl().getVampireList()[num].getPosX();
		int y = gameOB.getVl().getVampireList()[num].getPosY();
		
		if(x != 0 &&  y != 0 && gameOB.getBoard()[y][x-1] == "S") {
			posS = gameOB.getSl().searchSlayer(y, x-1);
			gameOB.getSl().getSlayerList()[posS].updateHealth();	
			//miramos si esta muerto
			if(gameOB.getSl().getSlayerList()[posS].getHealth() == 0) {
				gameOB.removeDead(y, x-1);
				gameOB.getSl().deleteSlayer(posS);
			}
		}
		
	}
	
	public boolean isFinished() {
		boolean exit = false;
		int i=0;
		if(gameOB.getVl().getVLcontmuertos() == level.getNumVampires())/*cuando no hay vampiros gana jugador*/ {
			System.out.println("Player wins");
			exit = true;
		}
		else/*cuando un vampiro llega a la columna 0 ganan vampiros*/{
			while(i < gameOB.getVl().getVLcont() && !exit) {
				if(gameOB.getVl().getVampireList()[i].getPosX() == 0) {
					System.out.println("Vampires wins");
					exit = true;
				}
				i++;
			}	
		}
		return exit;
	}
}
