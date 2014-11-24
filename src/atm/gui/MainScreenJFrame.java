package atm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainScreenJFrame extends JFrame {
	private JPanel mainScreenJPanel;

	// constructor sets up GUI
	public MainScreenJFrame() {
		setTitle("MainScreen");
		setVisible(false);
		setResizable(false);

		mainScreenJPanel = new JPanel();
		mainScreenJPanel.setLayout(new GridLayout(4, 4));

		getContentPane().add(mainScreenJPanel, BorderLayout.CENTER);
	}

	public void calcBounds(int w, int h) {
		pack();
		setVisible(true);
		int x = 0;
		int y = 0;
		setBounds(x, y, w, h);
	}
}