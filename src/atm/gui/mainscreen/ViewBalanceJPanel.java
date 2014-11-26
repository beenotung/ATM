package atm.gui.mainscreen;

import javax.swing.JPanel;

import atm.gui.MyGUISettings;
import atm.gui.sidebuttons.SideButtons;

public class ViewBalanceJPanel extends JPanel {
	private static final String[] commands = null;

	public ViewBalanceJPanel() {
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
	}

	public static void showMe() {
		SideButtons.commands=ViewBalanceJPanel.commands;
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_VIEW_BALANCE);
	}

}
