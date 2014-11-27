package atm.gui.mainscreen;

import javax.swing.JPanel;

import atm.gui.MonitorJFrame;
import atm.gui.MyGUISettings;
import atm.gui.sidebuttons.SideButtons;
import atm.utils.MyImages;

import java.awt.GridLayout;

import java.awt.Button;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JLabel;

public class ViewBalanceJPanel extends JPanel {
	public static final String STRING_MAIN_MENU = "Main Menu";
	public static final String STRING_TAKE_CARD = "Take Card";

	public static final String[] commands = { "", "", "", "", "", "", STRING_MAIN_MENU, STRING_TAKE_CARD };

	public ViewBalanceJPanel() {
		setBackground(MyGUISettings.getATMScreenBackGroundColor());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		add(horizontalBox);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);

		JLabel label = new JLabel(MyImages.viewBalance);
		label.setAlignmentX(0.5f);
		horizontalBox.add(label);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		add(contentPanel);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel infoPanel1 = new JPanel();
		contentPanel.add(infoPanel1);
		infoPanel1.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JPanel strucPanel = new JPanel();
		contentPanel.add(strucPanel);
		strucPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());
		strucPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel infoPanel2 = new JPanel();
		strucPanel.add(infoPanel2);
		infoPanel2.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		JPanel menuPanel = new JPanel();
		strucPanel.add(menuPanel);
		menuPanel.setLayout(new GridLayout(1, 2, 0, 0));
		menuPanel.setBackground(MyGUISettings.getATMScreenBackGroundColor());

		Button button_1 = new Button(STRING_MAIN_MENU);
		menuPanel.add(button_1);
		button_1.setFont(new Font("Arial", Font.PLAIN, 26));

		Button button_2 = new Button(STRING_TAKE_CARD);
		menuPanel.add(button_2);
		button_2.setFont(new Font("Arial", Font.PLAIN, 26));

		Component verticalStrut_1 = Box.createVerticalStrut(25);
		add(verticalStrut_1);

	}

	public static void showMe() {
		MonitorJFrame.STATE = MainScreenCardJPanel.STRING_VIEW_BALANCE;
		SideButtons.commands = ViewBalanceJPanel.commands;
		MainScreenCardJPanel.switchToCardStatic(MainScreenCardJPanel.STRING_VIEW_BALANCE);
	}
}
