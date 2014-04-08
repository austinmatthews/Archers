
/**
 *
 * @author Matthew Vollkommer and Austin Matthews
 *
 *
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Stack;

//implemented interfaces
public class TicTacToeGame implements MouseListener, KeyListener
{
	//constants
	private final int Z_KEY=17;
	private final int CONTROL_KEY =90;
    
	//class variables
	private boolean zKeyPressed=false;
	private boolean controlKeyPressed=false;
	private int theY;
	private int theX;
	private boolean theFlag;
	private boolean won;
	private boolean tie;
	//count wins
	private int xWins;
	private int oWins;
    
	//Frame
	private JFrame theJFrame;
    
	//a game board is a 3X3 2D array of X's and O's
	private char[][] charArray = new char[3][3];
    
	//stack to keep track of moves
	private Stack<Moves> theMoves = new Stack<Moves>();
    
	//constructor
	public TicTacToeGame()
	{
		P4Arcade.frame.setVisible(false);
		
		int xWins = 0;
		int oWins = 0;
		won = false;
		theFlag = true;
		tie = false;
		
		//initialize the JFrame
		theJFrame = new JFrame("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");
        
		theJFrame.setSize(1000, 700);
		theJFrame.getBackground();
		theJFrame.setBackground(Color.white);
		
        
		theJFrame.setVisible(true);
		//add listeners
		theJFrame.addMouseListener(this);
		theJFrame.addKeyListener(this);
        
		drawTheBoard();
		
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
		
		
		drawTheBoard();
		theJFrame.setVisible(true);
	}
    
    
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
        
	}
    
	//draw the gameboard, 9 boxes are made
	public void drawTheBoard(){
		
		//graphics on frame
		Graphics moreGraphics = theJFrame.getGraphics();
		
        
		//draw a circle of the same color as the background over the old circle
		moreGraphics.setColor(theJFrame.getBackground());
		Graphics2D theGraphics2D;
		
		theGraphics2D = (Graphics2D)moreGraphics;
		theGraphics2D.setStroke(new BasicStroke(10.0F));
        
		moreGraphics = theJFrame.getGraphics();
		moreGraphics.drawLine(200, 0, 200, 600);
		moreGraphics.drawLine(400, 0, 400, 600);
		moreGraphics.drawLine(0, 200, 600, 200);
		moreGraphics.drawLine(0, 400, 600, 400);
		moreGraphics.drawLine(600, 0, 600, 600);
	}
    
	//undoes a move
	private void undoMove(Moves pMove){
        
        
		int x = pMove.getX();
		int y = pMove.getY();
        
        
		Graphics2D theGraphics2D;
        
		//draw the board
		Graphics moreGraphics = theJFrame.getGraphics();
        
        
		//tell the player it is their turn
		if(theFlag == false){
			//it is now player x's turn
			theJFrame.setTitle("Turn: player X ||  O has "+ oWins +"  & X has " +xWins + " wins");
			//swap the flag
			theFlag = true;
		}else{
			//it is now player 0's turn
			theJFrame.setTitle("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");
			//swap the flag
			theFlag = false;
		}
        
        
		//if the flag is false, undo the last circle
		if (theFlag == false)
		{
			//reset each respective matching position
			if ((x < 200) && (y < 200)) {
				theX = 0; theY = 0; charArray[0][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y < 200)) {
				theX = 200; theY= 0; charArray[0][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y < 200)) {
				theX = 400; theY= 0; charArray[0][2] = '_';
            }
			if ((x < 200) && (y > 200) && (y < 400)) {
				theX = 0; theY= 200; charArray[1][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) {
				theX = 200; theY= 200; charArray[1][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) {
				theX = 400; theY= 200; charArray[1][2] = '_';
            }
			if ((x < 200) && (y > 400) && (y < 600)) {
				theX = 0; theY= 400; charArray[2][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) {
				theX = 200; theY= 400; charArray[2][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) {
				theX = 400; theY= 400; charArray[2][2] = '_';
            }
            
			//draw a circle of the same color as the background over the old circle
			moreGraphics.setColor(theJFrame.getBackground());
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.drawOval(theX + 10,theY+ 10, 160, 160);
            
            
		}
        
		//if theFlag is a true, undo the last X
		if (theFlag == true)
		{
            
            
			//reset each respective matching position
			if ((x < 200) && (y < 200)) {
				theX = 0; theY = 0; charArray[0][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y < 200)) {
				theX = 200; theY= 0; charArray[0][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y < 200)) {
				theX = 400; theY= 0; charArray[0][2] = '_';
            }
			if ((x < 200) && (y > 200) && (y < 400)) {
				theX = 0; theY= 200; charArray[1][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) {
				theX = 200; theY= 200; charArray[1][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) {
				theX = 400; theY= 200; charArray[1][2] = '_';
            }
			if ((x < 200) && (y > 400) && (y < 600)) {
				theX = 0; theY= 400; charArray[2][0] = '_';
            }
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) {
				theX = 200; theY= 400; charArray[2][1] = '_';
            }
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) {
				theX = 400; theY= 400; charArray[2][2] = '_';
            }
            
            
			//draw an X over the old X, same color as the background
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			theGraphics2D.setColor(theJFrame.getBackground());
			theGraphics2D.drawLine(theX + 10,theY+ 10,theX+ 170,theY+ 160);
			theGraphics2D.drawLine(theX + 170,theY+ 10,theX+ 10,theY+ 170);
            
		}
	}
    
    
    
	private void makeMove(int x, int y){
        
        
		Graphics2D theGraphics2D;
        
		//draw the board
		Graphics moreGraphics = theJFrame.getGraphics();
        
        
		//if the flag is false, draw a circle
		if (theFlag == false)
		{
			if ((x < 200) && (y < 200)) {
				if (charArray[0][0] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 0; theY = 0; charArray[0][0] = 'O';
			}
            
			if ((x > 200) && (x < 400) && (y < 200)) {
				if (charArray[0][1] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 200; theY= 0; charArray[0][1] = 'O';
			}
            
			if ((x > 400) && (x < 600) && (y < 200)) {
				if (charArray[0][2] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 400; theY= 0; charArray[0][2] = 'O';
                
			}
			if ((x < 200) && (y > 200) && (y < 400)) {
				if (charArray[1][0] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 0; theY= 200; charArray[1][0] = 'O';
                
                
			}
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) {
				if (charArray[1][1] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 200; theY= 200; charArray[1][1] = 'O';
			}
            
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) {
				if (charArray[1][2] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 400; theY= 200; charArray[1][2] = 'O';
			}
            
			if ((x < 200) && (y > 400) && (y < 600)) {
				if (charArray[2][0] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
                
				theX = 0; theY= 400; charArray[2][0] = 'O';
			}
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) {
				if (charArray[2][1] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
				theX = 200; theY= 400; charArray[2][1] = 'O';
                
			}
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) {
				if (charArray[2][2] != '_'){
					theJFrame.setTitle("player O that space already is taken");
					return;
				}
				theX = 400; theY= 400; charArray[2][2] = 'O';
			}
            
			//draw a circle
			moreGraphics.setColor(Color.BLACK);
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.drawOval(theX + 10,theY+ 10, 160, 160);
            
		}
        
		//if theflag is true, draw an X
		if (theFlag == true)
		{
			if ((x < 200) && (y < 200)) {
				if (charArray[0][0] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 0; theY = 0; charArray[0][0] = 'X';
			}
            
			if ((x > 200) && (x < 400) && (y < 200)) {
				if (charArray[0][1] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 200; theY= 0; charArray[0][1] = 'X';
			}
            
			if ((x > 400) && (x < 600) && (y < 200)) {
				if (charArray[0][2] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 400; theY= 0; charArray[0][2] = 'X';
			}
            
			if ((x < 200) && (y > 200) && (y < 400)) {
				if (charArray[1][0] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 0; theY= 200; charArray[1][0] = 'X';
			}
            
			if ((x > 200) && (x < 400) && (y > 200) && (y < 400)) {
				if (charArray[1][1] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 200; theY= 200; charArray[1][1] = 'X';
                
			}
            
			if ((x > 400) && (x < 600) && (y > 200) && (y < 400)) {
				if (charArray[1][2] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 400; theY= 200; charArray[1][2] = 'X';
			}
            
			if ((x < 200) && (y > 400) && (y < 600)) {
				if (charArray[2][0] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 0; theY= 400; charArray[2][0] = 'X';
			}
            
			if ((x > 200) && (x < 400) && (y > 400) && (y < 600)) {
				if (charArray[2][1] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 200; theY= 400; charArray[2][1] = 'X';
			}
            
			if ((x > 400) && (x < 600) && (y > 400) && (y < 600)) {
				if (charArray[2][2] != '_'){
					theJFrame.setTitle("player X that space already is taken");
					return;
				}
				theX = 400; theY= 400; charArray[2][2] = 'X';
			}
            
            
			//drawing an X
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			theGraphics2D.setColor(Color.RED);
			theGraphics2D.drawLine(theX + 10,theY+ 10,theX+ 170,theY+ 160);
			theGraphics2D.drawLine(theX + 170,theY+ 10,theX+ 10,theY+ 170);
            
		}
        
		//tell the player it is their turn
		if(theFlag == false){
			theJFrame.setTitle("Turn: player X ||  O has "+ oWins +"  & X has " +xWins +" wins");
			theFlag = true;
		}else{
			theJFrame.setTitle("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");
			theFlag = false;
		}
        
		//print array for testing this.printArray();
        
        
		//push the turn to the stack
		theMoves.push(new Moves(x, y));
        
		//check to see if the game has ended
		this.winner();
        
	}
    
    
	//is the there a winner?
	private void winner(){
        
		if(charArray[0][0] != '_' && charArray[1][0] != '_' && charArray[2][0] != '_' && charArray[0][1] != '_' && charArray[1][1] != '_' && charArray[2][1] != '_' && charArray[0][2] != '_' && charArray[1][2] != '_' && charArray[2][2] != '_' ){
			tie = true;
		}
		
		//is there a winner on a row or a column?
		for (int counter = 0; counter< 3; counter++){
            
			if((charArray[counter][0] == charArray[counter][1]) && (charArray[counter][0] == charArray[counter][2]) && charArray[counter][0] != '_') {
				//yes, there is a win
				won = true;
				break;
                
			}
            
            
			if((charArray[0][counter] == charArray[1][counter]) && (charArray[1][counter] == charArray[2][counter]) && charArray[0][counter] != '_'){
				//yes, there is a win
				won = true;
				break;
			}
		}
        
		//is there a diagonal winner?
        
		if(charArray[0][0] == charArray[1][1] && charArray [0][0] == charArray[2][2] && charArray[1][1] != '_'){
			//yes there is a diagonal win
			won = true;
            
		}
        
        
		if(charArray[0][2] == charArray[1][1] && charArray[0][2] == charArray[2][0] && charArray[1][1] != '_'){
			//yes there is a diaganol win
			won = true;
            
		}
		
		//did someone win?
		if(won == true || tie == true){
            
			if(theFlag == false && tie == false){
				xWins = xWins+1;
				
			}else if (theFlag == true && tie == false){
				oWins = oWins+1;
			}
			
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Play again?", "Player O: " + oWins + " vs Player X: " + xWins, dialogButton);
            
			//play again?
			if(dialogResult==0){
				//yes, reset won and tie to false
				won = false;
				tie = false;
				//who goes first is random
                
				
				
				//loop through the stack, undoing all moves and reseting the board
				for(int counter = theMoves.size(); counter > 0; counter --){
					this.undoMove(theMoves.pop());
                    
				}
				
                
			}else{
				
				theJFrame.dispose();
				//return to main menu
                
			}
            
            
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
    
	//*************************************************
	//the following methods are form their respective interfaces
    
	//*************************************************
	// following from MouseListener
    
	public void mousePressed(MouseEvent e) {
        
		//get the location the mouse was pressed
		int x = e.getX();
		int y = e.getY();
        
		//do not count count if the press is out of the board
		if(x > 600 || x< 0 || y < 0 || y > 600 ){
			return;
		}
        
		this.makeMove(x, y);
	}
    
    
	//**********************************************
	//following From KeyListener
    
	@Override
	public void keyPressed(KeyEvent e) {
        
        
		//was the z key pressed?
		if(Z_KEY==e.getKeyCode())
		{
			//yes
			zKeyPressed=true;
		}
        
		//was the control key pressed?
		if(CONTROL_KEY==e.getKeyCode())
		{
			//yes
			controlKeyPressed=true;
		}
        
		//were both the control and the zkey pressed?
		if(controlKeyPressed==true && zKeyPressed==true)
		{
            
			//are there moves in the stack?
			if (theMoves.isEmpty() == false){
				//yes, undo last move
				this.undoMove(theMoves.pop());
			}
		}
        
	}
    
    
    
	@Override
	public void keyReleased(KeyEvent e) {
        
		if(Z_KEY==e.getKeyCode())
		{
			zKeyPressed=false;
		}
        
		if(CONTROL_KEY==e.getKeyCode())
		{
			controlKeyPressed=false;
		}
	}
    
    
	//**********************************************
	//following methods unused. override interfaces
    
    
	//*************************************************
	// following from MouseListener
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
    
    
    
    
	//**********************************************
	//following From KeyListener
    
	@Override
	public void keyTyped(KeyEvent e) {
		//unused method
	}
    
    
    
    //main method for testing
    
	/*public static void main(String[] args)
     {
     
     //initialize the game, and then draw the board
     TicTacToeGame theGame = new TicTacToeGame();
     
     
     }
     */
    
    
}