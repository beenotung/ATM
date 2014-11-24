package atm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SideButtonsJPanel extends JPanel {
	public JPanel leftSideButtonsJPanel;
	public JPanel rightSideButtonsJPanel;

	// constructor sets up GUI
	public SideButtonsJPanel() {
		setLayout(new BorderLayout(0, 0));

		leftSideButtonsJPanel = new JPanel();
		add(leftSideButtonsJPanel, BorderLayout.WEST);
		leftSideButtonsJPanel.setLayout(new GridLayout(4, 4));

		rightSideButtonsJPanel = new JPanel();
		add(rightSideButtonsJPanel, BorderLayout.EAST);
		rightSideButtonsJPanel.setLayout(new GridLayout(4, 4));
	}
}