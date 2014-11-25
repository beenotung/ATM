package atm.gui.sidebuttons;

import java.awt.GridLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import atm.gui.MyGUISettings;
import atm.utils.MyURLs;

public class RightSideButtonsJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector<JButton> buttons;
	private ImageIcon imageIcon;

	public RightSideButtonsJPanel() throws MalformedURLException {
		setLayout(new GridLayout(4, 1, 0, 0));

		imageIcon = new ImageIcon(new ImageIcon(new URL(
				MyURLs.STRING_URL_IMAGE_TRIANGLE_POINT_LEFT)).getImage()
				.getScaledInstance(MyGUISettings.SIDE_BUTTON_SIZE, MyGUISettings.SIDE_BUTTON_SIZE, Image.SCALE_SMOOTH));

		buttons = new Vector<JButton>();
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			add(panel);
			JButton button = new JButton(imageIcon);
			buttons.add(button);
			panel.add(button);
		}
	}
}
