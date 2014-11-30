package atm.gui.virtualslots.cardslot;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import atm.utils.MyURLs;

public class Card {
	private static Vector<Card> cards = new Vector<Card>();
	public ImageIcon imageIconBright;
	public ImageIcon imageIconDark;
	public JButton buttonInsert;
	public JButton buttonTake;
	public JLabel labelDark;
	public String accountNumber;

	/** static instances **/
	public static void init() throws MalformedURLException {
		cards.add(new Card(MyURLs.IMAGE_CARD1, MyURLs.IMAGE_CARD1_DARK, "12356"));
		cards.add(new Card(MyURLs.IMAGE_CARD2, MyURLs.IMAGE_CARD2_DARK, "12369"));
		cards.add(new Card(MyURLs.IMAGE_CARD3, MyURLs.IMAGE_CARD3_DARK, "45678"));
		cards.add(new Card(MyURLs.IMAGE_CARD4, MyURLs.IMAGE_CARD4_DARK, "E3545"));
	}

	public static Vector<Card> getCards() {
		return cards;
	}

	public static ActionListener getInsertActionListener(final Card card) {
		ActionListener insertCard = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardSlotCardJPanel.insertCardStatic(card);
			}
		};
		return insertCard;
	}

	public static ActionListener getTakeActionListener(final Card card) {
		ActionListener insertCard = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardSlotCardJPanel.takeCardStatic();				
			}
		};
		return insertCard;
	}

	/** constructor **/
	public Card(String imageURLBright, String imageURLDark, String accountNumber)
			throws MalformedURLException {
		imageIconBright = new ImageIcon(new ImageIcon(new URL(imageURLBright)).getImage().getScaledInstance(
				MyGUISettings.CARD_IMAGE_WIDTH, MyGUISettings.CARD_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		imageIconDark = new ImageIcon(new ImageIcon(new URL(imageURLDark)).getImage().getScaledInstance(
				MyGUISettings.CARD_IMAGE_WIDTH, MyGUISettings.CARD_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		buttonInsert = new JButton(imageIconBright);
		buttonInsert.addActionListener(getInsertActionListener(this));
		buttonTake = new JButton(new ImageIcon(imageIconBright.getImage()));
		buttonTake.addActionListener(getTakeActionListener(this));
		labelDark = new JLabel(imageIconDark);
		this.accountNumber = accountNumber;
	}
}
