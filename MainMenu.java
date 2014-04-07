
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
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		mainMenu.setBounds(0, 0, 1000, 1000);
		mainMenu.setBackground(Color.black);
		frame.getContentPane().add(mainMenu);
		ScorchedEarth scorched = new ScorchedEarth();
	}
}