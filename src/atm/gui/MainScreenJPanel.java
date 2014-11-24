package atm.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainScreenJPanel extends JPanel {

	// constructor sets up GUI
	public MainScreenJPanel() {
		setLayout(new BorderLayout());
		JInternalFrame internalFrame = new JInternalFrame("ATM Main Screen");
		add(internalFrame);
		internalFrame.setVisible(true);
	}
}