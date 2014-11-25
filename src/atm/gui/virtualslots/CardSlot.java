package atm.gui.virtualslots;

import java.awt.CardLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import atm.gui.MyGUISettings;
import atm.utils.MyStrings;
import atm.utils.MyURLs;

public class CardSlot extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;

	public Vector<JButton> buttons;

	public CardSlot(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
		 buttons=new Vector<JButton>();
		try {
			JButton btnNewButton_1 = new JButton(new ImageIcon(
					new ImageIcon(new URL(MyURLs.STRING_URL_IMAGE_CARD1))
							.getImage().getScaledInstance(
									MyGUISettings.CARD_IMAGE_WIDTH,
									MyGUISettings.CARD_IMAGE_HEIGHT,
									Image.SCALE_SMOOTH)));
			buttons.add(btnNewButton_1);
			add(btnNewButton_1);

			JButton btnNewButton_2 = new JButton(new ImageIcon(
					new ImageIcon(new URL(MyURLs.STRING_URL_IMAGE_CARD2))
							.getImage().getScaledInstance(
									MyGUISettings.CARD_IMAGE_WIDTH,
									MyGUISettings.CARD_IMAGE_HEIGHT,
									Image.SCALE_SMOOTH)));
			buttons.add(btnNewButton_2);
			add(btnNewButton_2);

			JButton btnNewButton_3 = new JButton(new ImageIcon(
					new ImageIcon(new URL(MyURLs.STRING_URL_IMAGE_CARD3))
							.getImage().getScaledInstance(
									MyGUISettings.CARD_IMAGE_WIDTH,
									MyGUISettings.CARD_IMAGE_HEIGHT,
									Image.SCALE_SMOOTH)));
			buttons.add(btnNewButton_3);
			add(btnNewButton_3);

			JButton btnNewButton_4 = new JButton(new ImageIcon(
					new ImageIcon(new URL(MyURLs.STRING_URL_IMAGE_CARD4))
							.getImage().getScaledInstance(
									MyGUISettings.CARD_IMAGE_WIDTH,
									MyGUISettings.CARD_IMAGE_HEIGHT,
									Image.SCALE_SMOOTH)));
			buttons.add(btnNewButton_4);
			add(btnNewButton_4);
		} catch (MalformedURLException e) {
			System.out.println(MyStrings.INTERNET_ERROR);
			JOptionPane.showMessageDialog(getParent(),
					MyStrings.INTERNET_ERROR, "Internet Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
