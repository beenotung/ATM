package atm.gui;

import java.awt.EventQueue;

public class ATMGUILauncher {

	private MainScreenThread mainScreenThread;
	private KeypadThread keypadThread;
	private VirtualSlotThread virtualSlotThread;

	public ATMGUILauncher() {
		System.out.println("initializing screen");
		mainScreenThread = new MainScreenThread();
		EventQueue.invokeLater(mainScreenThread);
		System.out.println("initializing keypad");
		keypadThread = new KeypadThread();
		EventQueue.invokeLater(keypadThread);
		System.out.println("initializing card slot and cash dispenser");
		virtualSlotThread = new VirtualSlotThread();
		EventQueue.invokeLater(virtualSlotThread);
	}

	/**
	 * Launch the application.
	 */
	public void start() {
		System.out.println("showing main screen (with side buttons)");
		mainScreenThread.start(100,100);
		System.out.println("showing keypad");
		keypadThread.start();
		System.out.println("showing slot and dispenser");
		virtualSlotThread.start();
	}

}
