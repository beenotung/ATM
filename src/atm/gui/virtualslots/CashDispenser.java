package atm.gui.virtualslots;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CashDispenser extends JPanel {
	private CardLayout cardLayout;

	public CashDispenser(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}
