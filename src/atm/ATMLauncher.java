package atm;

import atm.gui.ATMGUILauncher;

public class ATMLauncher {

	private ATMGUILauncher atmguiLauncher;

	public ATMLauncher() {
		atmguiLauncher = new ATMGUILauncher();
	}

	public void start() {
		atmguiLauncher.start();
	}
}
