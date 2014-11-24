package atm.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MonitorJFrame extends JFrame {
	private MainScreenJPanel mainScreenJPanel;
	private SideButtonsJPanel sideButtonsJFrame;

	public MonitorJFrame() {
		setTitle("ATM Monitor");
		setVisible(false);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		mainScreenJPanel = new MainScreenJPanel();
		getContentPane().add(mainScreenJPanel.mainScreenJInternalFrame, BorderLayout.CENTER);

		sideButtonsJFrame = new SideButtonsJPanel();
		getContentPane().add(sideButtonsJFrame.leftSideButtonsJPanel, BorderLayout.WEST);
		getContentPane().add(sideButtonsJFrame.rightSideButtonsJPanel, BorderLayout.EAST);
	}

	public void calcBounds(int x, int y, int w, int h, int s) {
		setVisible(true);
		setBounds(x, y, w + s * 2, h);
	}

}
