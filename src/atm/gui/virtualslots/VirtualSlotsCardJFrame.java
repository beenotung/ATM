package atm.gui.virtualslots;

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
	}

	@Override
	protected void myInit() {
		addToCards(new CardSlot(this), STRING_CARD_SLOT);
		addToCards(new CashDispenser(this), STRING_CASH_DISPENSER);
		switchToCard(STRING_CARD_SLOT);
	}
}
