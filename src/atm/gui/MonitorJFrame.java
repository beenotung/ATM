package atm.gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import atm.gui.mainscreen.MainScreenCardJPanel;
import atm.gui.sidebuttons.LeftSideButtonsJPanel;
import atm.gui.sidebuttons.RightSideButtonsJPanel;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

public class MonitorJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 278412650556752290L;
	private LeftSideButtonsJPanel leftSideButtonsJPanel;
	private RightSideButtonsJPanel rightSideButtonsJPanel;
	private MainScreenCardJPanel mainScreenJPanel;
	private JInternalFrame internalFrame;

	public MonitorJFrame() throws MalformedURLException {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("ATM Monitor");
		setVisible(false);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		leftSideButtonsJPanel = new LeftSideButtonsJPanel();
		getContentPane().add(leftSideButtonsJPanel, BorderLayout.WEST);

		rightSideButtonsJPanel = new RightSideButtonsJPanel();
		getContentPane().add(rightSideButtonsJPanel, BorderLayout.EAST);

		JInternalFrame internalFrame = new JInternalFrame("ATM Main Screen");
		getContentPane().add(internalFrame, BorderLayout.CENTER);
		mainScreenJPanel = new MainScreenCardJPanel();
		internalFrame.getContentPane().add(mainScreenJPanel,
				BorderLayout.CENTER);

		internalFrame.setVisible(true);
	}

	public void calcBounds(int x, int y, int w, int h, int s) {
		setVisible(true);
		setBounds(x, y, w + s * 2, h);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
