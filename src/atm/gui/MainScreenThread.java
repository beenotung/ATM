package atm.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import atm.gui.utils.AbstractDemoJFrameThread;

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
	@Override
	protected void initialize() {
		setMainJFrame(new JFrame());
		getMainJFrame().setBounds(100, 100, 450, 300);
		getMainJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainJFrame().getContentPane().setLayout(new BorderLayout(0, 0));	
	}
}
