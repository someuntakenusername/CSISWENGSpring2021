package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
                userTray.setLayout(new BorderLayout());
                userTray.add(new JButton("Button"), BorderLayout.LINE_START);
                userTray.add(new JTextField(),BorderLayout.PAGE_START);
                userTray.add(new JTextField(),BorderLayout.PAGE_END);
                
                content.add(userTray,BorderLayout.PAGE_START);
                
                JTabbedPane tabs = new JTabbedPane();
                tabs.addTab("Tab 1", new JPanel());
                tabs.addTab("Tab 2", new JPanel());
                tabs.addTab("Tab 3", new JPanel());
                
                content.add(tabs,BorderLayout.PAGE_END);
            	
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
