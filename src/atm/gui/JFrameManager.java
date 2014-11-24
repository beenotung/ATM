package atm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class JFrameManager {

	private KeypadJFrame keypadJFrame;
	private MainScreenJFrame mainScreenJFrame;
	private JFrame sideButtonsJFrame;
	private JFrame virtualSlotsJFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameManager window = new JFrameManager();
					window.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void start() {
		showAll();
	}

	private void showAll() {
		keypadJFrame.setVisible(true);
		mainScreenJFrame.setVisible(true);
		sideButtonsJFrame.setVisible(true);
		virtualSlotsJFrame.setVisible(true);
	}

	public void end() {
		keypadJFrame.dispose();
		mainScreenJFrame.dispose();
		sideButtonsJFrame.dispose();
		virtualSlotsJFrame.dispose();
		System.exit(0);
	}

	/**
	 * Create the application.
	 */
	public JFrameManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		keypadJFrame = new KeypadJFrame();
		mainScreenJFrame = new MainScreenJFrame();
		sideButtonsJFrame = new JFrame();
		virtualSlotsJFrame = new JFrame();

		mainScreenJFrame.setAlwaysOnTop(true);
		keypadJFrame.calcBounds();
		mainScreenJFrame.calcBounds(600,400);
	}
}
