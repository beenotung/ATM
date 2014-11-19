package atm.gui;

import java.awt.EventQueue;

public class ATMGUILauncher {

	private MainScreenThread mainScreenThread;

	public ATMGUILauncher() {
		System.out.println("initializing screen");
		mainScreenThread = new MainScreenThread();
		EventQueue.invokeLater(mainScreenThread);
		System.out.println("initializing slot and dispenser");
		System.out.println("initializing keypad");
	}

	/**
	 * Launch the application.
	 */
	public void start() {
		System.out.println("showing main screen (with side buttons)");
		mainScreenThread.start();
		System.out.println("showing side buttons");
		System.out.println("showing slot and dispenser");
		System.out.println("showing keypad");
	}

}
