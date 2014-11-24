package atm.gui;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class MonitorJFrame extends JFrame {

	private LeftSideButtonsJPanel leftSideButtonsJPanel;
	private RightSideButtonsJPanel rightSideButtonsJPanel;
	private MainScreenJPanel mainScreenJPanel;

	public MonitorJFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("ATM Monitor");
		setVisible(false);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		leftSideButtonsJPanel = new LeftSideButtonsJPanel();
		getContentPane().add(leftSideButtonsJPanel, BorderLayout.WEST);

		rightSideButtonsJPanel = new RightSideButtonsJPanel();
		getContentPane().add(rightSideButtonsJPanel, BorderLayout.EAST);
		mainScreenJPanel = new MainScreenJPanel();
		getContentPane().add(mainScreenJPanel, BorderLayout.CENTER);

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
