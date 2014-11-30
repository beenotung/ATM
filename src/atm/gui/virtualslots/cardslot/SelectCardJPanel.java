package atm.gui.virtualslots.cardslot;

import javax.swing.JPanel;

import webs.layout.CircleLayout;

public class SelectCardJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectCardJPanel() {
		setLayout(new CircleLayout());
		for (Card card : Card.getCards())
			add(card.buttonInsert);
		setPreferredSize(getMinimumSize());
	}

	public void myUpdateUI() {
		setPreferredSize(getMinimumSize());
		updateUI();
	}
}
