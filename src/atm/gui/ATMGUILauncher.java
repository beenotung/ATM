package atm.gui;

import java.net.MalformedURLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
			System.out.println("loading native system look and feel (" + name + ")");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			try {
				name = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				UIManager.setLookAndFeel(name);
				System.out.println("loading native system look and feel (" + name + ")");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				try {
					name = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(name);
					System.out.println("loading native system look and feel (" + name + ")");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e2) {
					System.out.println("loading cross platform look and feel ("
							+ UIManager.getCrossPlatformLookAndFeelClassName() + ")");
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

	public void fetchImages() throws MalformedURLException {
		Card.init();
		CashNote100.init();
		CashNote500.init();
		CashNote1000.init();
		LeftSideButtonsJPanel.init();
		RightSideButtonsJPanel.init();
	}
}
