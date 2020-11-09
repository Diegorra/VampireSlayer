package view;

import logic.Game;
import utils.MyStringUtils;

public class GamePrinter {
	
	Game game;
	int numRows;
	int numCols;
	String[][] board; 
	final String space = " ";

	public GamePrinter(Game game, int cols, int rows) {
		this.game = game;
		this.numCols = cols;
		this.numRows = rows;
		this.board = new String[numRows][numCols];
	}

	private void encodeGame(Game game) {
		//este metodo crea la cabecera del game
		int pos;
		String numberOfCycles = "Number of cycles: " + game.getCycles();
		String coins = "Coins: " + game.getPlayer().getMoney();
		String remainingV = "Reamining vampires: " + (game.getGameOB().getLevel().getNumVampires() - game.getGameOB().getVl().getVLcontmuertos());
		String vampires = "Vampires on board: "  + (game.getGameOB().getVl().getVLcont());
		System.out.print(numberOfCycles + "\n" + coins + "\n" + remainingV + "\n" + vampires + "\n" );
		//codificamos el tablero;
		for(int i=0; i < this.numRows; i++) {
			for(int j=0; j < this.numCols; j++) {
				if(game.getGameOB().returnBox(i, j) == "V") {
					pos = game.getGameOB().getVl().searchVampire(i, j);
					board[i][j] = "V" + "[" + game.getGameOB().getVl().getVampireList()[pos].getHealth()  + "]";
				}
				else if(game.getGameOB().returnBox(i, j) == "S") {
					pos = game.getGameOB().getSl().searchSlayer(i, j);					
					board[i][j] = "S" + "[" + game.getGameOB().getSl().getSlayerList()[pos].getHealth()  + "]";
				}
				else { 
					board[i][j] = space;
				}
			}
		}
	}

	public String toString() {
		encodeGame(game);
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;

		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols - 1) + cellDelimiter
				+ vIntersect;
		String hEdge = corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols - 1) + cellDelimiter + corner;

		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineEdge);
		for (int i = 0; i < numRows; i++) {
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < numCols; j++)
				str.append(MyStringUtils.centre(this.board[i][j], cellSize)).append(vDelimiter);
			if (i != numRows - 1)
				str.append(lineDelimiter);
			else
				str.append(lineEdge);
		}

		return str.toString();
	}
}
