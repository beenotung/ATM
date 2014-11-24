package atm.gui.sidebuttons;

import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import atm.utils.MyURLs;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

public class LeftSideButtonsJPanel extends JPanel {
	public Vector<JButton> buttons;
	private ImageIcon imageIcon;

	public LeftSideButtonsJPanel() throws MalformedURLException {
		setLayout(new GridLayout(4, 1, 0, 0));

		imageIcon = new ImageIcon(new ImageIcon(new URL(
				MyURLs.STRING_URL_IMAGE_TRIANGLE_POINT_RIGHT)).getImage()
				.getScaledInstance(75, 75, Image.SCALE_SMOOTH));

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
