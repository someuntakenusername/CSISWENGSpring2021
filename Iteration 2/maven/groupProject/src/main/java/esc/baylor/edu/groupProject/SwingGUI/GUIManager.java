package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIManager {	
	public static class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}		
	}
	
	public void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	baseWindow = new JFrame("BearBudget");            	
            	baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Container content = baseWindow.getContentPane();
                content.setLayout(new BorderLayout());
                
                JPanel userTray = new JPanel();
                userTray.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                
                c.weightx = 0;
                c.gridx = 0;
                c.gridy = 0;
                c.ipadx = 10;
                c.ipady = 10;
                c.gridheight = 2;
                userTray.add(new JButton("Button"),c);
                
                c.weightx = 0.5;
                c.gridx = 1;
                c.gridy = 0;
                c.ipadx = 0;
                c.ipady = 0;
                c.gridheight = 1;
                userTray.add(new JTextField(),c);
                
                c.weightx = 0.5;
                c.gridx = 1;
                c.gridy = 1;
                c.ipadx = 0;
                c.ipady = 0;
                userTray.add(new JTextField(),c);
                
                content.add(userTray,BorderLayout.PAGE_START);
                
                JTabbedPane tabs = new JTabbedPane();
                tabs.addTab("Tab 1", new JPanel());
                tabs.addTab("Tab 2", new JPanel());
                tabs.addTab("Tab 3", new JPanel());
                
                content.add(tabs,BorderLayout.CENTER);
            	
            	baseWindow.pack();
            	baseWindow.setVisible(true);
            }
        });
	}
	
	public static void main(String args[]) {
		GUIManager mgr = new GUIManager();
		mgr.startGUI();
	}
	
	private JFrame baseWindow;
}
