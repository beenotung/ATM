package atm.gui.virtualslots;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardSlot extends JPanel {
	private CardLayout cardLayout;

	public CardSlot(CardLayout cardLayout) {
		this.cardLayout=cardLayout;
	}
}
