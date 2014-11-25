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

	public Vector<Card> cards;

	public CardInsideJPanel() {
		cards = new Vector<Card>();
		try {
			cards.add(new Card(MyURLs.STRING_URL_IMAGE_CARD1, "12356"));
			cards.add(new Card(MyURLs.STRING_URL_IMAGE_CARD2, "12369"));
			cards.add(new Card(MyURLs.STRING_URL_IMAGE_CARD3, "45678"));
			cards.add(new Card(MyURLs.STRING_URL_IMAGE_CARD4, "E3545"));

			for (Card card : cards) {
				add(card.button);
			}
		} catch (MalformedURLException e) {
			System.out.println(MyStrings.INTERNET_ERROR);
			JOptionPane.showMessageDialog(getParent(), MyStrings.INTERNET_ERROR, "Internet Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
