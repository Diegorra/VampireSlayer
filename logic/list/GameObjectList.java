package logic.list;
import java.util.ArrayList;
import logic.GameObjects.GameObject;


public class GameObjectList {
	
	private ArrayList<GameObject> gameobjectsList; //declaramos la lista
	
	public GameObjectList() {
		this.gameobjectsList = new ArrayList<GameObject>();
	}
	
	/**
	 * @return the gameobjects
	 */
	public ArrayList<GameObject> getGameobjectsList() {
		return gameobjectsList;
	}

	/**
	 * @param gameobjects the gameobjects to set
	 */
	public void setGameobjectsList(ArrayList<GameObject> gameobjects) {
		this.gameobjectsList = gameobjects;
	}
	//--------------------------------------------------------------
	//--------------------------------------------------------------
	
	//añadir nuevo objecto
	public void addObject(GameObject object) {
		gameobjectsList.add(object);
	}
	
	//eliminar objeto en una posicion
	public void deleteObject(int pos) {
		gameobjectsList.remove(pos);
	}
	
	//buscar un objetos dadas sus coordenadas
	public int searchObject(int x, int y) {
		int i = 0, ret = -1;
		while(i < gameobjectsList.size() && ret == -1) {
			if(gameobjectsList.get(i).getPos_x() == x && gameobjectsList.get(i).getPos_y() == y) {
				ret = i;
			}
			i++;
		}
		return ret;
	}
}
