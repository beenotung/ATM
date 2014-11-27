package atm.gui.mainscreen;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.gui.MyGUISettings;
import atm.utils.MyImages;
import atm.utils.MyStrings;
import atm.utils.MyURLs;

import javax.swing.BoxLayout;

import com.mortennobel.imagescaling.ImageUtils;
import com.sun.imageio.plugins.common.ImageUtil;

import java.awt.Component;

public class BannerJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BannerJPanel() {
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblBanner = new JLabel(MyImages.banner);
		lblBanner.setAlignmentY(Component.TOP_ALIGNMENT);
		lblBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblBanner);
	}
}