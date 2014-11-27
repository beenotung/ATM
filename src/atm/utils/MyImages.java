package atm.utils;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import atm.gui.MyGUISettings;
import myutils.gui.MyImageUtils;

public class MyImages {
	public static Image banner;
	public static Image viewBalance;

	private static boolean inited = false;

	public static void init() throws IOException {
		if (inited)
			return;
		banner = MyImageUtils.scaleImageByHeight(ImageIO.read(new URL(MyURLs.IMAGE_BANNER)),
				MyGUISettings.TOP_MARGIN);
		viewBalance = MyImageUtils.scaleImageByHeight(ImageIO.read(new URL(MyURLs.IMAGE_VIEW_BALANCE)),
				MyGUISettings.TOP_MARGIN);

		inited = true;
	}

}
