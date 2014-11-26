package atm.gui.virtualslots;

import java.util.Vector;

import atm.core.ATM;
import atm.gui.mainscreen.MainScreenCardJPanel;
import myutils.gui.cardlayout.AbstractCardJPanel;

public class CardSlotCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardSlotCardJPanel> contents = new Vector<CardSlotCardJPanel>();

	public static final String STRING_SELECT_CARD = "Select Card";
	public static final String STRING_CARD_INSIDE = "Card Inside";

	/** constructor **/
	public CardSlotCardJPanel() {
		contents.add(this);
	}

	@Override
	protected void myInit() {
		addToCards(new SelectCardJPanel(), STRING_SELECT_CARD);
		addToCards(new CardInsideJPanel(), STRING_CARD_INSIDE);
		switchToCard(STRING_SELECT_CARD);
	}

	/** static methods **/
	public static boolean hasCard() {
		return CardInsideJPanel.hasCard();
	}

	/** instance methods **/
	public void insertCard(Card card) {
		CardInsideJPanel.insertCardStatic(card);
		switchToCard(STRING_CARD_INSIDE);
		ATM.readCard(card);
	}

	public void popCard() {
		System.out.println("pop");
		CardInsideJPanel.popCardStatic();
		switchToCard(STRING_CARD_INSIDE);
	}

	public void removeCard() {
		CardInsideJPanel.removeCardStatic();
		switchToCard(STRING_SELECT_CARD);
	}

	/** static-instance connector **/
	public static void switchToCardStatic(String label) {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.switchToCard(label);
		}
	}

	public static void insertCardStatic(Card card) {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.insertCard(card);
		}
	}

	public static void popCardStatic() {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.popCard();
		}
	}

	public static void takeCardStatic() {
		CardInsideJPanel.removeCardStatic();
		switchToCardStatic(STRING_SELECT_CARD);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_WELCOME);
	}

}
