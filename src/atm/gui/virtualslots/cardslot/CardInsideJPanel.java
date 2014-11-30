package atm.gui.virtualslots.cardslot;

import java.util.Vector;

import javax.swing.JPanel;

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

	public static void insertCardStatic(Card card) {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.insertCard(card);
	}

	public static void popCardStatic() {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.popCard();
	}

	public static void removeCard() {
		CardInsideJPanel.card = null;
	}

	public static Card getCard() {
		return card;
	}

}
