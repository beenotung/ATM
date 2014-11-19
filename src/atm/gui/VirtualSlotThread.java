package atm.gui;

import javax.swing.JFrame;

public class VirtualSlotThread extends DemoJFrameThread {
	private JFrame virtualSlotFrame;

	/**
	 * Create the JFrame.
	 */
	public VirtualSlotThread() {
		super();
	}

	@Override
	public JFrame getMainJFrame() {
		return virtualSlotFrame;
	}

	@Override
	public void setMainJFrame(JFrame jFrame) {
		virtualSlotFrame = jFrame;
	}
}
