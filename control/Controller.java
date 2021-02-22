package control;

import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import exceptions.GameException;
import logic.Game;
import view.GamePrinter;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    private GamePrinter gamePrinter;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
	    this.gamePrinter = new GamePrinter(game, game.getLevel().getDimX(), game.getLevel().getDimY());
	}    
    
    public void  printGame() {
   	 System.out.println(gamePrinter.toString());
   }
    
    public void run() {
	    	boolean refreshDisplay = true;
	    	game.initObjects();
	    	while (!game.isFinished()){
	    		
        		 if (refreshDisplay) printGame();
        		 refreshDisplay = false;
        		 
			  System.out.print(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
		      try {
		    	  Command command = CommandGenerator.parseCommand(parameters);
			      refreshDisplay = command.execute(game);
		      }
		      catch(GameException ex) {
		    	  System.out.format(ex.getMessage() + "%n%n");
		      }
		       
		}
	    
    	if (refreshDisplay) printGame();
		System.out.println ("[Game over] " + game.getWinnerMessage());

    }

}

