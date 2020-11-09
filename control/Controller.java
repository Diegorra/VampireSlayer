package control;

import java.util.Scanner;

import logic.Game;
import logic.Level;
import view.GamePrinter;

public class Controller {

	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" + "[a]dd <x> <y>: add a slayer in position x, y%n" + "[h]elp: show this help%n"
					+ "[r]eset: reset game%n" + "[e]xit: exit game%n" + "[n]one | []: update%n");

	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");
	
	public long seed;
	public Level level;
	
	private Game game;
	private Scanner scanner;
	private GamePrinter gamePrinter;

	public Controller(Game game, Scanner scanner) {
		this.level = game.getLevel();
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game, this.level.getDimX(),this.level.getDimY());
	}

	public void printGame() {
		System.out.println(gamePrinter.toString());
	}

	public void run() {
		boolean exit = false;
		boolean rePrint = true;
		game.initObjects();
		while(!game.isFinished() && !exit) {
			if(rePrint){
				printGame();
			}
			System.out.print(prompt);
			String line = scanner.nextLine();
			String[] words = line.toLowerCase().trim().split("\\s+");//convertimos a minusculas y dividimos
			switch(words[0]) {
				case "add" :
					if(game.getPlayer().getMoney() >= 50) {
						game.getGameOB().addSlayer(Integer.parseInt(words[1]), Integer.parseInt(words[2]));//convertimos string a int
						game.getPlayer().buySlayer();//restamos dinero jugador
					}
					else {
						System.out.println("You don't have money :(");
					}
					game.update();
					rePrint = true;
					break;
				case "a" :
					if(game.getPlayer().getMoney() >= 50) {
						game.getGameOB().addSlayer(Integer.parseInt(words[1]), Integer.parseInt(words[2]));//convertimos string a int
						game.getPlayer().buySlayer();//restamos dinero jugador
					}
					else {
						System.out.println("You don't have money :(");
					}
					game.update();
					rePrint = true;
					break;
				case "" : 
					rePrint = true;
					game.update();
					break;
				case "none": 
					rePrint = true;
					game.update();
					break;
				case "n" : 
					rePrint = true;
					game.update();
					break;
				case "reset": 
					game.reset();
					rePrint = true;
					break;
				case "r" : 
					game.reset();
					rePrint = true;
					break;
				case "help" : 
					System.out.println(helpMsg); 
					rePrint = false;
					break;
				case "h" : 
					System.out.println(helpMsg);
					rePrint = false;
					break;
				case "exit" : 
					System.out.println("Game Over");
					exit = true;
					break;
				case "e" : 
					System.out.println("Game Over");
					exit = true;
					break;
				default : 
					System.out.println(unknownCommandMsg);
					rePrint = false;
					break;
			}
		}
	}

}
