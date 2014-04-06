
		import java.awt.*;

		import java.awt.event.ActionEvent;

		import java.awt.event.ActionListener;

		import javax.swing.*;



		public class MainMenu {

			public static MainMenu frame = new MainMenu();

			public static JPanel mainMenu = new JPanel();

			public static CardLayout cardLayout = new CardLayout();


/*
			public MainMenu() {

				setTitle("M&M Arcade");

				setSize(1000, 1000);

				mainMenu.setLayout(cardLayout);

				gamePanel = new JPanel();

				jp2 = new JPanel();

				jl1 = new JLabel("Card 1");

				jl2 = new JLabel("Card 2");

				jp1.add(jl1);

				jp2.add(jl2);

				mainMenu.add(jp1, "1");

				mainMenu.add(jp2, "2");

				btn1 = new JButton("Show Card 1");

				btn1.addActionListener(new ActionListener() {



					public void actionPerformed(ActionEvent e) {

						cardLayout.show(mainMenu, "1");

					}

				});

				btn2 = new JButton("Show Card 2");

				btn2.addActionListener(new ActionListener() {



					public void actionPerformed(ActionEvent e) {

						cardLayout.show(mainMenu, "2");

					}

				});

				buttonPanel.add(btn1);

				buttonPanel.add(btn2);

				add(mainMenu, BorderLayout.NORTH);

				add(buttonPanel, BorderLayout.SOUTH);

			}
*/


			public static void main(String[] args) {

						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						frame.setVisible(true);

					}

				});

			}

	}


