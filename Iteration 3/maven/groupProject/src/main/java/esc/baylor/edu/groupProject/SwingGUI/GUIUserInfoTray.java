package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUserInfoTray extends JPanel{
	private static final Logger log = Logger.getLogger(GUIUserInfoTray.class.getName());
	public void updateBalanceDisplay(String str) {
		log.entering(GUIUserInfoTray.class.getName(), "updateBalanceDisplay", str);
		balanceDisplay.setText(str);
		log.exiting(GUIUserInfoTray.class.getName(), "updateBalanceDisplay");
	}
	
	public void updateNetDisplay(String str) {
		log.entering(GUIUserInfoTray.class.getName(), "updateNetDisplay", str);
		netDisplay.setText(str);
		log.exiting(GUIUserInfoTray.class.getName(), "updateNetDisplay");
	}
	
	public GUIUserInfoTray() {
		log.entering(GUIUserInfoTray.class.getName(), "GUIUserInfoTray");
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
        log.exiting(GUIUserInfoTray.class.getName(), "GUIUserInfoTray");
	}
	
	public void addActionListener(ActionListener l) {
		log.entering(GUIUserInfoTray.class.getName(), "addActionListener", l);
		userSettingBtn.addActionListener(l);
		log.exiting(GUIUserInfoTray.class.getName(), "addActionListener");
	}
	
	private JButton	userSettingBtn;
	private JTextField balanceDisplay;
	private JTextField netDisplay;
}
