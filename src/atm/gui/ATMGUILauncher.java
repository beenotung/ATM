package atm.gui;

import java.net.MalformedURLException;

import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.gui.sidebuttons.LeftSideButtonsJPanel;
import atm.gui.sidebuttons.RightSideButtonsJPanel;
import atm.gui.virtualslots.Card;

public class ATMGUILauncher {
	JFrameManager frameManager;

	public ATMGUILauncher() throws MalformedURLException {
		System.out.println("fetching images");
		fetchImages();
		System.out.println("initializing GUI");
		frameManager = new JFrameManager();
	}

	/**
	 * Launch the application.
	 */
	public void start() {
		System.out.println();
		System.out.println("showing GUI");
		frameManager.start();
	}

	public void fetchImages() throws MalformedURLException {
		Card.init();
		CashNote100.init();
		CashNote500.init();
		CashNote1000.init();
		LeftSideButtonsJPanel.init();
		RightSideButtonsJPanel.init();
	}
}
