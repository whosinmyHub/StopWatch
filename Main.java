
/**
 * Purpose: Create a stop watch application that can start, pause, and reset
 * The stop watch counts centi-seconds, seconds, minutes, and hours
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.lang.StringBuilder;

/** TODO: add toggle button to switch to Timer mode **/

public class Main {
	private static JFrame screen;
	private static JButton start;
	private static JButton pause;
	private static JButton reset;
	private static int elapsedTime = 00;
	private static StringBuilder timeDisplay;
	private static JLabel time;
	private static Timer timer;
	
	public static void main(String[] args) {
		makeGUI();
		
	}

	private static void makeGUI() {
		// construct the screen/frame
		makeFrame ();
		GridBagConstraints gbc = new GridBagConstraints();

		//create the time label & add its layout values
		screen.add(makeLabel (gbc), gbc);

		// construct the buttons
		makeButtons (gbc);
		
		//construct Timer object
		timer = new Timer (10, e -> startAction ());

		// set visible
		screen.setVisible(true);
	}

/**
 * Action Listener Methods
 */
	private static void startAction() {
		elapsedTime += 10; 
		
		//logic inspired by https://www.youtube.com/watch?v=0cATENiMsBE
		timeDisplay.replace (9, 11, String.format("%02d", (elapsedTime / 10) % 100));
		timeDisplay.replace (6, 8, String.format("%02d", (elapsedTime / 1000) % 60));
		timeDisplay.replace (3, 5, String.format("%02d", (elapsedTime / 60000) % 60));
		timeDisplay.replace (0, 2, String.format("%02d", (elapsedTime / 3600000) % 60));
		
		time.setText (timeDisplay.toString ());
		
	}

	private static void pauseAction() {
		timer.stop ();
		System.out.print ("stopping");


	}

	private static void resetAction() {
		elapsedTime = 0;
		time.setText ("00:00:00.00");

	}

	/**
	 * Construction/Design Methods
	 */
	private static JLabel makeLabel (GridBagConstraints gbc) {
		timeDisplay = new StringBuilder ("00:00:00.00");
		time = new JLabel(timeDisplay.toString (), SwingConstants.CENTER);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.ipadx = 250; 
		gbc.ipady = 200;
		gbc.insets = new Insets(0, 0, 50, 0);
		time.setOpaque(false);
		time.setFont(new Font("SansSerif", Font.BOLD, 50));
		time.setForeground(Color.decode("#678098"));
		
		return time;
	}

	private static void makeFrame () {
		screen = new JFrame("Stop-watch");
		screen.setResizable(false);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(500, 520);
		screen.setLocationRelativeTo(null);
		screen.getContentPane().setBackground(Color.decode("#F0B6E1"));

		screen.setLayout(new GridBagLayout());
	}
	
	private static void makeButtons (GridBagConstraints gbc) {
		start = new JButton("Start");
		start.setFont(new Font("SansSerif", Font.BOLD, 20));
		start.setForeground(Color.decode("#273A64"));
		
		pause = new JButton("Pause");
		pause.setFont(new Font("SansSerif", Font.BOLD, 20));
		pause.setForeground(Color.decode("#273A64"));
		
		reset = new JButton("Reset");
		reset.setFont(new Font("SansSerif", Font.BOLD, 20));
		reset.setForeground(Color.decode("#273A64"));
		
		gbc = new GridBagConstraints();
		gbc.ipadx = 20;
		gbc.ipady = 40;

		gbc.weightx = 0.25;
		gbc.gridx = 3;
		screen.add(start, gbc);

		gbc.gridx = 4;
		screen.add(pause, gbc);

		gbc.gridx = 5;
		screen.add(reset, gbc);
		
		// add the relevant action listeners to each button
		start.addActionListener(e -> timer.start ());
		pause.addActionListener(e -> pauseAction());
		reset.addActionListener(e -> resetAction());
	}
}
