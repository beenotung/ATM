package atm.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import atm.gui.utils.AbstractDemoJFrameThread;

public class VirtualSlotThread extends AbstractDemoJFrameThread {
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
	}	@Override
	protected void initialize() {
		setMainJFrame(new JFrame());
		getMainJFrame().setBounds(100, 100, 450, 300);
		getMainJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainJFrame().getContentPane().setLayout(new BorderLayout(0, 0));	
	}
}
