package atm.gui.monitor.mainscreen;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.core.CashDispenser;
import atm.gui.MyGUISettings;
import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.utils.CashCount;
import webs.layout.CircleLayout;
import webs.layout.WrapLayout;

public class ShowPopCashNotesJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Vector<ShowPopCashNotesJPanel> contents = new Vector<ShowPopCashNotesJPanel>();
	private Vector<JLabel> cashNoteJLabels;

	public ShowPopCashNotesJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new CircleLayout());

		cashNoteJLabels = new Vector<JLabel>();
	}

	public void myUpdate(Vector<CashCount> popCashCounts) {
		// reset vectors (internal memory)
		cashNoteJLabels.removeAllElements();
		for (CashCount cashCount : popCashCounts) {
			if (cashCount.getCount() > 0) {
				switch (cashCount.getValue()) {
				case 100:
					for (int i = 0; i < cashCount.getCount(); i++)
						cashNoteJLabels.add(new JLabel(CashNote100.imageIcon));
					break;
				case 500:
					for (int i = 0; i < cashCount.getCount(); i++)
						cashNoteJLabels.add(new JLabel(CashNote500.imageIcon));
					break;
				case 1000:
					for (int i = 0; i < cashCount.getCount(); i++)
						cashNoteJLabels.add(new JLabel(CashNote1000.imageIcon));
					break;
				}
			}
		}

		// reset layout (shown-part GUI)
		removeAll();
		for (JLabel cashNoteJLabel : cashNoteJLabels)
			add(cashNoteJLabel);
	}

	public static void myUpdateStatic(Vector<CashCount> popCashCounts) {
		for (ShowPopCashNotesJPanel content : contents) {
			content.myUpdate(popCashCounts);
		}
	}
}
