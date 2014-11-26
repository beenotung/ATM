package atm.gui;

import java.awt.Color;
import java.awt.Font;

public class MyGUISettings {
	public static int SIDE_BUTTON_SIZE = 50;
	public static int CARD_IMAGE_WIDTH = 128;
	public static int CARD_IMAGE_HEIGHT = 64;

	public static Font getFont(int fontSize) {
		return new Font("Arial", Font.PLAIN, fontSize);
	}
	public static Color getATMScreenBackGroundColor(){
		return new Color(135, 206, 250);
	}
}
