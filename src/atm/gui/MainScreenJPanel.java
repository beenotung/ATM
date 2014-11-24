package atm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;

public class MainScreenJPanel extends JPanel {
	public JPanel mainScreenJPanel;
	public JInternalFrame mainScreenJInternalFrame;

	// constructor sets up GUI
	public MainScreenJPanel() {
		mainScreenJPanel = new JPanel();

		add(mainScreenJPanel, BorderLayout.CENTER);
		mainScreenJPanel.setLayout(new BorderLayout(0, 0));

		mainScreenJInternalFrame = new JInternalFrame("New JInternalFrame");
		mainScreenJPanel.add(mainScreenJInternalFrame, BorderLayout.CENTER);
		mainScreenJInternalFrame.setVisible(true);
	}
}