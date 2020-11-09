package logic.list;
import logic.Level;
import logic.gameObjects.Slayer;;

public class SlayerList {
	
	private Level level;
	private Slayer[] slayerList;
	private int SLcont =0;
	
	public SlayerList(Level level) {
		this.level = level;
		this.slayerList = new Slayer[(this.level.getDimY()) * (this.level.getDimX()-1)];//no podemos añadir slayers en última columna
		this.SLcont = 0;
	}
	
	public Slayer[] getSlayerList() {
		return this.slayerList;
	}
	
	public int getSLcont() {
		return SLcont;
	}
	
	public int sefSLcont(int a) {
		return SLcont = a;
	}
	
	public void newSlayer(int y, int x)/*Método que añade un nuevo slayer*/ {
		slayerList[SLcont] = new Slayer(y, x);
		SLcont++;
	}
	
	public void deleteSlayer(int x)/*Método que elimina un slayer de la lista*/ {
				for(int j=x; j < SLcont-1 ; j++)/*eliminamos de la lista*/ {
					slayerList[j].setHealth(slayerList[j+1].getHealth());
					slayerList[j].setPosX(slayerList[j+1].getPosX());
					slayerList[j].setPosY(slayerList[j+1].getPosY());
				}
				SLcont--;
	}
	
	//creamos una funcion que dadas unas posiciones devuelva su posicion en el array
	public int searchSlayer(int y, int x) {
		int ret=-1;
		int i=0;
		while(i < SLcont && ret == -1) {
			if(slayerList[i].getPosX() == x && slayerList[i].getPosY() == y){
				ret = i;
			}
			i++;
		}
		return ret;
	}
}
