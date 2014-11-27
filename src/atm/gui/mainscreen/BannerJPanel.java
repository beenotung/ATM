package atm.gui.mainscreen;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import atm.utils.MyImages;
import javax.swing.BoxLayout;

import java.awt.Component;

public class BannerJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BannerJPanel() {
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblBanner = new JLabel(new ImageIcon(MyImages.banner));
		lblBanner.setAlignmentY(Component.TOP_ALIGNMENT);
		lblBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblBanner);
	}
}