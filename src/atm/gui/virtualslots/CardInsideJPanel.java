package atm.gui.virtualslots;

import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import atm.utils.MyStrings;
import atm.utils.MyURLs;

public class CardInsideJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CardInsideJPanel> contents = new Vector<CardInsideJPanel>();

	public CardInsideJPanel() {
		contents.add(this);
	}

	public void insertCard(Card card) {
		removeAll();
		add(card.labelDark);
	}

	public void popCard(Card card) {
		removeAll();
		add(card.buttonTake);
	}

	public static void insertCardStatic(Card card) {
		for (CardInsideJPanel cardInsideJPanel : contents) {
			cardInsideJPanel.insertCard(card);
		}
	}

	public static void popStatic(Card card) {
		for (CardInsideJPanel cardInsideJPanel : contents) {
			cardInsideJPanel.insertCard(card);
		}
	}
}
