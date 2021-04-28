package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUserInfoTray extends JPanel{
	public void updateBalanceDisplay(String str) {
		balanceDisplay.setText(str);
	}
	
	public void updateNetDisplay(String str) {
		netDisplay.setText(str);
	}
	
	public GUIUserInfoTray() {
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //user settings button
        userSettingBtn = new JButton(new ImageIcon("./src/main/resources/ui/user_default_32res.png"));
        userSettingBtn.setMargin(new Insets(0,0,0,0));
        //take up the same height as the two text displays
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridheight = 2;
        add(userSettingBtn,c);
        
        //user balance text display
        balanceDisplay = new JTextField("Current Balance: " + TransactionTable.getTransactionLog().getCurrentBalance());
        balanceDisplay.setEditable(false);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(balanceDisplay,c);
        
        //user net growth text display
        netDisplay = new JTextField("");
        netDisplay.setEditable(false);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        add(netDisplay,c);
	}
	
	public void addActionListener(ActionListener l) {
		userSettingBtn.addActionListener(l);
	}
	
	private JButton	userSettingBtn;
	private JTextField balanceDisplay;
	private JTextField netDisplay;
}
