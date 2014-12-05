package atm.gui;

import java.io.IOException;
import java.util.Vector;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import atm.gui.keypad.KeyPadButtonIcons;
import atm.gui.monitor.sidebuttons.SideButtons;
import atm.gui.virtualslots.cardslot.Card;
import atm.gui.virtualslots.cashdispenser.notes.CashNote100;
import atm.gui.virtualslots.cashdispenser.notes.CashNote1000;
import atm.gui.virtualslots.cashdispenser.notes.CashNote500;
import atm.utils.FetchImageRunnable;
import atm.utils.MyImages;

public class ATMGUILauncher {
	JFrameManager frameManager;

	public ATMGUILauncher() throws IOException {
		System.out.println("fetching image in multi-thread mode");
		fetchImages();
		System.out.println("initializing GUI");
		setLookAndFeel();
		frameManager = new JFrameManager();
		frameManager.start();
	}

	private void setLookAndFeel() {
		String name = "";
		try {
			// name = "com.easynth.lookandfeel.EaSynthLookAndFeel";
			// name="com.alee.laf.WebLookAndFeel";
			UIManager.setLookAndFeel(name);
			System.out.println("loading native system look and feel (" + name
					+ ")");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			try {
				name = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				UIManager.setLookAndFeel(name);
				System.out.println("loading native system look and feel ("
						+ name + ")");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				try {
					name = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(name);
					System.out.println("loading native system look and feel ("
							+ name + ")");
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e2) {
					System.out.println("loading cross platform look and feel ("
							+ UIManager.getCrossPlatformLookAndFeelClassName()
							+ ")");
				}
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public void start() {
		System.out.println();
		System.out.println("showing GUI");
		frameManager.start();
	}

	@SuppressWarnings("deprecation")
	public void fetchImages() throws IOException {
		// prepare
		Vector<FetchImageRunnable> runnables = new Vector<FetchImageRunnable>();
		Vector<Thread> threads = new Vector<Thread>();
		runnables.add(new FetchImageRunnable(new Card()));
		runnables.add(new FetchImageRunnable(new CashNote100()));
		runnables.add(new FetchImageRunnable(new CashNote500()));
		runnables.add(new FetchImageRunnable(new CashNote1000()));
		runnables.add(new FetchImageRunnable(new KeyPadButtonIcons()));
		runnables.add(new FetchImageRunnable(new SideButtons()));
		runnables.add(new FetchImageRunnable(new MyImages()));
		for (FetchImageRunnable fetchImageRunnable : runnables) {
			threads.add(new Thread(fetchImageRunnable));
		}
		// start all downloading thread
		for (Thread thread : threads) {
			thread.start();
		}
		// wait all downloading thread finished
		boolean allFinished;
		long startTime = System.currentTimeMillis();
		do {
			allFinished = true;
			for (FetchImageRunnable fetchImageRunnable : runnables) {
				allFinished &= fetchImageRunnable.isFinished();
			}
			if (!allFinished) {
				System.out.println("still downloading..."
						+ (System.currentTimeMillis() - startTime) / 1000.0
						+ " second(s) passed");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// OS Level Error
					System.out.println("OS Level Error");
				}
			}
		} while (!allFinished);
		System.out.println("finished download==>"
				+ (System.currentTimeMillis() - startTime) / 1000.0
				+ " second(s) passed");
	}
}
