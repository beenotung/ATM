package atm.gui.virtualslots;

import java.util.Vector;

import atm.core.ATM;
import atm.gui.monitor.mainscreen.MainScreenCardJPanel;
import atm.gui.monitor.mainscreen.TakeCardJPanel;
import myutils.gui.cardlayout.AbstractCardJPanel;

public class CardSlotCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardSlotCardJPanel> contents = new Vector<CardSlotCardJPanel>();

	public static final String STRING_SELECT_CARD = "Select Card";
	public static final String STRING_CARD_INSIDE = "Card Inside";

	public static String STATE = "";

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
		switchToCard(STRING_CARD_INSIDE);
		CardInsideJPanel.popCardStatic();
	}

	@Override
	public void switchToCard(String label) {
		STATE = label;
		super.switchToCard(label);
	}

	public void waitPopCard() {
		(new WaitPopCard()).start();
	}

	/** private class **/
	private static class WaitPopCard extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			CardSlotCardJPanel.popCardStatic();
		}
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
		if (!CardInsideJPanel.hasCard())
			return;
		TakeCardJPanel.showMe();
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.popCard();
		}
	}

	public static void takeCardStatic() {
		System.out.println("card taken by user");
		CardInsideJPanel.removeCard();
		switchToCardStatic(STRING_SELECT_CARD);
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_WELCOME);
	}

	public static void waitPopCardStatic() {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.waitPopCard();
		}
	}
}
