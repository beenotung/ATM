package atm.gui.mainscreen;

import java.util.Vector;

import myutils.gui.cardlayout.AbstractCardJPanel;

public class MainScreenCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<MainScreenCardJPanel> contents = new Vector<MainScreenCardJPanel>();

	public static final String STRING_WELCOME = "Welcome";
	public static final String STRING_LOGIN = "Login";

	public MainScreenCardJPanel() {
		super();
		contents.add(this);
	}

	@Override
	protected void myInit() {
		addToCards(new WelcomeJPanel(), STRING_WELCOME);
		addToCards(new LoginJPanel(), STRING_LOGIN);
		switchToCard(STRING_WELCOME);
	}

	public static void switchToCardStatic(String label) {
		for (MainScreenCardJPanel content : contents)
			content.switchToCard(label);
	}
}
