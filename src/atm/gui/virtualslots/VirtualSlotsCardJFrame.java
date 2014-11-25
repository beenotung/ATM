package atm.gui.virtualslots;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import myutils.gui.cardlayout.AbstractCardJFrame;

public class VirtualSlotsCardJFrame extends AbstractCardJFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STRING_CARD_SLOT = "Card Slot";
	public static final String STRING_CASH_DISPENSER = "Cash Dispenser";

	public VirtualSlotsCardJFrame() {
		super("Virtual Slots");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(50,40);
	}

	@Override
	protected void myInit() {
		addToCards(new CardSlot(cardLayout), STRING_CARD_SLOT);
		addToCards(new CashDispenser(cardLayout), STRING_CASH_DISPENSER);
		switchToCard(STRING_CARD_SLOT);
	}

	
	public void calcBounds() {
		pack();
		setVisible(true);
		Rectangle client = getBounds();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getBounds();
		int x = screen.width - client.width;
		int y = 0;
		setLocation(x, y);
	}
}
