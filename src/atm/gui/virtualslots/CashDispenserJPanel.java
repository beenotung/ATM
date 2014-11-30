package atm.gui.virtualslots;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.utils.CashCount;

public class CashDispenserJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CashDispenserJPanel> contents = new Vector<CashDispenserJPanel>();

	public CashDispenserJPanel() {
		contents.add(this);
	}

	public void myUpdate(Vector<CashCount> popCashCounts) {
		// reset layout (shown-part GUI)
		removeAll();
		for (CashCount cashCount : popCashCounts) {
			if (cashCount.getCount() > 0) {
				switch (cashCount.getValue()) {
				case 100:
					for (int i = 0; i < cashCount.getCount(); i++)
						add(new JLabel(CashNote100.imageIcon));
					break;
				case 500:
					for (int i = 0; i < cashCount.getCount(); i++)
						add(new JLabel(CashNote500.imageIcon));
					break;
				case 1000:
					for (int i = 0; i < cashCount.getCount(); i++)
						add(new JLabel(CashNote1000.imageIcon));
					break;
				}
			}
		}
	}

	public static void myUpdateStatic(Vector<CashCount> popCashCounts) {
		for (CashDispenserJPanel content : contents) {
			content.myUpdate(popCashCounts);
		}
	}
}
