package atm.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RightSideButtonsJPanel extends JPanel {
	public RightSideButtonsJPanel() {
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
