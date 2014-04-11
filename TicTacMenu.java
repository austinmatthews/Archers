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
public class TicTacMenu {

	

	//make mainMenu panel
	public static JPanel ticMenu = new JPanel();
	
	//constructor
	public TicTacMenu(){

		this.initialize();
		
		//draw the menu when the constructor is called
		//drawMenu();
	}

	//initializes the game board, which includes the info panel on the top, the arena panel which is in the middle and the 
	//turns panel which is on the bottom
	public void initialize() {

		drawMenu();
		
		P4Arcade.mainPanel.add(ticMenu, "TicTacToe");

		P4Arcade.cardLayout.show(P4Arcade.mainPanel, "TicTacToe");
		
	

	}
	//this method draws and displays the menu
	public void drawMenu() {

		ticMenu.setBounds(0, 0, 1000, 850);
		ticMenu.setBackground(Color.white);
		ticMenu.setLayout(new BoxLayout(ticMenu, BoxLayout.Y_AXIS));
		final JLabel human;
		final JLabel RandomCPU;
		final JLabel Back;
		JLabel title;

		//A label is created and displayed prompting the user to choose a game"
		title = new JLabel("Pick Your Game!");
		title.setFont(new Font("Andalus", Font.BOLD, 100));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));
		ticMenu.add(title);

		//displays the PVP game button
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		human = new JLabel("P V P!");
		human.setFont(new Font("Andalus", Font.BOLD, 60));
		human.setAlignmentX(Component.CENTER_ALIGNMENT);
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));
		ticMenu.add(human);

		//displays the PVRandom game button
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		RandomCPU = new JLabel("P V RandomCPU");
		RandomCPU.setFont(new Font("Andalus", Font.BOLD, 60));
		RandomCPU.setAlignmentX(Component.CENTER_ALIGNMENT);
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));
		ticMenu.add(RandomCPU);

		//displays the exit game button
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));	
		Back = new JLabel("Exit");
		Back.setFont(new Font("Andalus", Font.BOLD, 60));
		Back.setAlignmentX(Component.CENTER_ALIGNMENT);
		ticMenu.add(Box.createRigidArea(new Dimension(5,50)));
		ticMenu.add(Back);

		P4Arcade.mainPanel.add(ticMenu, "TicMenu");

		human.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				//play against human
				new TicTacToeGame();
			}
			public void mouseEntered(MouseEvent e){
				human.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				human.setForeground(Color.black);
			}
		});

		
		RandomCPU.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){
				//play against random
				new CPUTicTacToe();
			}
			public void mouseEntered(MouseEvent e){
				RandomCPU.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				RandomCPU.setForeground(Color.black);
			}
		});

		Back.addMouseListener(new MouseInputAdapter(){
			//recursive method that calls itself whenever the shape is pressed, the majority of the game happens in this method
			public void mousePressed(MouseEvent e){

				System.exit(0);
			}
			public void mouseEntered(MouseEvent e){
				Back.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent e){
				Back.setForeground(Color.black);
			}
		});
	}
}