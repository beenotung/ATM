package atm.gui.mainscreen;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import atm.gui.MyGUISettings;

public class WelcomeJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<WelcomeJPanel> contents = new Vector<WelcomeJPanel>();

	private BannerJPanel bannerJPanel;
	private AvailableCashNotesJPanel availableCashNotesJPanel;

	public WelcomeJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		bannerJPanel = new BannerJPanel();
		add(bannerJPanel);

		availableCashNotesJPanel = new AvailableCashNotesJPanel();
		add(availableCashNotesJPanel);
	}

	public void showMe() {
		availableCashNotesJPanel.myUpdate();
	}

	public static void showMeStatic() {
		for (WelcomeJPanel welcomeJPanel : contents) {
			welcomeJPanel.showMe();
		}
	}
}