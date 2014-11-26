package atm;

import java.net.MalformedURLException;

import javax.swing.JOptionPane;

import bank.database.BankDatabase;
import atm.core.CashDispenser;
import atm.gui.ATMGUILauncher;
import atm.utils.MyStrings;

public class ATMLauncher {

	private ATMGUILauncher atmguiLauncher;

	public ATMLauncher() {
		BankDatabase.init();
		CashDispenser.init();
		try {
			atmguiLauncher = new ATMGUILauncher();
		} catch (MalformedURLException e) {
			System.out.println(MyStrings.INTERNET_ERROR);
			JOptionPane.showMessageDialog(null, MyStrings.INTERNET_ERROR,
					"Internet Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void start() {
		System.out.println("start GUI launcher");
		atmguiLauncher.start();
	}
}
