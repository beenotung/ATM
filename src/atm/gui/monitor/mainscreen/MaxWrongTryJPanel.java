package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;

import atm.gui.MyGUISettings;

public class MaxWrongTryJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MaxWrongTryJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		add(horizontalGlue_1);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(verticalBox);

		JLabel label = new JLabel("Too many wrong try");
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

	public static void showMe() {
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_MAX_WRONG_TRY);
	}
}
