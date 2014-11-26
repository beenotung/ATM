package atm.gui;

import java.net.MalformedURLException;

import atm.gui.virtualslots.VirtualSlotsJFrame;

public class JFrameManager {

	private KeypadJFrame keypadJFrame;
	private MonitorJFrame monitorJFrame;
	private VirtualSlotsJFrame virtualSlotsJFrame;

	/**
	 * Create the application.
	 * 
	 * @throws MalformedURLException
	 */
	public JFrameManager() throws MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws MalformedURLException
	 */
	private void initialize() throws MalformedURLException {
		System.out.println("initializing keypad");
		keypadJFrame = new KeypadJFrame();
		System.out.println("initializing monitor");
		monitorJFrame = new MonitorJFrame();
		System.out.println("initializing virtual slots");
		virtualSlotsJFrame = new VirtualSlotsJFrame();

		keypadJFrame.calcBounds();
		monitorJFrame.calcBounds(50, 50, 600, 400, 75);
		virtualSlotsJFrame.calcBounds();
	}

	protected void start() {
		showAll();
	}

	private void showAll() {
		System.out.println("showing keypad");
		keypadJFrame.setVisible(true);
		System.out.println("showing monitor");
		monitorJFrame.setVisible(true);
		System.out.println("showing virtual slots");
		virtualSlotsJFrame.setVisible(true);
	}

	public void end() {
		keypadJFrame.dispose();
		monitorJFrame.dispose();
		virtualSlotsJFrame.dispose();
		System.exit(0);
	}

}
