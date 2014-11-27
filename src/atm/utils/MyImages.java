package atm.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import atm.gui.MyGUISettings;
import myutils.gui.MyImageUtils;

public class MyImages {
	public static ImageIcon banner;
	public static ImageIcon viewBalance;

	public static void init() throws MalformedURLException {
		banner = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(MyURLs.IMAGE_BANNER)),
				MyGUISettings.TOP_MARGIN);
		viewBalance = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(MyURLs.IMAGE_VIEW_BALANCE)),
				MyGUISettings.TOP_MARGIN);
	}

}
