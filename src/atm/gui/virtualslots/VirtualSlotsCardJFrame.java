package atm.gui.virtualslots;

import myutils.gui.cardlayout.AbstractCardJFrame;

public class VirtualSlotsCardJFrame extends AbstractCardJFrame {
	public static final String STRING_CARD_SLOT = "Card Slot";
	public static final String STRING_CASH_DISPENSER = "Cash Dispenser";

	public VirtualSlotsCardJFrame() {
		super("Virtual Slots");
	}

	@Override
	protected void myInit() {
		addToCards(new CardSlot(cardLayout), STRING_CARD_SLOT);
		addToCards(new CashDispenser(cardLayout), STRING_CASH_DISPENSER);
		switchToCard(STRING_CARD_SLOT);
	}
}
