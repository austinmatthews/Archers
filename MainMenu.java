/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



//contains the main class
public class MainMenu extends JFrame implements ActionListener {

	//creates the frame and the main panel (mainMenu) that we are going to be using for cardLayout to switch other panels on
	public static MainMenu frame = new MainMenu();
	public static JPanel mainMenu = new JPanel();
	public static CardLayout cardLayout = new CardLayout();
	public static BorderLayout borderLayout = new BorderLayout();




	public static void main(String[] args) {

		//creates the frame and sets 
		mainMenu.setLayout(borderLayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("M&M Arcade");

		//1000X1000 is a lot. for example many displays like macbook pros are 1440X900, 1000X1000 wont fit that display
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		mainMenu.setBounds(0, 0, 800, 800);
		mainMenu.setBackground(Color.black);
		frame.getContentPane().add(mainMenu);

		//make buttons
		JButton playScorched = new JButton("Play Archers");
		JButton playTicTacToe = new JButton("Play TicTacToe");
		JButton quitArcarde = new JButton("Exit");


		//add button listeners; this does not work in a static method. how should we implement this?
	//	playScorched.addActionListener(this);
	//	playTicTacToe.addActionListener(this);
	//	quitArcarde.addActionListner(this);

		//get image

		BufferedImage arcadeImage = null;

		try {
			arcadeImage = ImageIO.read(new File("arcade.jpg"));
		} catch (Exception e) {
			//do nothing
		}

		JLabel picLabel = new JLabel(new ImageIcon(arcadeImage));




		//add buttons and image
		mainMenu.add(playScorched, BorderLayout.EAST);
		mainMenu.add(playTicTacToe, BorderLayout.WEST);
		mainMenu.add(picLabel, BorderLayout.CENTER);
		mainMenu.add(quitArcarde, BorderLayout.SOUTH);




		//make frame visible
		frame.setVisible(true);


		//flag for testing
		int flag = 2;



		//play scorched earth
		new ScorchedEarth();

		if (flag ==1 ){
			//play tic tac toe, and then draw the board
			TicTacToeGame theGame = new TicTacToeGame();
			theGame.drawTheBoard();
		}

	}





	@Override
	public void actionPerformed(ActionEvent event) {

		// --------------------------------------------------------------
		// Determines which button was pressed and sets the label
		// text accordingly.
		// also takes the appropriate action
		// --------------------------------------------------------------

		if (event.getActionCommand().equals("Exit")) {
			System.exit(0);


		} else if (event.getActionCommand().equals("Play TicTacToe")){

		}else if (event.getActionCommand().equals("Play Archers")){

		}
	}
}