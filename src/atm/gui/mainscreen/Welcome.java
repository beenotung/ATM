package atm.gui.mainscreen;

import java.awt.CardLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import atm.utils.MyStrings;
import atm.utils.MyURLs;
import javax.swing.BoxLayout;
import java.awt.Component;

public class Welcome extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;

	public Welcome(CardLayout cardLayout) {
		this.cardLayout = cardLayout;

		try {
			JLabel lblBanner;
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			lblBanner = new JLabel(new ImageIcon(new URL(
					MyURLs.STRING_URL_IMAGE_BANNER)));
			lblBanner.setAlignmentY(Component.TOP_ALIGNMENT);
			lblBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(lblBanner);
		} catch (MalformedURLException e) {
			System.out.println(MyStrings.INTERNET_ERROR);
			JOptionPane.showMessageDialog(getParent(),
					MyStrings.INTERNET_ERROR, "Internet Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
}