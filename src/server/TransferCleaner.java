package server;

import java.util.Calendar;
import java.util.Vector;

import core.Account;
import core.SavingAccount;

public class TransferCleaner implements Runnable {
	private Thread thread;
	private boolean alive = false;
	private Vector<Account> savingAccounts;

	public TransferCleaner(Vector<Account> savingAccounts) {
		this.savingAccounts = savingAccounts;
		start();
	}

	@Override
	public void run() {
		Calendar lastUpdated = Calendar.getInstance();
		Calendar now;
		while (alive) {
			boolean shouldUpdate = true;
			now = Calendar.getInstance();
			if (Bank.isOperate()) {
				for (Account account : savingAccounts) {
					account.cleanTransfer();
				}
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
