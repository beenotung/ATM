package atm.gui.mainscreen;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import atm.utils.MyStrings;

import java.awt.Component;

import javax.swing.Box;

public class ByeJPanel extends JPanel {
	public ByeJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);

		JPanel panel = new JPanel();
		add(panel);
		panel.setBackground(getParent().getBackground());

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lblNewLabel1 = new JLabel(MyStrings.BYE1);
		lblNewLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel1);
		lblNewLabel1.setFont(MyGUISettings.getFont(26));

		JLabel lblNewLabel2 = new JLabel(MyStrings.BYE2);
		lblNewLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel2);
		lblNewLabel2.setFont(MyGUISettings.getFont(26));

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		add(horizontalGlue_1);
	}	
	
	public static void showMe() {
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_TAKE_CARD);
	}

}
