package atm.gui.virtualslots;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import atm.core.ATM;
import atm.gui.MyGUISettings;
import atm.gui.mainscreen.MainScreenCardJPanel;

public class Card {
	public JButton button;
	public ImageIcon imageIcon;
	public String accountNumber;

	public Card(String imageURL, String accountNumber) throws MalformedURLException {
		imageIcon = new ImageIcon(new ImageIcon(new URL(imageURL)).getImage().getScaledInstance(
				MyGUISettings.CARD_IMAGE_WIDTH, MyGUISettings.CARD_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		button = new JButton(imageIcon);
		this.accountNumber = accountNumber;
		button.addActionListener(getActionListener(this));
	}

	public static ActionListener getActionListener(final Card card) {
		ActionListener insertCard = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ATM.insertCard(card);
				MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_LOGIN);
				CardSlotCardJPanel.switchToCardStatic(CardSlotCardJPanel.STRING_CARD_INSIDE);
			}
		};
		return insertCard;
	}
}
