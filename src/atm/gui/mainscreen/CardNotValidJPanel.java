package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.utils.MyStrings;

public class CardNotValidJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardNotValidJPanel() {
		JLabel label = new JLabel(MyStrings.CARD_NOT_VALID);
		add(label);
	}

}
