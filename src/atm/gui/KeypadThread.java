package atm.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import atm.gui.utils.AbstractDemoJFrameThread;

public class KeypadThread extends AbstractDemoJFrameThread {
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

	@Override
	protected void initialize() {
		setMainJFrame(new JFrame());
		// getMainJFrame().setBounds(100, 100, 450, 300);
		// getMainJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainJFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getMainJFrame().getContentPane().setLayout(new BorderLayout(0, 0));
	}

	public void goCorner() {
		getMainJFrame().pack();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getBounds();
		Rectangle client = getMainJFrame().getBounds();
		int yPos = screen.height - client.height;
		int xPos = screen.width - client.width;
		getMainJFrame().setBounds(xPos, yPos, client.width, client.height);
	}
}
