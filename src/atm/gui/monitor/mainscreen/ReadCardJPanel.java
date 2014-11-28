package atm.gui.monitor.mainscreen;

import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;

public class ReadCardJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReadCardJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		Component glue = Box.createHorizontalGlue();
		add(glue);
		JLabel label = new JLabel("Reading Your Card...");
		add(label);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(MyGUISettings.getFont(26));

		Component glue_1 = Box.createHorizontalGlue();
		add(glue_1);
	}
}
