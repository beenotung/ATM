package atm.gui.virtualslots;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CashDispenser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;

	public CashDispenser(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}
