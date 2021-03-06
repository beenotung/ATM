package atm.gui.monitor.sidebuttons;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import atm.gui.MyGUISettings;
import atm.gui.monitor.MonitorJFrame;
import atm.gui.virtualslots.cashdispenser.notes.CashNote;
import atm.utils.MyURLs;

public class SideButtons implements CashNote {
	public static ImageIcon triangle_point_left_imageIcon;
	public static ImageIcon triangle_point_right_imageIcon;
	public static String[] commands = new String[8];

	public static void init() throws MalformedURLException {
		triangle_point_right_imageIcon = new ImageIcon(new ImageIcon(new URL(
				MyURLs.IMAGE_TRIANGLE_POINT_RIGHT)).getImage()
				.getScaledInstance(MyGUISettings.SIDE_BUTTON_SIZE,
						MyGUISettings.SIDE_BUTTON_SIZE, Image.SCALE_SMOOTH));

		triangle_point_left_imageIcon = new ImageIcon(new ImageIcon(new URL(
				MyURLs.IMAGE_TRIANGLE_POINT_LEFT)).getImage()
				.getScaledInstance(MyGUISettings.SIDE_BUTTON_SIZE,
						MyGUISettings.SIDE_BUTTON_SIZE, Image.SCALE_SMOOTH));
	}

	public static void click(int id) {
		MonitorJFrame.sideButtonClick(commands[id - 1]);
	}

	@Override
	public void fetchImage() throws IOException {
		System.out.println("fetching images for layout");
		init();
	}
}
