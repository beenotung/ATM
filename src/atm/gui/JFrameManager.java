package atm.gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class JFrameManager {

	private KeypadJFrame keypadJFrame;
	private MonitorJFrame monitorJFrame;
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
		keypadJPanel.setVisible(true);
		monitorJFrame.setVisible(true);
		virtualSlotsJFrame.setVisible(true);
	}

	public void end() {
		keypadJFrame.dispose();
		monitorJFrame.dispose();
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
		keypadJPanel = new KeypadJPanel();
		monitorJFrame = new MonitorJFrame();
		virtualSlotsJFrame = new JFrame();

		keypadJPanel.calcBounds();
		monitorJFrame.calcBounds(50, 50, 600, 400, 75);

	}

}
