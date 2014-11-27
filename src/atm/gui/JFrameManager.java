package atm.gui;

import java.net.MalformedURLException;

import atm.gui.keypad.KeypadJFrame;
import atm.gui.monitor.MonitorJFrame;
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
		// set size and location on screen
		System.out.println("showing keypad");
		keypadJFrame.calcBounds();
		System.out.println("showing virtual slots");
		virtualSlotsJFrame.calcBounds();
		System.out.println("showing monitor");
		monitorJFrame.calcBounds(600, 400, 75);
	}

	protected void start() {
	}

	public void end() {
		keypadJFrame.dispose();
		monitorJFrame.dispose();
		virtualSlotsJFrame.dispose();
		System.exit(0);
	}

}
