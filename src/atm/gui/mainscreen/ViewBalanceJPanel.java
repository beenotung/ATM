package atm.gui.mainscreen;

import javax.swing.JPanel;

import atm.gui.MyGUISettings;
import atm.gui.sidebuttons.SideButtons;
import atm.utils.MyImages;

import java.awt.GridLayout;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Color;

public class ViewBalanceJPanel extends JPanel {
	private static final String[] commands = null;

	public ViewBalanceJPanel() {
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		add(topPanel);
		topPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		topPanel.add(horizontalGlue);
		
		JLabel label = new JLabel(MyImages.banner);
		label.setAlignmentX(0.5f);
		topPanel.add(label);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		topPanel.add(horizontalGlue_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		add(panel);
		
		Button button = new Button("Main Menu");
		panel.add(button);
		
		Component verticalStrut_1 = Box.createVerticalStrut(25);
		add(verticalStrut_1);

	}

	public static void showMe() {
		SideButtons.commands = ViewBalanceJPanel.commands;
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_VIEW_BALANCE);
	}
}
