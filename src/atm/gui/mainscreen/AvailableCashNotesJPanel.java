package atm.gui.mainscreen;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.core.CashDispenser;
import atm.gui.MyGUISettings;
import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.utils.CashCount;

import webs.layout.WrapLayout;

public class AvailableCashNotesJPanel extends JPanel {
	private static Vector<AvailableCashNotesJPanel> contents = new Vector<AvailableCashNotesJPanel>();
	private JLabel label;
	Vector<JLabel> cashNoteJLabels;

	public AvailableCashNotesJPanel() {
		contents.add(this);
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new WrapLayout());

		label = new JLabel();
		add(label);

		cashNoteJLabels = new Vector<JLabel>();
		myUpdate();
	}

	public void myUpdate() {
		cashNoteJLabels.removeAllElements();
		for (CashCount cashCount : CashDispenser.cashCounts) {
			if (cashCount.getCount() > 0) {
				switch (cashCount.getValue()) {
				case 100:
					cashNoteJLabels.add(CashNote100.jLabel);
					break;
				case 500:
					cashNoteJLabels.add(CashNote500.jLabel);
					break;
				case 1000:
					cashNoteJLabels.add(CashNote1000.jLabel);
					break;
				}
			}
		}
		if (cashNoteJLabels.size() == 0) {
			label.setText("This ATM does not provide any cash notes");
		} else {
			label.setText("This ATM provide the following types of cash note");
		}
		// label.setFont(MyGUISettings.getFont(26));
		label.setFont(MyGUISettings.getBoldFont(24));

		removeAll();

		add(label);
		for (JLabel cashNoteJLabel : cashNoteJLabels)
			add(cashNoteJLabel);
	}

	public static void myUpdateStatic() {
		for (AvailableCashNotesJPanel cashAvailableJPanel : contents) {
			cashAvailableJPanel.myUpdate();
		}
	}
}
