


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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
