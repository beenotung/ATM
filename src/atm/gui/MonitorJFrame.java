package atm.gui;

import javax.swing.JFrame;

import atm.gui.mainscreen.MainScreenCardJPanel;
import atm.gui.sidebuttons.LeftSideButtonsJPanel;
import atm.gui.sidebuttons.RightSideButtonsJPanel;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

public class MonitorJFrame extends JFrame {
	private LeftSideButtonsJPanel leftSideButtonsJPanel;
	private RightSideButtonsJPanel rightSideButtonsJPanel;
	private MainScreenCardJPanel mainScreenJPanel;

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

		mainScreenJPanel = new MainScreenCardJPanel();
		getContentPane().add(mainScreenJPanel, BorderLayout.CENTER);
		mainScreenJPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
	}

	public void calcBounds(int w, int h, int s) {
		setVisible(true);		
		setBounds(10,10, w + s * 2, h);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
