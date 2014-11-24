package atm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SideButtonsJFrame extends JFrame {
	private JPanel sideButtonsJPanel;

	// constructor sets up GUI
	public SideButtonsJFrame() {
		setTitle("MainScreen");
		setVisible(false);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		sideButtonsJPanel = new JPanel();
		sideButtonsJPanel.setLayout(new GridLayout(4, 4));

		getContentPane().add(sideButtonsJPanel);
	}

	public void calcBounds() {
		// pack();
		setVisible(true);
		Rectangle client = getBounds();
		int x = 0;
		int y = 0;
		setBounds(x, y, client.width, client.height);
	}
}