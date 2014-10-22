package server;

import java.util.Calendar;
import java.util.Vector;

import core.Account;
import core.SavingAccount;

public class SavingAccountInterestPaying implements Runnable {
	private Thread thread;
	private boolean alive = false;
	private Vector<Account> savingAccounts;

	public SavingAccountInterestPaying(Vector<Account> savingAccounts) {
		this.savingAccounts = savingAccounts;
	}

	@Override
	public void run() {
		Calendar lastUpdated = Calendar.getInstance();
		Calendar now;
		while (alive) {
			boolean shouldUpdate = true;
			now = Calendar.getInstance();
			if (now.get(Calendar.MONTH) == SavingAccount.getAnnum().get(Calendar.MONTH))
				shouldUpdate = false;
			if (now.get(Calendar.DAY_OF_MONTH) == SavingAccount.getAnnum().get(
					Calendar.DAY_OF_MONTH))
				shouldUpdate = false;
			if (now.get(Calendar.HOUR_OF_DAY) == SavingAccount.getAnnum().get(
					Calendar.HOUR_OF_DAY))
				shouldUpdate = false;
			if (now.get(Calendar.MINUTE) == SavingAccount.getAnnum().get(Calendar.MINUTE))
				shouldUpdate = false;
			if (lastUpdated.get(Calendar.YEAR) == now.get(Calendar.YEAR))
				shouldUpdate = false;
			if (shouldUpdate) {
				update();
				lastUpdated = Calendar.getInstance();
			}
			try {
				thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	private void update() {
		for (Account account : savingAccounts) {
			if (account instanceof SavingAccount) {
				SavingAccount savingAccount = (SavingAccount) account;
				savingAccount.interestPaying();
			}
		}
	}

	public void start() {
		if (thread == null)
			thread = new Thread(this);
		if (!alive) {
			alive = true;
			thread.start();
		}
	}
}
