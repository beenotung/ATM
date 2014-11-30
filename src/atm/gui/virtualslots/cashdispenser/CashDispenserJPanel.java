package atm.gui.virtualslots.cashdispenser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import webs.layout.WrapLayout;
import atm.core.CashDispenser;
import atm.gui.virtualslots.VirtualSlotsJFrame;
import atm.gui.virtualslots.cashdispenser.notes.CashNote100;
import atm.gui.virtualslots.cashdispenser.notes.CashNote1000;
import atm.gui.virtualslots.cashdispenser.notes.CashNote500;
import atm.utils.CashCount;
import atm.utils.MyStrings;

import javax.swing.BoxLayout;

import java.awt.Component;

public class CashDispenserJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<CashDispenserJPanel> contents = new Vector<CashDispenserJPanel>();

	private JButton takeCashJButton;
	private JPanel cashPanel;
	public static boolean hasCashToBePopped = false;
	private Vector<CashCount> popCashCounts;

	public CashDispenserJPanel() {
		contents.add(this);
		setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		takeCashJButton = new JButton("Take all cash");
		takeCashJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanel.add(takeCashJButton);
		takeCashJButton.setVisible(false);
		takeCashJButton.addActionListener(getButtonActionListener());

		//cashPanel = new JPanel(new CircleLayout());
		cashPanel = new JPanel(new WrapLayout());
		add(cashPanel, BorderLayout.CENTER);
		cashPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cashPanel.setVisible(false);
	}

	private ActionListener getButtonActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("cash taken by user");
				hasCashToBePopped = false;
				CashDispenser.commit();
				takeCashJButton.setVisible(false);
				cashPanel.setVisible(false);
				VirtualSlotsJFrame.myResetStatic();
			}
		};
	}

	public void setPopCashCounts(Vector<CashCount> popCashCounts) {
		hasCashToBePopped = true;
		this.popCashCounts = popCashCounts;
	}

	public void showMe() {
		takeCashJButton.setVisible(true);
		cashPanel.setVisible(true);
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
	}

	/** static connector to instance stuff **/
	public static void setPopCashCountsStatic(Vector<CashCount> popCashCounts) {
		for (CashDispenserJPanel content : contents) {
			content.setPopCashCounts(popCashCounts);
		}
	}

	public static void showMeStatic() {
		for (CashDispenserJPanel content : contents) {
			content.showMe();
		}
	}
}
