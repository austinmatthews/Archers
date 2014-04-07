
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;


//implemented interfaces
public class TicTacToeGame implements MouseListener
{

	//class variables
	JFrame theJFrame;
	int theFlag = 2;
	int theY;
	int theX;
	boolean won;
	//a game board is a 3X3 2D array of X's and O's
	char[][] charArray = new char[3][3];
	

	//constructor
	public TicTacToeGame()
	{
		
		won = false;
		//initialize the JFrame
		theJFrame = new JFrame("player O");
		theJFrame.setSize(1000, 700);
		theJFrame.setVisible(true);
		//add listeners
		theJFrame.addMouseListener(this);
		
		//initialize the board array
		for (int counter = 0; counter < 3; counter++){
			for(int counterTwo = 0; counterTwo < 3; counterTwo++){
			//make the char array all a's
			charArray[counter][counterTwo] = '_';
			}
		
		}


		// ****************************************************
		// prevent resizing

		theJFrame.setResizable(false);
		// prevent window from being increased
		theJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				theJFrame.setSize(Math.max(100, theJFrame.getWidth()),
						Math.max(100, theJFrame.getHeight()));

			}
		});

		//exit on close
		theJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.drawTheBoard();
		

		theJFrame.setVisible(true);
	}


	public void windowClosing(WindowEvent e)
	{
		System.exit(0); 

	}

	//draw the gameboard, 9 boxes are made
	public void drawTheBoard(){
		Graphics moreGraphics = theJFrame.getGraphics();
		moreGraphics.drawLine(200, 0, 200, 600);
		moreGraphics.drawLine(400, 0, 400, 600);
		moreGraphics.drawLine(0, 200, 600, 200);
		moreGraphics.drawLine(0, 400, 600, 400);
		moreGraphics.drawLine(600, 0, 600, 600);
	}


	public void mousePressed(MouseEvent e) { 

		Graphics2D theGraphics2D;

		//draw the board
		Graphics moreGraphics = theJFrame.getGraphics();

		//get the location the mouse was pressed
		int x = e.getX();
		int y = e.getY();

		//do not count count if the press is out of the board
		if(x > 600 || x< 0 || y < 0 || y > 600 ){
			return;
		}

		//subtract one from the flag on each pass through
		theFlag -= 1;
		
		//tell the player it is their turn
		if(theFlag == 1){
			theJFrame.setTitle("player X");
		}else{
			theJFrame.setTitle("player O");
		}
		
		//if the flag is a 1, draw a circle
		if (theFlag == 1)
		{
			if ((x < 200) && (y < 200)) { theX = 0; theY = 0; charArray[0][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y < 200)) { theX = 200; theY= 0; charArray[0][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y < 200)) { theX = 400; theY= 0; charArray[0][2] = 'O'; }
			if ((x < 200) && (y > 200) && (y < 400)) { theX = 0; theY= 200; charArray[1][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) { theX = 200; theY= 200; charArray[1][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) { theX = 400; theY= 200; charArray[1][2] = 'O'; }
			if ((x < 200) && (y > 400) && (y < 600)) { theX = 0; theY= 400; charArray[2][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) { theX = 200; theY= 400; charArray[2][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) { theX = 400; theY= 400; charArray[2][2] = 'O'; }

			//draw a circle
			moreGraphics.setColor(Color.BLACK);
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.drawOval(theX + 10,theY+ 10, 160, 160);

		}

		//if theflag is a 0, draw an X
		if (theFlag == 0)
		{
			if ((x < 200) && (y < 200)) { theX = 0; theY = 0; charArray[0][0] = 'X'; }
			if ((x > 200) && (x < 400) && (y < 200)) { theX = 200; theY= 0; charArray[0][1] = 'X'; }
			if ((x > 400) && (x < 600) && (y < 200)) { theX = 400; theY= 0; charArray[0][2] = 'X'; }
			if ((x < 200) && (y > 200) && (y < 400)) { theX = 0; theY= 200; charArray[1][0] = 'X'; }
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) { theX = 200; theY= 200; charArray[1][1] = 'X'; }
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) { theX = 400; theY= 200; charArray[1][2] = 'X'; }
			if ((x < 200) && (y > 400) && (y < 600)) { theX = 0; theY= 400; charArray[2][0] = 'X'; }
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) { theX = 200; theY= 400; charArray[2][1] = 'X'; }
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) { theX = 400; theY= 400; charArray[2][2] = 'X'; }
			
			
			//drawing an X
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			theGraphics2D.setColor(Color.RED);
			theGraphics2D.drawLine(theX + 10,theY+ 10,theX+ 170,theY+ 160);
			theGraphics2D.drawLine(theX + 170,theY+ 10,theX+ 10,theY+ 170);

			//cycle back through the flag, setting the flag to 2
			theFlag = 2;

		}

		//check to see if the game has ended
		this.winner();
		
		//print array for testing this.printArray();
	}

	
	//is the there a winner?
	public void winner(){

		//is there a winner on a row or a column?
		for (int counter = 0; counter< 3; counter++){
			
			if((charArray[counter][0] == charArray[counter][1]) && (charArray[counter][0] == charArray[counter][2]) && charArray[counter][0] != '_') {
				won = true;
			}
			
			if((charArray[0][counter] == charArray[1][counter]) && (charArray[1][counter] == charArray[2][counter]) && charArray[0][counter] != '_'){
				won = true;
			}
		}
		
		//is there a diagonal winner?

		if(charArray[0][0] == charArray[1][1] && charArray [0][0] == charArray[2][2] && charArray[1][1] != '_'){
				won = true;
		}
		
		if(charArray[0][2] == charArray[1][1] && charArray[0][2] == charArray[2][0] && charArray[1][1] != '_'){
			won = true;
		}
		
		if(won == true){

		}
	}

	//print the 2D char array for testing purposes
	
	/* public void printArray(){
		
		for(int counter = 0; counter < 3; counter++){
			
			for(int counterTwo = 0; counterTwo < 3; counterTwo++){
				System.out.print(charArray[counter][counterTwo] + ",");
			}
			System.out.println();
		}
		System.out.println();
	}
	*/


	// main method for testing

	public static void main(String[] args)
	{
		TicTacToeGame theGame = new TicTacToeGame();
		theGame.drawTheBoard();

	}






	//**********************************************
	//following methods unused. override interfaces

	@Override
	public void mouseReleased(MouseEvent e)
	{
		//method unused
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		//method unused
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//method unused
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//method unused
	}



}



