package atm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class MainScreenThread extends Thread {	
	private JFrame mainFrame;

	/**
	 * Create the application.
	 */
	public MainScreenThread() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Launch the application.
	 */
	@Override
	public void start() {		
		mainFrame.setVisible(true);
	}
}
