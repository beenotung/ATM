package atm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainFrame implements Runnable {
	private Thread thread;
	private JFrame frame;

	
	

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * Launch the application.
	 */
	public void start() {
		if (thread == null)
			thread = new Thread(this);
		
	}
}
