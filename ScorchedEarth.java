
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Queue;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */

public class ScorchedEarth {


	JPanel scorchedMenu = new JPanel();
	JPanel scorchedGame = new JPanel();
	JPanel scorchedInfo = new JPanel();
	JPanel filler = new JPanel();
	JPanel filler2 = new JPanel();
	JPanel scorchedArena = new JPanel();
	JPanel scorchedTurns = new JPanel();
	JSlider powerSlider = new JSlider();
	JSlider angleSlider = new JSlider();
	JLabel next = new JLabel("Next");
	JLabel power = new JLabel();
	JLabel angle = new JLabel();
	JLabel turnIndicator = new JLabel();
	JLabel wallLabel;
	JLabel p1ArcherLabel;
	JLabel p2ArcherLabel;
	int archerHeight = 100;
	int archerWidth = 71;
	int archerY = 338;
	int archer1X;
	int archer2X;
	int click = 0;
	int turn = 1;
	private Queue<Moves> theMoves;
	int intAngle;
	int intPower;
	
	double xVelocity, yVelocity, x, y, velocity;
	int gravity = 10;
	int time = 0;
	

	public ScorchedEarth(){
		initializeGame();
		playGame();
	}

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

	public void setTurns() {

		powerSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		powerSlider.setMajorTickSpacing(10);
		powerSlider.setPaintTicks(true);
		powerSlider.setPaintLabels(true);
		powerListener powerListener = new powerListener();
		powerSlider.addChangeListener(powerListener);
		powerSlider.setPreferredSize(new Dimension (400, 40));

		angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		angleSlider.setMajorTickSpacing(5);
		angleSlider.setPaintTicks(true);
		angleSlider.setPaintLabels(true);
		angleListener p1AngleListener = new angleListener();
		angleSlider.addChangeListener(p1AngleListener);
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
		next.addMouseListener(new MouseInputAdapter(){

			public void mousePressed(MouseEvent e){
				if (click < 10){
					click++;
					
					theMoves.add(new Moves(intPower, intAngle));
					
					
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
			
			public void playGame(){
				
				do{
					this.getMove(theMoves.poll());
				
				} while(!(theMoves.peek().equals(null)));
			
				
			}
			
			public void getMove(Moves theMove){
				
			
				
				intPower = theMove.getX();
				intAngle = theMove.getY();
				
				
				
				xVelocity = intPower*Math.cos(intAngle);
				
				
				yVelocity = intPower*Math.sin(intAngle);
				
				yVelocity = yVelocity - gravity * time;
				
				
				x = x + xVelocity * time;
				
				
				y = y +  yVelocity * time;
				
			}
			
		});
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

	public void archersX (){
		archer1X = (int)(Math.random() * ((350) + 1));
		archer2X =  650 + (int)(Math.random() * ((929 - 650) + 1));

	}


	public void setInfo() {

		JLabel p1Name;
		JLabel p2Name;
		JLabel round;
		JLabel p1Health;
		JLabel p2Health;
		JLabel p1HealthNum;
		JLabel p2HealthNum;

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

		p1HealthNum = new JLabel("10");
		p1HealthNum.setFont(new Font("Andalus", Font.BOLD, 40));
		p1HealthNum.setForeground(Color.black);

		p2HealthNum = new JLabel("10");
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

	public class powerListener implements ChangeListener {
		
		public void stateChanged (ChangeEvent event) {
			int intPower = powerSlider.getValue();
			
			
		}
	}

	public class angleListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			int intAngle = angleSlider.getValue();
		}
	}
	


	

	}




