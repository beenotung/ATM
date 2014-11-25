package atm.gui.virtualslots;

import java.util.Vector;

import myutils.gui.cardlayout.AbstractCardJPanel;

public class CardSlotCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardSlotCardJPanel> contents = new Vector<CardSlotCardJPanel>();

	public static final String STRING_SELECT_CARD = "Select Card";
	public static final String STRING_CARD_INSIDE = "Card Inside";

	@Override
	protected void myInit() {
		addToCards(new SelectCardJPanel(), STRING_SELECT_CARD);
		addToCards(new CardInsideJPanel(), STRING_CARD_INSIDE);
		switchToCard(STRING_SELECT_CARD);
	}

	public static void switchToCardStatic(String label) {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.switchToCard(label);
		}
	}
}
