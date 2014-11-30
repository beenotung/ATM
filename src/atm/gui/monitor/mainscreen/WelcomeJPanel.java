package atm.gui.monitor.mainscreen;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import atm.core.ATM;
import atm.gui.MyGUISettings;
import java.awt.Component;

public class WelcomeJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<WelcomeJPanel> contents = new Vector<WelcomeJPanel>();

	private BannerJPanel bannerJPanel;
	private AvailableCashNotesJPanel availableCashNotesJPanel;

	public WelcomeJPanel() {
		contents.add(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMScreenBackGroundColor());

		bannerJPanel = new BannerJPanel();
		bannerJPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(bannerJPanel);

		availableCashNotesJPanel = new AvailableCashNotesJPanel();
		add(availableCashNotesJPanel);
	}

	public void showMe() {
		System.out.println("WelcomeJPanel.showMe()");
		availableCashNotesJPanel.myUpdate();
		ATM.initStatic();
		MainScreenCardJPanel
				.switchToCardStatic(MainScreenCardJPanel.STRING_WELCOME);
	}

	public static void showMeStatic() {
		System.out.println("WelcomeJPanel.showMeStatic()");
		for (WelcomeJPanel welcomeJPanel : contents) {
			welcomeJPanel.showMe();
		}
	}
}