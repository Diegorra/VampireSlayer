package logic.list;

import logic.Level;
import logic.gameObjects.*;

public class SlayerList {
	private static Level level;
	public static Slayer[] slayerList = new Slayer[(level.getDimX()-1) * level.getDimY()];//no podemos añadir slayers en última column
	private static int SLcont =0;
	
	public static int getSLcont() {
		return SLcont;
	}
	
	public static void newSlayer(int x, int y)/*Método que añade un nuevo slayer*/ {
		slayerList[SLcont] = new Slayer(x, y);
		SLcont++;
	}
	
	public static void deleteSlayer(int x)/*Método que elimina un slayer de la lista*/ {
				for(int j=x; j < SLcont-1 ; j++)/*eliminamos de la lista*/ {
					slayerList[j] = slayerList[j+1];
				}
				SLcont--;
	}
	
	//creamos una funcion que dadas unas posiciones devuelva su posicion en el array
	public static int searchSlayer(int x, int y) {
		int ret=0;
		for(int i=0; i < SLcont; i++) {
			if(slayerList[i].getPosX() == x && slayerList[i].getPosY() == y){
				ret = i;
			}
		}
		return ret;
	}
}
