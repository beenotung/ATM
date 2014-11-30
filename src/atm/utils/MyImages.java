package atm.utils;

import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import myutils.gui.MyImageUtils;
import atm.gui.MyGUISettings;

public class MyImages implements FetchImageNeeder {
	public static ImageIcon banner;
	public static ImageIcon viewBalance;
	public static ImageIcon transfer;
	public static ImageIcon extraCharge;
	public static ImageIcon cashNotEnough;

	private static boolean inited = false;

	public static void init() throws IOException {
		if (inited)
			return;

		banner = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(
				MyURLs.IMAGE_BANNER)), MyGUISettings.MONITOR_TOP_MARGIN);
		viewBalance = MyImageUtils.scaleImageIconByHeight(new ImageIcon(
				new URL(MyURLs.IMAGE_VIEW_BALANCE)),
				MyGUISettings.MONITOR_TOP_MARGIN);
		transfer = MyImageUtils.scaleImageIconByHeight(new ImageIcon(new URL(
				MyURLs.IMAGE_TRANSFER)), MyGUISettings.MONITOR_TOP_MARGIN);
		extraCharge = MyImageUtils.scaleImageIconByHeight(new ImageIcon(
				new URL(MyURLs.IMAGE_EXTRA_CHARGE)),
				MyGUISettings.MONITOR_TOP_MARGIN);
		cashNotEnough = MyImageUtils.scaleImageIconByHeight(new ImageIcon(
				new URL(MyURLs.IMAGE_CASH_NOT_ENOUGH)),
				MyGUISettings.MONITOR_TOP_MARGIN);

		inited = true;
	}

	@Override
	public void fetchImage() throws IOException {
		init();
	}

}
