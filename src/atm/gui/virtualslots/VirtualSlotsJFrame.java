package atm.gui.virtualslots;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import atm.gui.virtualslots.cardslot.CardSlotCardJPanel;
import atm.gui.virtualslots.cashdispenser.CashDispenserJPanel;

public class VirtualSlotsJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<VirtualSlotsJFrame> contents = new Vector<VirtualSlotsJFrame>();

	private CardSlotCardJPanel cardSlotCardJPanel;
	private CashDispenserJPanel cashDispenserJPanel;

	public VirtualSlotsJFrame() {
		super("Virtual Slots");
		contents.add(this);
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

	public void myUpdateUI() {
		cardSlotCardJPanel.myUpdateUI();
		cashDispenserJPanel.myUpdateUI();
		setPreferredSize(getMinimumSize());
		calcBounds();
	}

	public static void myUpdateUIStatic() {
		for (VirtualSlotsJFrame virtualSlotsJFrame : contents) {
			virtualSlotsJFrame.myUpdateUI();
		}
	}
}
