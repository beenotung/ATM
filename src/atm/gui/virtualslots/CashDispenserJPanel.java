package atm.gui.virtualslots;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import webs.layout.CircleLayout;
import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.utils.CashCount;
import atm.utils.MyStrings;

public class CashDispenserJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CashDispenserJPanel> contents = new Vector<CashDispenserJPanel>();
	private JLabel noCashLabel;
	private JButton takeCashJButton;
	private JPanel cashPanel;

	public CashDispenserJPanel() {
		contents.add(this);
		setLayout(new BorderLayout());

		cashPanel = new JPanel(new CircleLayout());
		add(cashPanel, BorderLayout.CENTER);

		noCashLabel = new JLabel("No cash pop out currently");
		add(noCashLabel, BorderLayout.NORTH);
		noCashLabel.setVisible(true);

		takeCashJButton = new JButton("Take all cash");
		add(takeCashJButton, BorderLayout.NORTH);
		takeCashJButton.setVisible(false);
		takeCashJButton.addActionListener(getButtonActionListener());
	}

	private ActionListener getButtonActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("cash taken by user");
				noCashLabel.setVisible(true);
				takeCashJButton.setVisible(false);
				cashPanel.removeAll();
				cashPanel.updateUI();
				updateUI();
			}
		};
	}

	public void myUpdate(Vector<CashCount> popCashCounts) {
		noCashLabel.setVisible(false);
		takeCashJButton.setVisible(true);
		// reset contentpanel layout (shown-part GUI)
		cashPanel.removeAll();
		for (CashCount cashCount : popCashCounts) {
			if (cashCount.getCount() > 0) {
				switch (cashCount.getValue()) {
				case 100:
					for (int i = 0; i < cashCount.getCount(); i++) {
						cashPanel.add(new JLabel(CashNote100.imageIcon));
						System.out.println("popping " + MyStrings.DOLLAR_SIGN
								+ " " + cashCount.getValue());
					}
					break;
				case 500:
					for (int i = 0; i < cashCount.getCount(); i++) {
						cashPanel.add(new JLabel(CashNote500.imageIcon));
						System.out.println("popping " + MyStrings.DOLLAR_SIGN
								+ " " + cashCount.getValue());
					}
					break;
				case 1000:
					for (int i = 0; i < cashCount.getCount(); i++) {
						cashPanel.add(new JLabel(CashNote1000.imageIcon));
						System.out.println("popping " + MyStrings.DOLLAR_SIGN
								+ " " + cashCount.getValue());
					}
					break;
				}
			}
		}
		updateUI();
	}

	public static void myUpdateStatic(Vector<CashCount> popCashCounts) {
		for (CashDispenserJPanel content : contents) {
			content.myUpdate(popCashCounts);
		}
	}
}
