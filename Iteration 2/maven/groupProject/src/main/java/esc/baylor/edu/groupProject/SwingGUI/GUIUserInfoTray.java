package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUserInfoTray extends JPanel{
	public JButton getUserSettingBtn() {
		return userSettingBtn;
	}
	public void setUserSettingBtn(JButton userSettingBtn) {
		this.userSettingBtn = userSettingBtn;
	}
	public JTextField getBalanceDisplay() {
		return balanceDisplay;
	}
	public void setBalanceDisplay(JTextField balanceDisplay) {
		this.balanceDisplay = balanceDisplay;
	}
	public JTextField getNetDisplay() {
		return netDisplay;
	}
	public void setNetDisplay(JTextField netDisplay) {
		this.netDisplay = netDisplay;
	}
	public GUIUserInfoTray() {
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //user settings button
        userSettingBtn = new JButton(new ImageIcon("./resources/ui/user_default_32res.png"));
        userSettingBtn.setMargin(new Insets(0,0,0,0));
        //take up the same height as the two text displays
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridheight = 2;
        add(userSettingBtn,c);
        
        //user balance text display
        balanceDisplay = new JTextField("Sample Text");
        balanceDisplay.setEditable(false);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(balanceDisplay,c);
        
        //user net growth text display
        netDisplay = new JTextField("Hello again");
        netDisplay.setEditable(false);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        add(netDisplay,c);
	}
	
	private JButton	userSettingBtn;
	private JTextField balanceDisplay;
	private JTextField netDisplay;
}
