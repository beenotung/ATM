package atm.gui.monitor;

import javax.swing.JFrame;

import atm.gui.MyGUISettings;
import atm.gui.monitor.mainscreen.MainMenuJPanel;
import atm.gui.monitor.mainscreen.MainScreenCardJPanel;
import atm.gui.monitor.mainscreen.ViewBalanceJPanel;
import atm.gui.monitor.mainscreen.WithDrawalJPanel;
import atm.gui.monitor.sidebuttons.LeftSideButtonsJPanel;
import atm.gui.monitor.sidebuttons.RightSideButtonsJPanel;
import atm.gui.virtualslots.CardInsideJPanel;
import atm.gui.virtualslots.CardSlotCardJPanel;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

public class MonitorJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		System.out.println("Side button clicked: " + command);
		switch (STATE) {
		case MainScreenCardJPanel.STRING_MAIN_MENU:
			switch (command) {
			case MainScreenCardJPanel.STRING_VIEW_BALANCE:
				ViewBalanceJPanel.showMeStatic();
				break;
			case MainScreenCardJPanel.STRING_TAKE_CARD:
				CardSlotCardJPanel.popCardStatic();
				break;
			case MainScreenCardJPanel.STRING_WITHDRAWAL:
				WithDrawalJPanel.showMeStatic();
				break;
			}
			break;
		case MainScreenCardJPanel.STRING_VIEW_BALANCE:
			switch (command) {
			case ViewBalanceJPanel.STRING_MAIN_MENU:
				MainMenuJPanel.showMe();
				break;
			case ViewBalanceJPanel.STRING_TAKE_CARD:
				CardSlotCardJPanel.popCardStatic();
				break;
			}
			break;
		case MainScreenCardJPanel.STRING_WITHDRAWAL:
			switch (command) {
			case WithDrawalJPanel.STRING_MAIN_MENU:
				MainMenuJPanel.showMe();
				break;
			case WithDrawalJPanel.STRING_TAKE_CARD:
				CardSlotCardJPanel.popCardStatic();
				break;
			default:
				WithDrawalJPanel.sideButtonClickStatic(command);
				break;
			}
		default:
			break;
		}
	}

	public static void returnButtonClick() {
		if (!CardInsideJPanel.hasCard())
			return;
		switch (STATE) {
		case MainScreenCardJPanel.STRING_MAIN_MENU:
			CardSlotCardJPanel.popCardStatic();
			break;
		case MainScreenCardJPanel.STRING_VIEW_BALANCE:
			MainMenuJPanel.showMe();
			break;
		default:
			CardSlotCardJPanel.popCardStatic();
			break;
		}
	}
}
