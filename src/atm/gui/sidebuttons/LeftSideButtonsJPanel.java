
package atm.gui.sidebuttons;

import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import atm.gui.MyGUISettings;
import atm.utils.MyURLs;

import java.awt.Image;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class LeftSideButtonsJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector<JButton> buttons;
	private static ImageIcon imageIcon;

	public static void init() throws MalformedURLException {
		imageIcon = new ImageIcon(new ImageIcon(new URL(MyURLs.IMAGE_TRIANGLE_POINT_RIGHT)).getImage()
				.getScaledInstance(MyGUISettings.SIDE_BUTTON_SIZE, MyGUISettings.SIDE_BUTTON_SIZE,
						Image.SCALE_SMOOTH));
	}

	public LeftSideButtonsJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMShellColor());

		Component topVerticalStrut = Box.createVerticalStrut(75);
		add(topVerticalStrut);

		JPanel centerPanel = new JPanel();
		add(centerPanel);
		centerPanel.setLayout(new GridLayout(4, 1, 0, 0));

		Component bottomVerticalStrut = Box.createVerticalStrut(25);
		add(bottomVerticalStrut);

		buttons = new Vector<JButton>();
		for (int i = 0; i < 4; i++) {
			JButton button = new JButton(imageIcon);
			buttons.add(button);
			centerPanel.add(button);
		}
	}
}
