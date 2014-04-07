import java.util.Stack;


public class Moves  {
	int x;
	int y;
	int player;
	
	
	//default constructor
	public Moves(){
		x = -1;
		y = -1;
		player = 2;
	}
	
	//the constructor used in the tic tac toe class
	public Moves(int pX, int pY, int pPlayer){
		x = pX;
		y = pY;
		player = pPlayer;
	}
	
}
