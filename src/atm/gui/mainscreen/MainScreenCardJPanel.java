package atm.gui.mainscreen;

import java.util.Vector;

import myutils.gui.cardlayout.AbstractCardJPanel;

import java.awt.Color;
import java.awt.Component;

import atm.gui.MyGUISettings;

public class MainScreenCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<MainScreenCardJPanel> contents = new Vector<MainScreenCardJPanel>();

	public static final String STRING_WELCOME = "Welcome";
	public static final String STRING_READCARD = "Read Card";
	public static final String STRING_CARD_NOT_VALID = "Card Not Valid";
	public static final String STRING_LOGIN = "Login";

	public MainScreenCardJPanel() {
		super();
		contents.add(this);
	}

	@Override
	protected void myInit() {
		addToCards(new WelcomeJPanel(), STRING_WELCOME);
		addToCards(new ReadCardJPanel(), STRING_READCARD);
		addToCards(new CardNotValidJPanel(), STRING_CARD_NOT_VALID);
		addToCards(new LoginJPanel(), STRING_LOGIN);
		switchToCard(STRING_WELCOME);
	}

	public static void switchToCardStatic(String label) {
		for (MainScreenCardJPanel content : contents)
			content.switchToCard(label);
	}

	@Override
	public void addToCards(Component component, String label) {
		super.addToCards(component, label);
		component.setBackground(MyGUISettings.getATMScreenBackGroundColor());
	}
}
