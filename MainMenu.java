/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */


import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;


//this class makes the main menu
public class MainMenu {

	

	//make mainMenu panel
	public static JPanel mainMenu = new JPanel();
	
	//constructor
	public MainMenu(){

		mainMenu.addKeyListener(new KonamiCode());
		mainMenu.setFocusable(true);
		mainMenu.requestFocusInWindow();
		
		//draw the menu when the constructor is called
		drawMenu();
	}

	
	//this method draws and displays the menu
	public void drawMenu() {

		mainMenu.setBounds(0, 0, 1000, 850);
		mainMenu.setBackground(Color.white);
		mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
		final JLabel archers;
		final JLabel ticTacToe;
		final JLabel instructions;
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
		instructions = new JLabel("Archer Instructions");
		instructions.setFont(new Font("Andalus", Font.BOLD, 60));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.add(Box.createRigidArea(new Dimension(5,50)));
		mainMenu.add(instructions);

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
				//new TicTacToeGame(); makes a new tic tac toe game
				new TicTacMenu();
			}
			public void mouseEntered(MouseEvent e){
				ticTacToe.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				ticTacToe.setForeground(Color.black);
			}
		});

		//show the instructions
		instructions.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				new TheInstructions();
			}
			public void mouseEntered(MouseEvent e){
				instructions.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				instructions.setForeground(Color.black);
			}
		});
	}

		
}	
