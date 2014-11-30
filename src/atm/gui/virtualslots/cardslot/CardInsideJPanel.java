package atm.gui.virtualslots.cardslot;

import java.util.Vector;

import javax.swing.JPanel;

import atm.gui.virtualslots.VirtualSlotsJFrame;

public class CardInsideJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardInsideJPanel> contents = new Vector<CardInsideJPanel>();
	private static Card card = null;

	/** constructor **/
	public CardInsideJPanel() {
		contents.add(this);
		setPreferredSize(getMinimumSize());
	}

	/** static methods **/
	public static boolean hasCard() {
		return (card != null);
	}

	public static void removeCard() {
		CardInsideJPanel.card = null;
		VirtualSlotsJFrame.hideCardSlotStatic();
	}

	public static Card getCard() {
		return card;
	}

	/** instance methods **/
	public void insertCard(Card card) {
		CardInsideJPanel.card = card;
		removeAll();
		add(card.labelDark);
		card.labelDark.setVisible(true);
		add(card.buttonTake);
		card.buttonTake.setVisible(false);
	}

	public void popCard() {
		card.labelDark.setVisible(false);
		card.buttonTake.setVisible(true);
	}

	public void myUpdateUI() {
		setPreferredSize(getMinimumSize());
		updateUI();
	}

	/** static connector to instance stuff **/
	public static void insertCardStatic(Card card) {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.insertCard(card);
	}

	public static void popCardStatic() {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.popCard();
	}

}
