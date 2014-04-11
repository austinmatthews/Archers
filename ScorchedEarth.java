
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */

public class ScorchedEarth implements MouseListener {

	private JLabel p1Name;
	private JLabel p2Name;
	private JLabel round;
	private JLabel p1Health;
	private JLabel p2Health;
	private JLabel p1HealthNum;
	private JLabel p2HealthNum;
	private JPanel scorchedMenu = new JPanel();
	private JPanel scorchedGame = new JPanel();
	private JPanel scorchedInfo = new JPanel();
	private JPanel filler = new JPanel();
	private JPanel filler2 = new JPanel();
	private JPanel scorchedArena = new JPanel();
	private JPanel scorchedTurns = new JPanel();
	private JSlider powerSlider;
	private JSlider angleSlider;
	private JLabel next = new JLabel("Next");
	private JLabel power = new JLabel();
	private JLabel angle = new JLabel();
	private JLabel turnIndicator = new JLabel();
	private JLabel wallLabel;
	private JLabel p1ArcherLabel;
	private JLabel p2ArcherLabel;
	private JLabel p1ArrowLabel;
	private JLabel p2ArrowLabel;
	private int archerHeight = 100;
	private final int archerWidth = 71;
	private final int archerY = 338;
	private int archer1X;
	private int archer2X;
	private int click = 0;
	private int turn = 1;
	private int p1Num = 9;
	private int p2Num = 9;
	private int leftX, rightX, topY, bottomY, x, y = 397;
	private double currentTime;
	private int count = 0;
	private double gravity = 9.8;
	private Queue<Moves> theMoves = new LinkedList();
	private double xVelocity, yVelocity;
	private double time;
	boolean switchTurn;



	public ScorchedEarth(){
		initializeGame();
	}

	//initializes the game board, which includes the info panel on the top, the arena panel which is in the middle and the 
	//turns panel which is on the bottom
	public void initializeGame() {

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
	public void setTurns() {

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

	public void mousePressed(MouseEvent e){
		if (click < 10){
			click++;
			Moves theMove = new Moves(powerSlider.getValue(), angleSlider.getValue());
			theMoves.add(theMove);

			if (click == 9){
				next.setText("Play!");

				turnIndicator.setText("P2 - Turn 5");
			}
			else if (click == 10){
				turnIndicator.setForeground(Color.black);
				this.playGame();
			}
			else if (click % 2 == 0){
				turn++;
				turnIndicator.setText("P1 - Turn " + turn);
			}
			else{
				turnIndicator.setText("P2 - Turn " + turn);
			}
		}
	}

	public void mouseEntered(MouseEvent e){
		next.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent e){
		next.setForeground(Color.white);
	}


	public void setArena() {
		archersX();
		importWall();
		scorchedArena.add(wallLabel);
		wallLabel.setBounds(450, 200, 100, 300);
		importArchers();
		scorchedArena.add(p1ArcherLabel);
		p1ArcherLabel.setBounds(archer1X, 386, 71, 100);
		scorchedArena.add(p2ArcherLabel);
		p2ArcherLabel.setBounds(archer2X, 386, 71, 100);
		importArrows();
		scorchedArena.add(p1ArrowLabel);
		p1ArrowLabel.setBounds(archer1X, 397, 64, 32);
		scorchedArena.add(p2ArrowLabel);
		p2ArrowLabel.setBounds(archer2X, 397, 64, 32);

	}

	public void importWall(){

		BufferedImage wall = null;
		try {
			wall = ImageIO.read(new File("wall.png"));
		} catch (IOException e) {
			//do nothing
		}
		wallLabel = new JLabel(new ImageIcon(wall));
		wallLabel.setPreferredSize(new Dimension (100, 275));
	}

	public void importArchers(){

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
	public void importArrows(){

		BufferedImage arrow1 = null;
		BufferedImage arrow2 = null;
		try {
			arrow1 = ImageIO.read(new File("arrow1.png"));
			arrow2 = ImageIO.read(new File("arrow2.png"));	
		} catch (IOException e) {
			//do nothing
		}

		p2ArrowLabel = new JLabel(new ImageIcon(arrow2));
		p2ArrowLabel.setPreferredSize(new Dimension (64, 32));
		p1ArrowLabel = new JLabel(new ImageIcon(arrow1));
		p1ArrowLabel.setPreferredSize(new Dimension (64, 32));

	}


	public void archersX (){
		archer1X = (int)(Math.random() * ((350) + 1));
		archer2X =  650 + (int)(Math.random() * ((929 - 650) + 1));

	}


	public void setInfo() {

		p1Name = new JLabel("Player 1");
		p1Name.setFont(new Font("Andalus", Font.BOLD, 40));
		p1Name.setForeground(Color.black);

		p2Name = new JLabel("Player 2");
		p2Name.setFont(new Font("Andalus", Font.BOLD, 40));
		p2Name.setForeground(Color.black);

		round = new JLabel("Round 1");
		round.setFont(new Font("Andalus", Font.BOLD, 40));
		round.setForeground(Color.black);

		p1Health = new JLabel("P1 Health - ");
		p1Health.setFont(new Font("Andalus", Font.BOLD, 40));
		p1Health.setForeground(Color.black);

		p2Health = new JLabel("P2 Health - ");
		p2Health.setFont(new Font("Andalus", Font.BOLD, 40));
		p2Health.setForeground(Color.black);

		p1HealthNum = new JLabel("" + p1Num);
		p1HealthNum.setFont(new Font("Andalus", Font.BOLD, 40));
		p1HealthNum.setForeground(Color.black);

		p2HealthNum = new JLabel("" + p2Num);
		p2HealthNum.setFont(new Font("Andalus", Font.BOLD, 40));
		p2HealthNum.setForeground(Color.black);

		scorchedInfo.add(Box.createRigidArea(new Dimension(100,5)));
		scorchedInfo.add(p1Name);
		scorchedInfo.add(Box.createRigidArea(new Dimension(130,5)));
		scorchedInfo.add(round);
		scorchedInfo.add(Box.createRigidArea(new Dimension(130,5)));
		scorchedInfo.add(p2Name);
		scorchedInfo.add(Box.createRigidArea(new Dimension(100,5)));
		scorchedInfo.add(p1Health);
		scorchedInfo.add(p1HealthNum);
		scorchedInfo.add(Box.createRigidArea(new Dimension(300,5)));
		scorchedInfo.add(p2Health);
		scorchedInfo.add(p2HealthNum);

	}

	public void playGame(){
		switchTurn = true;
		int theCounter = 0;

		do{
			this.getMove(theMoves.poll());
			theCounter++;

		} while(theCounter < 10);

	}

	/*where the game actually takes place, after the last turn is put in
	the game begins and goes through the queue to display to turns'
	I got all of the math right for it, it needs to just set the location of the arrow1 and arrow2 images
	 */
	public void getMove(Moves theMove){
		y = 397;

		//the next 4 lines print out to tell me which turn it was on odd numbers are player 1, even numbers are player 2
		count++;
		System.out.println("");
		System.out.println("" + count);
		System.out.println("");
		int intPower = theMove.getX();
		int intAngle = theMove.getY();
		double rad = Math.toRadians(intAngle);
		xVelocity = intPower*Math.cos(rad);
		yVelocity = intPower*Math.sin(rad);

		//an if statement to decide which player it is. It is p1 if switchturn is true 
		if (switchTurn == true){
			x = archer1X;
			leftX = archer1X;
			rightX = leftX + 71;
			topY = 400;
			bottomY = topY + 100;
			time = 0;

			while(y < 500){
				yVelocity = yVelocity - gravity * time;
				x = (int) (x + xVelocity * time);
				y = (int) (y -  yVelocity * time);
				timer.start();
			}
			switchTurn = false;
		}

		//this is for p2
		if (switchTurn == false){
			x = archer2X;
			leftX = archer2X;
			rightX = leftX + 71;
			topY = 400;
			bottomY = topY + 100;
			time = 0;


			while(y < 500){
				yVelocity = yVelocity - gravity * time;
				x = (int) (x + xVelocity * time);
				y = (int) (y -  yVelocity * time);
				timer.start();
			}
			switchTurn = true;	
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// unused
	}

	public void mouseReleased(MouseEvent arg0) {
		// unused
	}

	TimerListener listen = new TimerListener();
	Timer timer = new Timer(1000, listen);

	private class TimerListener implements ActionListener {

		public void actionPerformed (ActionEvent event) {

			if (switchTurn == true){
				leftX = archer1X;
				rightX = leftX + 71;
				topY = 338;
				bottomY = topY + 100;

				p1ArrowLabel.setLocation(x, y);

				//this isnt right, it is supposed to be the 4 corners of the other player
				if((x < rightX) && (x > leftX) && (y < bottomY) && (y > topY)){
					p2Num = p2Num - 2;
					p2HealthNum.setText("" + p2Num);
				}

			}
			else{
				leftX = archer2X;
				rightX = leftX - 71;
				topY = 338;
				bottomY = topY + 100;

				while(y < 500){
					p2ArrowLabel.setLocation(x, y);

					//this isnt right, it is supposed to be the 4 corners of the other player
					if((x < rightX) && (x > leftX) && (y < bottomY) && (y > topY)){
						p1Num = p1Num - 2;
						p1HealthNum.setText("" + p1Num);
					}
				}

			}
		}

	}
}


//what we need to do
//make it so that after the round ends, it plays another round, up to 5 round
//then ask if they want to play again

//we need to update the round title at the top, its just a set text command
//I think that is all, but I would second check me with the projects sheet to see if we missed anything



