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
	//all UI components should use this as the event handler
	public static class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}		
	}
	
	/*
	 *	startGUI
	 *
	 *	Builds & displays the main window. Should be called only once on start-up
	 */
	public void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	baseWindow = new JFrame("BearBudget");            	
            	baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Container content = baseWindow.getContentPane();
                content.setLayout(new BorderLayout());
                
                userTray = new GUIMUserInfoTray();
                content.add(userTray,BorderLayout.PAGE_START);
                
                //add content panels here
                JTabbedPane tabs = new JTabbedPane();
                tabs.addTab("Tab 1", new ListDisplay());
                tabs.addTab("Tab 2", new JPanel());
                tabs.addTab("Tab 3", new JPanel());
                
                content.add(tabs,BorderLayout.CENTER);
            	
            	baseWindow.setSize(640, 480);
            	baseWindow.setVisible(true);
            }
        });
	}
	
	/*
	 * 	tester
	 */
	public static void main(String args[]) {
		GUIManager mgr = new GUIManager();
		mgr.startGUI();
	}
	
	private GUIMUserInfoTray userTray;
	private JFrame baseWindow;
}
