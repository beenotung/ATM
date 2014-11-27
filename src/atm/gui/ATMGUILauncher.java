package atm.gui;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import atm.gui.keypad.KeyPadButtonIcons;
import atm.gui.notes.CashNote100;
import atm.gui.notes.CashNote1000;
import atm.gui.notes.CashNote500;
import atm.gui.sidebuttons.SideButtons;
import atm.gui.virtualslots.Card;
import atm.utils.MyImages;

public class ATMGUILauncher {
	JFrameManager frameManager;

	public ATMGUILauncher() throws IOException {
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

	public void fetchImages() throws IOException {
		System.out.println("fetching images of cards");
		Card.init();
		System.out.println("fetching images of cash notes");		
		CashNote100.init();
		CashNote500.init();
		CashNote1000.init();
		System.out.println("fetching images of keypads");
		KeyPadButtonIcons.init();
		SideButtons.init();		
		System.out.println("fetching images for layout");
		MyImages.init();
	}
}
