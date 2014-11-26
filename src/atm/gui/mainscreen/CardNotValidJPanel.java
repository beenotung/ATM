package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;

public class CardNotValidJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardNotValidJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		add(horizontalGlue_1);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(verticalBox);

		JLabel label = new JLabel("Your card is not identified");
		label.setFont(new Font("Arial", Font.PLAIN, 26));
		label.setAlignmentX(0.5f);
		verticalBox.add(label);

		JLabel label_1 = new JLabel("Please contact CC Bank (9876-5432)");
		label_1.setFont(new Font("Arial", Font.PLAIN, 26));
		label_1.setAlignmentX(0.5f);
		verticalBox.add(label_1);

		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
	}
}
