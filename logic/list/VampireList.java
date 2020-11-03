package logic.list;


import logic.Level;
import logic.gameObjects.*;

public class VampireList {
	private static Level level;
	public static Vampire[] vampireList = new Vampire[level.getNumVampires()];
	private static int VLcont=0;
	
	public static int getVLcont() {
		return VLcont;
	}
	
	public static void newVampire(int x, int y, int turn)/*Método que añade un nuevo vampiro*/ {
		vampireList[VLcont] = new Vampire(x, y, turn);
		VLcont++;
	}
	
	public static void deleteVampire(int x)/*Método que elimina un vampiro de la lista*/ {
				for(int j=x; j < VLcont-1 ; j++)/*eliminamos de la lista*/ {
					vampireList[j] = vampireList[j+1];
				}
				VLcont--;
	}
	
	//creamos una funcion que dadas unas posiciones devuelva su posicion en el array
	public static int searchVampire(int x, int y) {
		int ret=0;
		for(int i=0; i < VLcont; i++) {
			if(vampireList[i].getPosX() == x && vampireList[i].getPosY() == y){
				ret = i;
			}
		}
		return ret;
	}
	
	
}
