
import java.awt.*;

import javax.swing.*;


//contains the main class
public class MainMenu extends JFrame {

	//creates the frame and the main panel (mainMenu) that we are going to be using for cardLayout to switch other panels on
	public static MainMenu frame = new MainMenu();
	public static JPanel mainMenu = new JPanel();
	public static CardLayout cardLayout = new CardLayout();

	public static void main(String[] args) {

		//creates the frame and sets 
		mainMenu.setLayout(cardLayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("M&M Arcade");
		frame.setSize(1000, 1000);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		mainMenu.setBounds(0, 0, 1000, 1000);
		mainMenu.setBackground(Color.black);
		frame.getContentPane().add(mainMenu);

		//make buttons
		JButton playScorched = new JButton("Play Archers");
		JButton playTicTacToe = new JButton("Play TicTacToe");

		//set button size
		playScorched.setBounds(0, 0, 500, 500);
		playTicTacToe.setBounds(500, 500, 500,500);
		//add buttons
		frame.add(playScorched);
		frame.add(playTicTacToe);

	
		 
		//make frame visible
		frame.setVisible(true);


		//flag for testing
		int flag = 2;
		
		if(flag == 0){

			//play scorched earth
			new ScorchedEarth();
			
		}else if (flag ==1 ){
			//play tic tac toe, and then draw the board
			TicTacToeGame theGame = new TicTacToeGame();
			theGame.drawTheBoard();
		}
	}
}