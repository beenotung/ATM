package atm.gui.virtualslots;

import java.util.Vector;

import javax.swing.JPanel;

public class CashDispenserJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<CashDispenserJPanel> contents = new Vector<CashDispenserJPanel>();

	public CashDispenserJPanel() {
		contents.add(this);
	}
}
