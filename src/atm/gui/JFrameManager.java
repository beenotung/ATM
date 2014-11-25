package atm.gui;

import java.net.MalformedURLException;

import javax.swing.JFrame;

import atm.gui.virtualslots.VirtualSlotsCardJFrame;

public class JFrameManager {

	private KeypadJFrame keypadJFrame;
	private MonitorJFrame monitorJFrame;
	private VirtualSlotsCardJFrame virtualSlotsJFrame;

	/**
	 * Create the application.
	 * @throws MalformedURLException 
	 */
	public JFrameManager() throws MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException {
		keypadJFrame = new KeypadJFrame();
		monitorJFrame = new MonitorJFrame();
		virtualSlotsJFrame = new VirtualSlotsCardJFrame();

		keypadJFrame.calcBounds();
		monitorJFrame.calcBounds(50, 50, 600, 400, 75);
		virtualSlotsJFrame.calcBounds();
	}

	protected void start() {
		showAll();
	}

	private void showAll() {
		keypadJFrame.setVisible(true);
		monitorJFrame.setVisible(true);
		virtualSlotsJFrame.setVisible(true);
	}

	public void end() {
		keypadJFrame.dispose();
		monitorJFrame.dispose();
		virtualSlotsJFrame.dispose();
		System.exit(0);
	}

}
