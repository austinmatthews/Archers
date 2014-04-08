import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
	JSlider p1PowerSlider = new JSlider();
	JSlider p1AngleSlider = new JSlider();
	JSlider p2PowerSlider = new JSlider();
	JSlider p2AngleSlider = new JSlider();
	JButton next = new JButton("Next");
	JLabel p1Power = new JLabel();
	JLabel p2Power = new JLabel();
	JLabel p1Angle = new JLabel();
	JLabel p2Angle = new JLabel();
	

	public ScorchedEarth(){
		playGame();
	}

	public void playGame() {

		//sets the bounds of the game panels
		scorchedGame.setBounds(0, 0, 1000, 1000);
		scorchedInfo.setMaximumSize(new Dimension(1000, 148));
		filler.setMaximumSize(new Dimension(1000, 4));
		scorchedArena.setMaximumSize(new Dimension(1000, 596));
		filler2.setMaximumSize(new Dimension(1000, 4));
		scorchedTurns.setMaximumSize(new Dimension(1000, 248));

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
		//setInfo();
		//setArena();
		setTurns();
		P4Arcade.cardLayout.show(P4Arcade.mainPanel, "scorchedGame");

	}

	public void setTurns() {

		p1PowerSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		p1PowerSlider.setMajorTickSpacing(10);
		p1PowerSlider.setPaintTicks(true);
		p1PowerSlider.setPaintLabels(true);
		p1PowerSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
		p1PowerListener powerListener = new p1PowerListener();
		p1PowerSlider.addChangeListener(powerListener);
		p1PowerSlider.setPreferredSize(new Dimension (400, 40));
		
		p2PowerSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		p2PowerSlider.setMajorTickSpacing(10);
		p2PowerSlider.setPaintTicks(true);
		p2PowerSlider.setPaintLabels(true);
		p2PowerSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		p2PowerListener p2PowerListener = new p2PowerListener();
		p2PowerSlider.addChangeListener(p2PowerListener);
		p2PowerSlider.setPreferredSize(new Dimension (400, 40));
		
		p1AngleSlider = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		p1AngleSlider.setMajorTickSpacing(5);
		p1AngleSlider.setPaintTicks(true);
		p1AngleSlider.setPaintLabels(true);
		p1AngleSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
		p1AngleListener p1AngleListener = new p1AngleListener();
		p1AngleSlider.addChangeListener(p1AngleListener);
		p1AngleSlider.setPreferredSize(new Dimension (400, 40));
		
		p2AngleSlider = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		p2AngleSlider.setMajorTickSpacing(5);
		p2AngleSlider.setPaintTicks(true);
		p2AngleSlider.setPaintLabels(true);
		p2AngleSlider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		p2AngleListener p2AngleListener = new p2AngleListener();
		p2AngleSlider.addChangeListener(p2AngleListener);
		p2AngleSlider.setPreferredSize(new Dimension (400, 40));
		
		p1Power = new JLabel("P1 Power");
		p1Power.setFont(new Font("Andalus", Font.BOLD, 40));
		p1Power.setForeground(Color.white);
		
		p2Power = new JLabel("P2 Power");
		p2Power.setFont(new Font("Andalus", Font.BOLD, 40));
		p2Power.setForeground(Color.white);
		
		p1Angle = new JLabel("P1 Angle");
		p1Angle.setFont(new Font("Andalus", Font.BOLD, 40));
		p1Angle.setForeground(Color.white);
		
		p2Angle = new JLabel("P2 Angle");
		p2Angle.setFont(new Font("Andalus", Font.BOLD, 40));
		p2Angle.setForeground(Color.white);
		
		scorchedTurns.add(p1Power);
		scorchedTurns.add(Box.createRigidArea(new Dimension(290,5)));
		scorchedTurns.add(p2Power);
		scorchedTurns.add(p1PowerSlider);
		scorchedTurns.add(Box.createRigidArea(new Dimension(60,5)));
		scorchedTurns.add(p2PowerSlider);
		scorchedTurns.add(p1Angle);
		scorchedTurns.add(Box.createRigidArea(new Dimension(300,5)));
		scorchedTurns.add(p2Angle);
		scorchedTurns.add(p1AngleSlider);
		scorchedTurns.add(next);
		scorchedTurns.add(p2AngleSlider);
	}

	public void setArena() {


	}

	public void setInfo() {


	}

	public void makeSelection() {


	}

	public class p1PowerListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			int power = p1PowerSlider.getValue();
		}
	}
	
	public class p2PowerListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			int power = p2PowerSlider.getValue();
		}
	}
	
	public class p1AngleListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			int angle = p1AngleSlider.getValue();
		}
	}
	
	public class p2AngleListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			int angle = p2AngleSlider.getValue();
		}
	}
}

