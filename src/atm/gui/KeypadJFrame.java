package atm.gui;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class KeypadJFrame extends JFrame {
	private JComponent keys[];
	private JPanel numberKeysJPanel;
	private JPanel functionKeysJPanel;

	// constructor sets up GUI
	public KeypadJFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Keypad");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		numberKeysJPanel = new JPanel(new GridLayout(4, 3));
		getContentPane().add(numberKeysJPanel);

		functionKeysJPanel = new JPanel(new GridLayout(4, 1));
		getContentPane().add(functionKeysJPanel);

		keys = new JComponent[16]; // array keys contains 16 JButtons
		// initialize all digit key buttons
		for (int i = 0; i <= 9; i++)
			keys[i] = new JButton(String.valueOf(i));
		// initialize all function key buttons
		keys[10] = new JButton("Cancel");
		keys[11] = new JButton("Correct");
		keys[12] = new JButton("Enter");
		keys[13] = new JPanel();
		keys[14] = new JButton("00");
		keys[15] = new JButton(".");

		// add buttons to keyPadJPanel panel
		// 7 8 9
		for (int i = 7; i <= 9; i++)
			numberKeysJPanel.add(keys[i]);
		// 4 5 6
		for (int i = 4; i <= 6; i++)
			numberKeysJPanel.add(keys[i]);
		// 1 2 3
		for (int i = 1; i <= 3; i++)
			numberKeysJPanel.add(keys[i]);

		// 0 . 00
		numberKeysJPanel.add(keys[0]);
		numberKeysJPanel.add(keys[15]);
		numberKeysJPanel.add(keys[14]);

		for (int i = 10; i <= 13; i++)
			functionKeysJPanel.add(keys[i]);
	} // end CalculatorFrame constructor

	public void calcBounds() {
		pack();
		setVisible(true);
		Rectangle client = getBounds();
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getBounds();
		int x = screen.width - client.width;
		int y = screen.height - client.height;
		setLocation(x, y);
	}
} // end class CalculatorFrame 