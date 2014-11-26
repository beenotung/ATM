package atm.gui.virtualslots;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.gui.mainscreen.MainScreenCardJPanel;

public class Card {
	public ImageIcon imageIconBright;
	public ImageIcon imageIconDark;
	public JButton buttonInsert;
	public JButton buttonTake;
	public JLabel labelDark;
	public String accountNumber;

	public Card(String imageURLBright, String imageURLDark, String accountNumber)
			throws MalformedURLException {
		imageIconBright = new ImageIcon(new ImageIcon(new URL(imageURLBright)).getImage().getScaledInstance(
				MyGUISettings.CARD_IMAGE_WIDTH, MyGUISettings.CARD_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		imageIconDark = new ImageIcon(new ImageIcon(new URL(imageURLDark)).getImage().getScaledInstance(
				MyGUISettings.CARD_IMAGE_WIDTH, MyGUISettings.CARD_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		buttonInsert = new JButton(imageIconBright);
		buttonInsert.addActionListener(getInsertActionListener(this));
		buttonTake = new JButton(imageIconBright);
		buttonTake.addActionListener(getTakeActionListener(this));
		labelDark = new JLabel(imageIconDark);
		this.accountNumber = accountNumber;
	}

	public static ActionListener getInsertActionListener(final Card card) {
		ActionListener insertCard = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("insertig");
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
}
