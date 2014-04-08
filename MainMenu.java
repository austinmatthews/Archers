/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;



public class MainMenu {

	//make mainMenu panel
	public static JPanel mainMenu = new JPanel();

	public MainMenu(){
		drawMenu();

		/*
		BufferedImage arcadeImage = null;

		try {
			arcadeImage = ImageIO.read(new File("arcade.jpg"));
		} catch (Exception e) {
			System.out.println("No Image");
		}

		JLabel picLabel = new JLabel(new ImageIcon(arcadeImage));
		 */



	}



	//flag for testing
	//	int flag = 2;
	/*
		if (flag ==1 ){
			//play tic tac toe, and then draw the board
			TicTacToeGame theGame = new TicTacToeGame();
			theGame.drawTheBoard();
		}

	}

	 */

	public void drawMenu() {


		mainMenu.setBounds(0, 0, 1000, 1000);
		mainMenu.setBackground(Color.white);
		mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
		final JLabel archers;
		final JLabel ticTacToe;
		final JLabel exit;
		JLabel title;

		//A label is created and displayed prompting the user to choose a game"
		title = new JLabel("Pick Your Game!");
		title.setFont(new Font("Andalus", Font.BOLD, 100));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));
		mainMenu.add(title);

		//displays the archers game button
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		archers = new JLabel("Archers!");
		archers.setFont(new Font("Andalus", Font.BOLD, 60));
		archers.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));
		mainMenu.add(archers);

		//displays the archers game button
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		ticTacToe = new JLabel("TicTacToe");
		ticTacToe.setFont(new Font("Andalus", Font.BOLD, 60));
		ticTacToe.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));
		mainMenu.add(ticTacToe);

		//displays the archers game button
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		exit = new JLabel("Exit");
		exit.setFont(new Font("Andalus", Font.BOLD, 60));
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));
		mainMenu.add(exit);

		P4Arcade.mainPanel.add(mainMenu, "MainMenu");

		archers.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				new ScorchedEarth();
			}
			public void mouseEntered(MouseEvent e){
				archers.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				archers.setForeground(Color.black);
			}
		});

		ticTacToe.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				TicTacToeGame theGame = new TicTacToeGame();
				theGame.drawTheBoard();
			}
			public void mouseEntered(MouseEvent e){
				ticTacToe.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				ticTacToe.setForeground(Color.black);
			}
		});
		
		exit.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				System.exit(0);
			}
			public void mouseEntered(MouseEvent e){
				exit.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				exit.setForeground(Color.black);
			}
		});
	}
}