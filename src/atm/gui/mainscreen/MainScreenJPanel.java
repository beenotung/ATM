package atm.gui.mainscreen;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;

public class MainScreenJPanel extends JPanel {

	// constructor sets up GUI
	public MainScreenJPanel() {
		setLayout(new BorderLayout());
		JInternalFrame internalFrame = new JInternalFrame("ATM Main Screen");
		add(internalFrame);
		internalFrame.setVisible(true);
	}
}