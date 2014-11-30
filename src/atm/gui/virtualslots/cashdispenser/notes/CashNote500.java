package atm.gui.virtualslots.cashdispenser.notes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import atm.utils.MyURLs;

public class CashNote500 implements CashNote {
	public static ImageIcon imageIcon;
	public static JLabel jLabel;
	public static int value;

	public static void init() throws MalformedURLException {
		imageIcon = new ImageIcon(new URL(MyURLs.IMAGE_NOTE500));
		jLabel = new JLabel(imageIcon);
		value = 500;
	}

	@Override
	public void fetchImage() throws IOException {
		System.out.println("fetching images of cash note 500");
		init();
	}
}
