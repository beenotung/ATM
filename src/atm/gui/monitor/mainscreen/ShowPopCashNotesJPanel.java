package atm.gui.monitor.mainscreen;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import atm.gui.virtualslots.cashdispenser.notes.CashNote100;
import atm.gui.virtualslots.cashdispenser.notes.CashNote1000;
import atm.gui.virtualslots.cashdispenser.notes.CashNote500;
import atm.utils.CashCount;
import webs.layout.CircleLayout;

public class ShowPopCashNotesJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<ShowPopCashNotesJPanel> contents = new Vector<ShowPopCashNotesJPanel>();

	public ShowPopCashNotesJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new CircleLayout());
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
		for (ShowPopCashNotesJPanel content : contents) {
			content.myUpdate(popCashCounts);
		}
	}
}
