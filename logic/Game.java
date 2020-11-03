//hace referencia al juego(jugador, turnos...)
package logic;

import logic.gameObjects.Player;
import logic.list.SlayerList;
import logic.list.VampireList;

public class Game {
	long seed;
	static Level level;
	static Player player;
	public static String[][] board ;//= new board[level.getDimX()][level.getDimY()];
	public static int turn;
	
	
	public Game(Long seed, Level level)/*constructor de game*/ {
		this.seed = seed;
		this.level = level;
	}

	public static void initObjects()/*función inicializa objetos del juego*/ {
		
		//inicializamos a null todas las posiciones del tablero
		for(int i=0; i < level.getDimX() ; i++) {
			for(int j =0; j < level.getDimY(); j++){
				board[i][j] = "N";
			}
		}
		
		//inicializamos el numero de turnos a 0, listas vacias, el jugador...
		player = new Player();
		turn = 0;
		
		
	}

	public static void update(int turn) {
		player.recieveMoney();//se actualiza el dinero del jugador
		GameObjectBoard.update(turn);//se generan y mueven vampiros
		for(int i=0; i < SlayerList.getSLcont(); i++) {//los slayer atacan
			attackSlayer(i);
		}
		for(int j=0; j < VampireList.getVLcont(); j++) {//los vampiros atacan
			attackVampire(j);
		}
		turn++;//se actualiza el turno
	}

	public static void reset() {
		initObjects();
	}

	public static void attackSlayer(int num) {
		//buscamos un vampiro en su fila y en caso de encontrarlo actualizamos vida vampire
		int i= SlayerList.slayerList[num].getPosX()+1;
		int y = SlayerList.slayerList[num].getPosY();
		int posV=0;
		
		while(board[i][y] != "V" && i < level.getDimX()) {
			posV = VampireList.searchVampire(i, y);
			VampireList.vampireList[posV].updateHealth();
			//miramos si está muerto
			if(VampireList.vampireList[posV].getHealth()== 0) {
				GameObjectBoard.removeDead(i, y);
				VampireList.deleteVampire(posV);
			}
			i++;
		}
	}

	public static void attackVampire(int num) {
		//si tiene delante un slayer le quita un punto de vida
		int posS;
		int x = VampireList.vampireList[num].getPosX();
		int y=VampireList.vampireList[num].getPosY();
		
		if(board[x-1][y] == "S") {
			posS = SlayerList.searchSlayer(x-1, y);
			SlayerList.slayerList[posS].updateHealth();	
			//miramos si esta muerto
			if(SlayerList.slayerList[posS].getHealth() == 0) {
				GameObjectBoard.removeDead(x-1, y);
				SlayerList.deleteSlayer(posS);
			}
		}
		
	}
}
