package atm.gui.virtualslots;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import atm.gui.MyGUISettings;
import atm.gui.monitor.mainscreen.WelcomeJPanel;
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
		// getContentPane().setLayout(new WrapLayout());

		cardSlotCardJPanel = new CardSlotCardJPanel();
		getContentPane().add(cardSlotCardJPanel);

		cashDispenserJPanel = new CashDispenserJPanel();
		getContentPane().add(cashDispenserJPanel);

		myReset();
	}

	public void calcBounds() {
		setVisible(true);
		pack();
		Rectangle client = new Rectangle(
				MyGUISettings.VIRTUAL_SLOTS_FRAME_WIDTH,
				MyGUISettings.VIRTUAL_SLOTS_FRAME_HEIGHT);
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().getBounds();
		int x = screen.width - client.width;
		int y = 0;
		setBounds(x, y, client.width, client.height);
	}

	public void calcBounds(float wRatio, float hRatio) {
		setVisible(true);
		pack();
		Rectangle client = new Rectangle(
				Math.round(MyGUISettings.VIRTUAL_SLOTS_FRAME_WIDTH * wRatio),
				Math.round(MyGUISettings.VIRTUAL_SLOTS_FRAME_HEIGHT * hRatio));
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().getBounds();
		int x = screen.width - client.width;
		int y = 0;
		setBounds(x, y, client.width, client.height);
	}

	public void hideCardSlot() {
		cardSlotCardJPanel.setVisible(false);
		// calcBounds(1f, 2f);
	}

	public void myReset() {
		cardSlotCardJPanel.setVisible(true);

		cardSlotCardJPanel.switchToCard(CardSlotCardJPanel.STRING_SELECT_CARD);
		WelcomeJPanel.showMeStatic();

		calcBounds();
	}

	/** static connector to instance stuff **/
	public static void myResetStatic() {
		for (VirtualSlotsJFrame virtualSlotsJFrame : contents) {
			virtualSlotsJFrame.myReset();
		}
	}

	public static void hideCardSlotStatic() {
		for (VirtualSlotsJFrame virtualSlotsJFrame : contents) {
			virtualSlotsJFrame.hideCardSlot();
		}
	}
}
