package atm.gui.mainscreen;

import javax.swing.JPanel;

import sun.applet.Main;

public class TakeCardJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TakeCardJPanel() {

	}

	public static void showMe() {
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
	}

}
