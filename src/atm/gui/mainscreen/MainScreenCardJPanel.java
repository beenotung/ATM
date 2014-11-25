package atm.gui.mainscreen;

import myutils.gui.cardlayout.AbstractCardJPanel;

public class MainScreenCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STRING_WELCOME = "Welcome";
	public static final String STRING_LOGIN = "Login";

	public MainScreenCardJPanel() {
		super();
	}

	@Override
	protected void myInit() {
		addToCards(new Welcome(cardLayout), STRING_WELCOME);
		addToCards(new Login(cardLayout), STRING_LOGIN);
		switchToCard(STRING_WELCOME);
	}
}
