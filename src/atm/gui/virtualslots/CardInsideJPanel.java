package atm.gui.virtualslots;

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
		System.out.println("pop2");
		card.labelDark.setVisible(false);
		card.buttonTake.setVisible(true);
	}

	public void removeCard() {
		card = null;
	}

	public static void insertCardStatic(Card card) {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.insertCard(card);
	}

	public static void popCardStatic() {
		for (CardInsideJPanel cardInsideJPanel : contents)
			cardInsideJPanel.popCard();
	}

	public static void removeCardStatic() {
		for (CardInsideJPanel cardInsideJPanel : contents)
			card = null;
	}

}
