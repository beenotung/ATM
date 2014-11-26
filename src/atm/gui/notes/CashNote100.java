package atm.gui.notes;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import atm.utils.MyURLs;

public class CashNote100 implements CashNote{
	public static ImageIcon imageIcon;
	public static JLabel jLabel;
	public static int value;

	public static void init() throws MalformedURLException {
		imageIcon = new ImageIcon(new URL(MyURLs.IMAGE_NOTE100));
		jLabel = new JLabel(imageIcon);
		value = 100;
	}
}
