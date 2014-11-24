package atm.gui.mainscreen;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class Welcome extends JPanel {
	private CardLayout cardLayout;

	public Welcome(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}