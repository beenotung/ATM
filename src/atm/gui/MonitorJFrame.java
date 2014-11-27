package atm.gui;

import javax.swing.JFrame;

import atm.gui.mainscreen.MainMenuJPanel;
import atm.gui.mainscreen.MainScreenCardJPanel;
import atm.gui.mainscreen.ViewBalanceJPanel;
import atm.gui.sidebuttons.LeftSideButtonsJPanel;
import atm.gui.sidebuttons.RightSideButtonsJPanel;
import atm.gui.virtualslots.CardSlotCardJPanel;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

public class MonitorJFrame extends JFrame {
	private LeftSideButtonsJPanel leftSideButtonsJPanel;
	private RightSideButtonsJPanel rightSideButtonsJPanel;
	private MainScreenCardJPanel mainScreenJPanel;

	public static String STATE = "";

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
		setBounds(10, 10, w + s * 2, h);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public static void sideButtonClick(String command) {
		switch (STATE) {
		case MainScreenCardJPanel.STRING_MAIN_MENU:
			switch (command) {
			case MainMenuJPanel.STRING_VIEW_BALANCE:
				ViewBalanceJPanel.showMe();
				break;
			case MainMenuJPanel.STRING_TAKE_CARD:
				CardSlotCardJPanel.popCardStatic();
				break;
			}
			break;
		case MainScreenCardJPanel.STRING_VIEW_BALANCE: {
			switch (command) {
			case ViewBalanceJPanel.STRING_TAKE_CARD:
				CardSlotCardJPanel.popCardStatic();
				break;
			}
			break;
		}
		default:
			break;
		}
	}
}
