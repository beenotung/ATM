package atm.gui.virtualslots;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class VirtualSlotsJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardSlotCardJPanel cardSlotCardJPanel;
	private CashDispenserJPanel cashDispenserJPanel;

	public VirtualSlotsJFrame() {
		super("Virtual Slots");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(400, 300);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		cardSlotCardJPanel = new CardSlotCardJPanel();
		getContentPane().add(cardSlotCardJPanel);

		cashDispenserJPanel = new CashDispenserJPanel();
		getContentPane().add(cashDispenserJPanel);
	}

	public void calcBounds() {
		setVisible(true);
		pack();
		Rectangle client = getBounds();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().getBounds();
		int x = screen.width - client.width;
		int y = 0;
		setLocation(x, y);
	}
}
