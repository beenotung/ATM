package atm.gui;

import java.awt.Color;
import java.awt.Font;

public class MyGUISettings {
	public static int MONITOR_TOP_MARGIN = 75;
	public static int MONITOR_FRAME_WIDTH = 600;
	public static int MONITOR_FRAME_HEIGHT = 400;	
	public static int VIRTUAL_SLOTS_FRAME_WIDTH = 400;
	public static int VIRTUAL_SLOTS_FRAME_HEIGHT = 400;
	public static int SIDE_BUTTON_MARGIN = 75;
	public static int SIDE_BUTTON_SIZE = 50;
	public static int CARD_IMAGE_WIDTH = 128;
	public static int CARD_IMAGE_HEIGHT = 64;
	public static int FUNCTION_BUTTON_WIDTH = 64;
	public static int FUNCTION_BUTTON_HEIGHT = 32;
	

	public static Font getFont(int fontSize) {
		return new Font("Arial", Font.PLAIN, fontSize);
	}

	public static Font getBoldFont(int fontSize) {
		return new Font("Arial", Font.BOLD, fontSize);
	}

	public static Color getATMScreenBackGroundColor() {
		return new Color(135, 206, 250);
	}

	public static Color getATMShellColor() {
		return new Color(128, 128, 128);
	}
}
