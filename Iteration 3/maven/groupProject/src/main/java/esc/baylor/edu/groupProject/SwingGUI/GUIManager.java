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

	/*
	 *	startGUI
	 *
	 *	Builds & displays the main window. Should be called only once on start-up
	 */
	public void startGUI() {
		//create new thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {            	
            	baseWindow = new JFrame("BearBudget");            	
            	baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Container content = baseWindow.getContentPane();
                content.setLayout(new BorderLayout());
                
                userTray = new GUIUserInfoTray();
                content.add(userTray,BorderLayout.PAGE_START);
                
                //add content panels here
                JTabbedPane tabs = new JTabbedPane();
                tabs.addTab("Overview", new OverviewPanel());
                tabs.addTab("Expenses", expensePane = new ExpensesPanel());
                
            	//TODO: have GUIManager handle menu bar action events
                menuBar = new JMenuBar();
                menuBar.add(new CategoryMenu(expensePane.list.model));
            	baseWindow.setJMenuBar(menuBar);
                
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
		//GUIManager mgr = new GUIManager();
		//mgr.startGUI();
	}
	
	private JMenuBar menuBar;
	private ExpensesPanel expensePane;
	private GUIUserInfoTray userTray;
	private JFrame baseWindow;
}
