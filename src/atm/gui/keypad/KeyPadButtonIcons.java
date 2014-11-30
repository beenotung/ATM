package atm.gui.keypad;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import atm.gui.MyGUISettings;
import atm.gui.virtualslots.cashdispenser.notes.CashNote;
import atm.utils.MyURLs;

public class KeyPadButtonIcons implements CashNote {
	public static ImageIcon IMAGEICON_ENTER;
	public static ImageIcon IMAGEICON_CANCEL;
	public static ImageIcon IMAGEICON_CLEAR;

	public static void init() throws MalformedURLException {
		IMAGEICON_ENTER = new ImageIcon(new ImageIcon(new URL(
				MyURLs.IMAGE_ENTER)).getImage().getScaledInstance(
				MyGUISettings.FUNCTION_BUTTON_WIDTH,
				MyGUISettings.FUNCTION_BUTTON_HEIGHT, Image.SCALE_SMOOTH));
		IMAGEICON_CANCEL = new ImageIcon(new ImageIcon(new URL(
				MyURLs.IMAGE_CANCEL)).getImage().getScaledInstance(
				MyGUISettings.FUNCTION_BUTTON_WIDTH,
				MyGUISettings.FUNCTION_BUTTON_HEIGHT, Image.SCALE_SMOOTH));
		IMAGEICON_CLEAR = new ImageIcon(new ImageIcon(new URL(
				MyURLs.IMAGE_CLEAR)).getImage().getScaledInstance(
				MyGUISettings.FUNCTION_BUTTON_WIDTH,
				MyGUISettings.FUNCTION_BUTTON_HEIGHT, Image.SCALE_SMOOTH));
	}

	@Override
	public void fetchImage() throws IOException {
		System.out.println("fetching images of keypads");
		init();
	}
}
