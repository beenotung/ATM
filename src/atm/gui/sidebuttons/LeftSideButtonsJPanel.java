package atm.gui.sidebuttons;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import atm.gui.MyGUISettings;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;

public class LeftSideButtonsJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector<JButton> buttons;

	public LeftSideButtonsJPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(MyGUISettings.getATMShellColor());

		Component topVerticalStrut = Box.createVerticalStrut(75);
		add(topVerticalStrut);

		JPanel centerPanel = new JPanel();
		add(centerPanel);
		centerPanel.setLayout(new GridLayout(4, 1, 0, 0));
		centerPanel.setBackground(MyGUISettings.getATMShellColor());

		Component bottomVerticalStrut = Box.createVerticalStrut(25);
		add(bottomVerticalStrut);

		buttons = new Vector<JButton>();
		for (int i = 0; i < 4; i++) {
			JButton button = new JButton(SideButtons.triangle_point_right_imageIcon);
			buttons.add(button);
			button.addActionListener(getActionListener(i));
			centerPanel.add(button);
		}
	}

	private ActionListener getActionListener(final int id) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SideButtons.click(id * 2 + 1);
			}
		};
	}
}
