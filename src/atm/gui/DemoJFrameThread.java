package atm.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class DemoJFrameThread extends Thread {

	public abstract JFrame getMainJFrame();

	public abstract void setMainJFrame(JFrame jFrame);

	public DemoJFrameThread() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		setMainJFrame(new JFrame());
		getMainJFrame().setBounds(100, 100, 450, 300);
		getMainJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainJFrame().getContentPane().setLayout(new BorderLayout(0, 0));
	}

	/**
	 * Launch the JFrame.
	 */
	@Override
	public void start() {
		getMainJFrame().setVisible(true);
	}

	/**
	 * Launch the JFrame with desire position on screen.
	 */
	public void start(int x, int y) {
		getMainJFrame().setLocation(x, y);
		start();
	}

	/**
	 * Close the JFrame.
	 */
	public void end() {
		getMainJFrame().setVisible(false);
		getMainJFrame().dispose();
	}

}
