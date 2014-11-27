package atm.utils;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import atm.gui.MyGUISettings;
import myutils.gui.MyImageUtils;

public class MyImages {
	public static ImageIcon banner;
	public static ImageIcon viewBalance;

	private static boolean inited = false;

	public static void init() throws IOException {
		if (inited)
			return;

		banner = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(MyURLs.IMAGE_BANNER)),
				MyGUISettings.TOP_MARGIN);
		viewBalance = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(MyURLs.IMAGE_VIEW_BALANCE)),
				MyGUISettings.TOP_MARGIN);

		inited = true;
	}

}
