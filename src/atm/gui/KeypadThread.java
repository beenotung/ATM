package atm.gui;

import javax.swing.JFrame;

public class KeypadThread extends DemoJFrameThread {
	private JFrame keypadframe;

	/**
	 * Create the JFrame.
	 */
	public KeypadThread() {
		super();
	}

	@Override
	public JFrame getMainJFrame() {
		return keypadframe;
	}

	@Override
	public void setMainJFrame(JFrame jFrame) {
		keypadframe = jFrame;
	}

}
