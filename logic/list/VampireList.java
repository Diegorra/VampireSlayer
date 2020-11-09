package logic.list;

import logic.Level;
import logic.gameObjects.Vampire;;

public class VampireList {
	private Level level;
	private Vampire[] vampireList;
	private int VLcont;
	private  int VLcontmuertos;
	
	public VampireList(Level level) {
		this.level = level;
		vampireList = new Vampire[this.level.getNumVampires()];
		this.VLcont = 0;
		this.VLcontmuertos = 0;
	}
	
	public Vampire[] getVampireList() {
		return this.vampireList;
	}
	
	public int getVLcont() {
		return VLcont;
	}
	
	public int getVLcontmuertos() {
		return VLcontmuertos;
	}
	
	public int setVLcont(int a) {
		return VLcont = a;
	}
	
	public void newVampire(int y, int x, int turn)/*Método que añade un nuevo vampiro*/ {
		vampireList[VLcont] = new Vampire(y, x, turn);
		VLcont++;
	}
	
	public void deleteVampire(int x)/*Método que elimina un vampiro de la lista*/ {
		for(int j=x; j < VLcont-1 ; j++)/*eliminamos de la lista*/ {
			vampireList[j].setHealth(vampireList[j+1].getHealth());
			vampireList[j].setPosX(vampireList[j+1].getPosX());
			vampireList[j].setPosY(vampireList[j+1].getPosY());
			vampireList[j].setTurn(vampireList[j+1].getTurn());
		}
		VLcont--;
		VLcontmuertos++;
	}
	
	//creamos una funcion que dadas unas posiciones devuelva su posicion en el array
	public int searchVampire(int y, int x) {
		int ret=-1;
		int i=0;
		while(i < VLcont && ret == -1) {
			if(vampireList[i].getPosX() == x && vampireList[i].getPosY() == y){
				ret = i;
			}
			i++;
		}
		return ret;
	}
}
