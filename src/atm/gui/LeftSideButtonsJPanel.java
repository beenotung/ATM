package atm.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

public class LeftSideButtonsJPanel extends JPanel {
	public LeftSideButtonsJPanel() {
		setLayout(new GridLayout(4, 4));

		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("New button");
		add(btnNewButton_4);
	}
}
