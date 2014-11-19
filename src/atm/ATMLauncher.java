package atm;

import bank.database.BankDatabase;
import atm.gui.ATMGUILauncher;

public class ATMLauncher {

	private ATMGUILauncher atmguiLauncher;

	public ATMLauncher() {
		BankDatabase.init();
		atmguiLauncher = new ATMGUILauncher();		
	}

	public void start() {
		System.out.println("start GUI launcher");
		atmguiLauncher.start();
	}
}
