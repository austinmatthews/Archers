
/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */

public abstract class Game {

	//would you like to play again?
	public abstract void drawMenu();
	
	//select either yes or no to replay or exit
	public abstract void makeSelection();
	
	//play the game
	public abstract void playGame();
	
	//draw the game
	public abstract void drawGame();
}
