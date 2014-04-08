import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

//contains the main class
public class P4Arcade extends JFrame {

	public static P4Arcade frame = new P4Arcade();
	public static JPanel mainPanel = new JPanel();
	public static CardLayout cardLayout = new CardLayout();
	public static BorderLayout borderLayout = new BorderLayout();

	public static void main(String[] args) {

		//creates the frame and adds the main panel to the frame
		mainPanel.setLayout(cardLayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("M&M Arcade");
		frame.setSize(1000, 850);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		mainPanel.setBounds(0, 0, 1000, 800);
		mainPanel.setBackground(Color.black);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
		MainMenu mainMenu = new MainMenu();


	}
}
