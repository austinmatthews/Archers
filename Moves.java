/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */

//keep tracks of moves
//X and Y for tic tac toe,
//power and angle for  scorched earth
//it is kept in either a stack or queue in each game
public class Moves  {
	private int x;
	private int y;



	//default constructor
	public Moves(){
		x = -1;
		y = -1;

	}

	//the constructor used in the tic tac toe class
	public Moves(int pX, int pY){
		x = pX;
		y = pY;

	}
	public int getY(){
		return y;
	}
	public int getX(){
		return x;
	}
}
