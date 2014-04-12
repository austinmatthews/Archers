

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Matthew Vollkommer and Austin Matthews
 *
 *
 */

public class ScorchedEarth implements MouseListener {

	public JLabel p1Name;
	public JLabel p2Name;
	public JLabel round;
	public JLabel p1Health;
	public JLabel p2Health;
	public JLabel p1Wins;
	public JLabel p2Wins;
	public JLabel p1HealthNum;
	public JLabel p2HealthNum;
	public JLabel p1WinNum;
	public JLabel p2WinNum;
	public JPanel scorchedMenu = new JPanel();
	public JPanel scorchedGame = new JPanel();
	public JPanel scorchedInfo = new JPanel();
	public JPanel filler = new JPanel();
	public JPanel filler2 = new JPanel();
	public JPanel scorchedArena = new JPanel();
	public JPanel scorchedTurns = new JPanel();
	public JSlider powerSlider;
	public JSlider angleSlider;
	public JLabel next = new JLabel("Next");
	public JLabel power = new JLabel();
	public JLabel angle = new JLabel();
	public JLabel turnIndicator = new JLabel();
	public JLabel wallLabel;
	public JLabel p1ArcherLabel;
	public JLabel p2ArcherLabel;
	private int archer1X;
	private int archer2X;
	private int click = 0;
	private int turn = 1;
	private int p1Num = 5;
	private int roundNum = 1;
	private int p2Num = 5;
	private int p1NumWins = 0;
	private int p2NumWins = 0;
	private int x, y = 397;
	private int count = 0;
	private double gravity = 9.8;
	private Queue<Round> theRounds = new LinkedList<Round>();
	private double xVelocity, yVelocity, initialYVelocity;
	private double time;
	private boolean switchTurn;
	Graphics archer1Arrow;
	Graphics archer2Arrow;
	BufferedImage arrow1 = null;
	BufferedImage arrow2 = null;

	public ScorchedEarth(){
		initializeGame();
	}

	//initializes the game board, which includes the info panel on the top, the arena panel which is in the middle and the
	//turns panel which is on the bottom
	private void initializeGame() {

		//sets the bounds of the game panels
		scorchedGame.setBounds(0, 0, 1000, 850);
		scorchedInfo.setMaximumSize(new Dimension(1000, 140));
		filler.setMaximumSize(new Dimension(1000, 4));
		scorchedArena.setMaximumSize(new Dimension(1000, 500));
		scorchedArena.setLayout(null);
		filler2.setMaximumSize(new Dimension(1000, 4));
		scorchedTurns.setMaximumSize(new Dimension(1000, 210));


		//sets the background colors of the game panels
		scorchedGame.setBackground(Color.black);
		scorchedInfo.setBackground(Color.white);
		filler.setBackground(Color.black);
		scorchedArena.setBackground(Color.white);
		filler2.setBackground(Color.green);
		scorchedTurns.setBackground(Color.black);
		scorchedTurns.setLocation(0,754);

		//adds the game panels to the main game panel, and switches from the menu panel to the game panel
		scorchedGame.setLayout(new BoxLayout(scorchedGame, BoxLayout.Y_AXIS));
		P4Arcade.mainPanel.add(scorchedGame, "scorchedGame");
		scorchedGame.add(scorchedInfo);
		scorchedGame.add(filler);
		scorchedGame.add(scorchedArena);
		scorchedGame.add(filler2);
		scorchedGame.add(scorchedTurns);
		setInfo();
		setArena();
		setTurns();
		P4Arcade.cardLayout.show(P4Arcade.mainPanel, "scorchedGame");

	}

	//sets up all of the things on the turns panel, which is on the bottom of the frame
	private void setTurns() {

		powerSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		powerSlider.setMajorTickSpacing(10);
		powerSlider.setPaintTicks(true);
		powerSlider.setPaintLabels(true);
		powerSlider.setForeground(Color.white);
		powerSlider.setBackground(Color.black);
		powerSlider.setPreferredSize(new Dimension (400, 40));

		angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		angleSlider.setMajorTickSpacing(5);
		angleSlider.setPaintTicks(true);
		angleSlider.setPaintLabels(true);
		angleSlider.setForeground(Color.white);
		angleSlider.setBackground(Color.black);
		angleSlider.setPreferredSize(new Dimension (400, 40));

		turnIndicator = new JLabel("P1 - Turn " + turn);
		turnIndicator.setFont(new Font("Andalus", Font.BOLD, 40));
		turnIndicator.setForeground(Color.white);

		next.setFont(new Font("Andalus", Font.BOLD, 25));
		next.setForeground(Color.white);

		power = new JLabel("Power");
		power.setFont(new Font("Andalus", Font.BOLD, 40));
		power.setForeground(Color.white);

		angle = new JLabel("Angle");
		angle.setFont(new Font("Andalus", Font.BOLD, 40));
		angle.setForeground(Color.white);

		scorchedTurns.add(Box.createRigidArea(new Dimension(350,5)));
		scorchedTurns.add(turnIndicator);
		scorchedTurns.add(Box.createRigidArea(new Dimension(350,5)));
		scorchedTurns.add(power);
		scorchedTurns.add(Box.createRigidArea(new Dimension(375,5)));
		scorchedTurns.add(angle);
		scorchedTurns.add(powerSlider);
		scorchedTurns.add(next);
		scorchedTurns.add(angleSlider);
		next.addMouseListener(this);
	}

	//detects the mouse being pressed in the button area
	public void mousePressed(MouseEvent e){
		

		if(roundNum <= 5){
			//are there less than ten click?
			if (click < 10){
				//yes, add one to the click, get the values from the sliders, and add it to the que
				click++;
				Round theRound = new Round(powerSlider.getValue(), angleSlider.getValue());
				theRounds.add(theRound);

				//is this the tenth click? 
				if (click == 9){

					//yes, set the button text to play
					next.setText("Play!");

					//and then update the turn indicator
					turnIndicator.setText("P2 - Turn 5");
				}

				//if it is the tenth click
				else if (click == 10){
					//set the foreground to black
					turnIndicator.setForeground(Color.black);
					turnIndicator.paintImmediately(turnIndicator.getVisibleRect());

					//and play the game
					this.playGame();
				}

				//if it is an even turn
				else if (click % 2 == 0){

					//add one to turns counter
					turn++;

					//and tell p1 that it is their turn
					turnIndicator.setText("P1 - Turn " + turn);
				}

				//else
				else{

					//tell p2 it is their turn
					turnIndicator.setText("P2 - Turn " + turn);
				}
			}
		}
	
	}

	//when mouse is entered, turn the foreground to red
	public void mouseEntered(MouseEvent e){
		next.setForeground(Color.red);
	}

	//when the mouse exit, turn the foreground to white
	public void mouseExited(MouseEvent e){
		next.setForeground(Color.white);
	}


	//initialize the arena
	private void setArena() {
		archersX();
		importWall();
		scorchedArena.add(wallLabel);
		wallLabel.setBounds(450, 200, 100, 300);
		importArchers();

		scorchedArena.add(p1ArcherLabel);
		p1ArcherLabel.setBounds(archer1X, 386, 71, 100);
		scorchedArena.add(p2ArcherLabel);
		p2ArcherLabel.setBounds(archer2X, 386, 71, 100);

	}

	//import the wall image
	private void importWall(){

		BufferedImage wall = null;

		try {
			wall = ImageIO.read(new File("wall.png"));
		} catch (IOException e) {
			//do nothing
		}
		wallLabel = new JLabel(new ImageIcon(wall));
		wallLabel.setPreferredSize(new Dimension (100, 275));
	}

	//import the archer images
	private void importArchers(){

		BufferedImage archer1 = null;
		BufferedImage archer2 = null;
		try {
			archer1 = ImageIO.read(new File("archer1.jpg"));
			archer2 = ImageIO.read(new File("archer2.jpg"));
		} catch (IOException e) {
			//do nothing
		}

		p2ArcherLabel = new JLabel(new ImageIcon(archer2));
		p2ArcherLabel.setPreferredSize(new Dimension (75, 100));
		p1ArcherLabel = new JLabel(new ImageIcon(archer1));
		p1ArcherLabel.setPreferredSize(new Dimension (75, 100));

	}


	//place the archers on the board and set their x Coordinates
	private void archersX (){
		archer1X = (int)(Math.random() * ((350) + 1));
		archer2X =  650 + (int)(Math.random() * ((929 - 650) + 1));

	}

	//initialize the info panel at the top of the frame
	private void setInfo() {

		p1Name = new JLabel("Player 1");
		p1Name.setFont(new Font("Andalus", Font.BOLD, 40));
		p1Name.setForeground(Color.black);

		p2Name = new JLabel("Player 2");
		p2Name.setFont(new Font("Andalus", Font.BOLD, 40));
		p2Name.setForeground(Color.black);

		round = new JLabel("Round " + roundNum);
		round.setFont(new Font("Andalus", Font.BOLD, 40));
		round.setForeground(Color.black);

		//set p1 one health
		p1Health = new JLabel("P1 Health - ");
		p1Health.setFont(new Font("Andalus", Font.BOLD, 25));
		p1Health.setForeground(Color.black);

		//set p2 health
		p2Health = new JLabel("P2 Health - ");
		p2Health.setFont(new Font("Andalus", Font.BOLD, 25));
		p2Health.setForeground(Color.black);

		//set p1 one health
		p1Wins = new JLabel("P1 Wins - ");
		p1Wins.setFont(new Font("Andalus", Font.BOLD, 25));
		p1Wins.setForeground(Color.black);

		//set p2 health
		p2Wins = new JLabel("P2 Wins - ");
		p2Wins.setFont(new Font("Andalus", Font.BOLD, 25));
		p2Wins.setForeground(Color.black);

		//set p1 health
		p1HealthNum = new JLabel("" + p1Num);
		p1HealthNum.setFont(new Font("Andalus", Font.BOLD, 25));
		p1HealthNum.setForeground(Color.black);

		//set p2 health
		p2HealthNum = new JLabel("" + p2Num);
		p2HealthNum.setFont(new Font("Andalus", Font.BOLD, 25));
		p2HealthNum.setForeground(Color.black);

		//set p1 health
		p1WinNum = new JLabel("" + p1NumWins);
		p1WinNum.setFont(new Font("Andalus", Font.BOLD, 25));
		p1WinNum.setForeground(Color.black);

		//set p2 health
		p2WinNum = new JLabel("" + p2NumWins);
		p2WinNum.setFont(new Font("Andalus", Font.BOLD, 25));
		p2WinNum.setForeground(Color.black);

		//add the labels
		scorchedInfo.add(Box.createRigidArea(new Dimension(100,5)));
		scorchedInfo.add(p1Name);
		scorchedInfo.add(Box.createRigidArea(new Dimension(130,5)));
		scorchedInfo.add(round);
		scorchedInfo.add(Box.createRigidArea(new Dimension(130,5)));
		scorchedInfo.add(p2Name);
		scorchedInfo.add(Box.createRigidArea(new Dimension(100,5)));
		scorchedInfo.add(p1Health);
		scorchedInfo.add(p1HealthNum);
		scorchedInfo.add(Box.createRigidArea(new Dimension(80,5)));
		scorchedInfo.add(p1Wins);
		scorchedInfo.add(p1WinNum);
		scorchedInfo.add(Box.createRigidArea(new Dimension(140,5)));
		scorchedInfo.add(p2Wins);
		scorchedInfo.add(p2WinNum);
		scorchedInfo.add(Box.createRigidArea(new Dimension(80,5)));
		scorchedInfo.add(p2Health);
		scorchedInfo.add(p2HealthNum);

	}

	//play the game, looping through the queue of submitted turns
	private void playGame(){
		switchTurn = true;
		int theCounter = 0;

		do{
			this.getMove(theRounds.poll());
			theCounter++;

		} while(theCounter < 10);
		initialize();
	}



	//delay the game
	private void delay(){
		long start = System.currentTimeMillis();
		long end = start + 20;
		//long end = start + 1;
		while (System.currentTimeMillis() < end)
		{
			// run
		}
	}


	/*where the game actually takes place, after the last turn is put in
     the game begins and goes through the queue to display to turns'
     I got all of the math right for it, it needs to just set the location of the arrow1 and arrow2 images
	 */
	private void getMove(Round theRound){

		//this is where both of the archers' bows are on the game panel
		y = 397;


		//set graphics

		Graphics2D theGraphics2D;
		Graphics moreGraphics = scorchedArena.getGraphics();
		theGraphics2D = (Graphics2D)moreGraphics;
		theGraphics2D.setStroke(new BasicStroke(5.0F));


		//the next 4 lines print out to tell me which turn it was on odd numbers are player 1, even numbers are player 2


		// Initialize variables
		int intPower = theRound.getX() /4;
		int intAngle = theRound.getY();
		double rad = Math.toRadians(intAngle);
		xVelocity = intPower*Math.cos(rad);
		initialYVelocity = intPower*Math.sin(rad);
		time = 0;

		//an if statement to decide which player it is. It is p1 if switchturn is true
		//p1's turn
		if (switchTurn == true){
			x = archer1X;




			//loop through time, redisplaying the arrow image at 
			//appropriate location
			while(y < 500){

				delay();


				//erase the last arrow
				moreGraphics.setColor(Color.WHITE);
				theGraphics2D.drawLine(x + 50, y + 20, x+50, y + 20);



				//calculate the x and y coordinates

				//radians - 9.8 * time, will always be negative
				yVelocity = initialYVelocity - gravity * time;
				x = (int) (x + xVelocity * time);
				y = (int) (y -  yVelocity * time);



				//draw the arrow
				moreGraphics.setColor(Color.RED);
				theGraphics2D.drawLine(x + 50, y + 20, x+50, y + 20);


				if( hitP2(x+50, y+20) == true  || hitWall(x+50, y+20) == true || x+50 < -20 || x+50 > 1000){

					moreGraphics.setColor(Color.RED);
					theGraphics2D.drawLine(x + 50, y+20, x+50, y + 20);

					//end the loop, a hit occured
					break;

				}


				//add to the time add the end of each loop
				//time is arbitrary. we can control it
				time = time + .005;

				//repeat the loop
			}

			//it is now player 2's turn
			switchTurn = false;
		}


		// p2 calculations are different ? why?
		//this is for p2
		else{

			x = archer2X;

			//loop through time, redisplaying the arrow image at 
			//appropriate location
			while(y < 500){


				delay();


				//erase the last arrow
				moreGraphics.setColor(Color.WHITE);
				theGraphics2D.drawLine(x, y+20, x, y + 20);



				//calculate the x and y coordinates

				//radians - 9.8 * time, will always be negative
				yVelocity = initialYVelocity - gravity * time;
				x = (int) (x - xVelocity * time);
				y = (int) (y -  yVelocity * time);



				//draw the arrow
				moreGraphics.setColor(Color.RED);
				theGraphics2D.drawLine(x, y+20, x, y + 20);


				if(hitP1(x, y+20) == true  || hitWall(x, y+20) == true || x< -20 || y + 20 >500){
					moreGraphics.setColor(Color.RED);
					theGraphics2D.drawLine(x, y+20, x, y + 20);

					//end the loop, a hit occured
					break;

				}


				//add to the time add the end of each loop
				//time is arbitrary. we can control it
				time = time + .005;

				//repeat the loop
			}

			//it is now player 2's turn
			switchTurn = true;
		}
	}

	//was the wall hit?
	private boolean hitWall(int arrowX, int arrowY){
		boolean hit = false;
		if(((arrowX < 550) && (arrowX > 450) && (arrowY < 550) && (arrowY > 200) )){
			hit = true;
		}

		return hit;
	}

	//was p1 hit?
	private boolean hitP1(int arrowX, int arrowY){

		//initialize hit variable
		boolean hit = false;

		//was P1 hit?
		if((arrowX < archer1X + 71) && (arrowX > archer1X) && (arrowY < 386 + 100) && (arrowY > 386)){
			//yes, deduct health
			p1Num = p1Num - 1;
			p1HealthNum.setText("" + p1Num);
			p1HealthNum.paintImmediately(p1HealthNum.getVisibleRect());
			hit = true;
			if(p1Num == 0){
				winner2();
			}


		}

		return hit;
	}

	//was p2 hit?
	private boolean hitP2(int arrowX, int arrowY){

		//initialize hit
		boolean hit = false;


		//did the arrow hit p2?
		if((arrowX < archer2X + 71) && (arrowX > archer2X) && (arrowY < 386+100) && (arrowY > 386)){

			//yes deduct health
			p2Num = p2Num - 1;
			p2HealthNum.setText("" + p2Num);
			p2HealthNum.paintImmediately(p2HealthNum.getVisibleRect());
			hit = true;
			if(p2Num == 0){
				winner1();
			}

		}

		//return hit
		return hit;
	}



	private void winner1(){
		
		int theWinner = 1;
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Play again?", "P3 Wins", dialogButton);

		//play again?
		if(dialogResult==0){

			//start a new game
			initializeNextGame(theWinner);

			


		}else{

			//end the game
			System.exit(0);

		}

	
	}
	
	private void winner2(){
		
		int theWinner = 2;
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Play again?", "P2 Wins", dialogButton);

		//play again?
		if(dialogResult==0){

			//start a new game
			initializeNextGame( theWinner);

			


		}else{

			//end the game
			System.exit(0);

		}

	
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// unused
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// unused
	}
	public void initialize(){
		roundNum++;
		if(roundNum > 5){
			initializeNextGame(0);
		}
		round.setText("Round " + roundNum);
		turnIndicator.setText("P1 - Turn " + 1);
		next.setText("Next");
		turn = 1;
		turnIndicator.setForeground(Color.white);
		click = 0;
	}
	
	public void initializeNextGame(int theWinner){
		roundNum = 1;
		round.setText("Round " + roundNum);
		turnIndicator.setText("P1 - Turn " + 1);
		next.setText("Next");
		turn = 1;
		turnIndicator.setForeground(Color.white);
		click = 0;
		p1Num = 5;
		p2Num = 5;
		archersX();
		
		if(theWinner == 1){
			//give p1 a win
			p1NumWins++;
			p1Wins.setText("P1 Wins - " + p1NumWins);
		}else if(theWinner == 2){
			p2NumWins++;
			p2Wins.setText("P2 Wins - " + p2NumWins);
		}else{
			//tie give no wins
		
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Play again?", "Tie Game", dialogButton);
			//play again?
			if(dialogResult==0){

			//close dialog  box
			//do nothing, play game
				


			}else{

				//end the game
				System.exit(0);

			}

		}
		
	}

} 

