package atm.gui;

import javax.swing.JFrame;

public class MainScreenThread extends AbstractDemoJFrameThread {
	private JFrame screenFrame;

	/**
	 * Create the JFrame.
	 */
	public MainScreenThread() {
		super();
	}

	@Override
	public JFrame getMainJFrame() {
		return screenFrame;
	}

	@Override
	public void setMainJFrame(JFrame jFrame) {
		screenFrame = jFrame;
	}
}
