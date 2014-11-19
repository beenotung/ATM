package atm.core;

import bank.database.BankDatabase;

public class UI {
	public BankDatabase bankDatabase;
	public Screen screen;
	public Keypad keypad;

	public UI(Screen screen, BankDatabase bankDatabase, Keypad keypad) {
		this.screen = screen;
		this.bankDatabase = bankDatabase;
		this.keypad = keypad;
	}

}
