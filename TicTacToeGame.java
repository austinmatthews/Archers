
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

	//a game board is a 3X3 2D array of X's and O's
	char[][] charArray = new char[2][2];

	//constructor
	public TicTacToeGame()
	{
		//initialize the JFrame
		theJFrame = new JFrame("Tic Tac Toe");
		theJFrame.setSize(1000, 700);
		theJFrame.setVisible(true);
		//add listeners
		theJFrame.addMouseListener(this);
		
		//initialize the board array
		for (int counter = 0; counter < 3; counter++){
			for(int counterTwo = 0; counterTwo < 3; counterTwo++){
			//make the char array all a's
			charArray[counter][counterTwo] = 'a';
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




		int x = e.getX();
		int y = e.getY();

		//do not count count if the press is out of the board
		if(x > 600 || x< 0 || y < 0 || y > 600 ){
			return;
		}

		//subtract one from the flag on each pass through
		theFlag -= 1;

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
			if ((x < 200) && (y < 200)) { theX = 0; theY = 0; charArray[0][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y < 200)) { theX = 200; theY= 0; charArray[0][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y < 200)) { theX = 400; theY= 0; charArray[0][2] = 'O'; }
			if ((x < 200) && (y > 200) && (y < 400)) { theX = 0; theY= 200; charArray[1][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) { theX = 200; theY= 200; charArray[1][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) { theX = 400; theY= 200; charArray[1][2] = 'O'; }
			if ((x < 200) && (y > 400) && (y < 600)) { theX = 0; theY= 400; charArray[2][0] = 'O'; }
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) { theX = 200; theY= 400; charArray[2][1] = 'O'; }
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) { theX = 400; theY= 400; charArray[2][2] = 'O'; }
			
			
			//drawing an X
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			theGraphics2D.setColor(Color.RED);
			theGraphics2D.drawLine(theX + 10,theY+ 13,theX+ 170,theY+ 160);
			theGraphics2D.drawLine(theX + 170,theY+ 10,theX+ 10,theY+ 170);

			//cycle back through the flag, setting the flag to 2
			theFlag = 2;
		}

		//check to see if the game has ended
		this.gameOver();
	}


	public void gameOver(){


	}



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



