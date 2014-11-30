package atm.gui.virtualslots.cardslot;

import java.util.Vector;

import atm.core.ATM;
import atm.gui.monitor.mainscreen.MainScreenCardJPanel;
import atm.gui.monitor.mainscreen.TakeCardJPanel;
import atm.gui.monitor.mainscreen.TakeCashJPanel;
import atm.gui.virtualslots.VirtualSlotsJFrame;
import atm.gui.virtualslots.cashdispenser.CashDispenserJPanel;
import myutils.gui.cardlayout.AbstractCardJPanel;

public class CardSlotCardJPanel extends AbstractCardJPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardSlotCardJPanel> contents = new Vector<CardSlotCardJPanel>();

	public static final String STRING_SELECT_CARD = "Select Card";
	public static final String STRING_CARD_INSIDE = "Card Inside";
	public static final String STRING_EMPTY = "Empty";

	public static String STATE = "";

	private SelectCardJPanel selectCardJPanel;
	private CardInsideJPanel cardInsideJPanel;

	/** constructor **/
	public CardSlotCardJPanel() {
		contents.add(this);
	}

	@Override
	protected void myInit() {
		selectCardJPanel = new SelectCardJPanel();
		cardInsideJPanel = new CardInsideJPanel();
		addToCards(selectCardJPanel, STRING_SELECT_CARD);
		addToCards(cardInsideJPanel, STRING_CARD_INSIDE);
		addToCards(new EmptyJPanel(), STRING_EMPTY);
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
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
		CardInsideJPanel.popCardStatic();
	}

	@Override
	public void switchToCard(String label) {
		STATE = label;
		super.switchToCard(label);
		System.out.println(STATE);
		updateUI();
		myUpdateUI();
	}

	public void waitPopCard() {
		(new WaitPopCard()).start();
	}

	private void takeCard() {
		System.out.println("card taken by user");
		CardInsideJPanel.removeCard();
		if (CashDispenserJPanel.hasCashToBePopped) {
			System.out.println("going to pop cash");
			switchToCardStatic(STRING_EMPTY);
			TakeCashJPanel.showMe();
		} else {
			VirtualSlotsJFrame.myResetStatic();
		}
	}

	public void myUpdateUI() {
		if (selectCardJPanel != null)
			selectCardJPanel.myUpdateUI();
		if (cardInsideJPanel != null)
			cardInsideJPanel.myUpdateUI();
		setPreferredSize(getMinimumSize());
		updateUI();
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
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.takeCard();
		}
	}

	public static void waitPopCardStatic() {
		for (CardSlotCardJPanel cardSlotCardJPanel : contents) {
			cardSlotCardJPanel.waitPopCard();
		}
	}
}
