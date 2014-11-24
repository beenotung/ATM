package atm.gui;

import java.net.MalformedURLException;

public class ATMGUILauncher {
	JFrameManager frameManager;

	public ATMGUILauncher() throws MalformedURLException {
		System.out.println("initializing screen");
		System.out.println("initializing keypad");
		System.out.println("initializing card slot and cash dispenser");
		frameManager = new JFrameManager();
	}

	/**
	 * Launch the application.
	 */
	public void start() {
		System.out.println("showing main screen (with side buttons)");
		System.out.println("showing keypad");
		System.out.println("showing slot and dispenser");
		frameManager.start();
	}

}
