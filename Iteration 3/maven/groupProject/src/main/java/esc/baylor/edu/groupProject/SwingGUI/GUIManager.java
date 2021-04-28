package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.*;

import esc.baylor.edu.groupProject.TransactionObjects.Transaction;

public class GUIManager {
	private static final Logger log = Logger.getLogger(GUIManager.class.getName());

	/*
	 *	startGUI
	 *
	 *	Builds & displays the main window. Should be called only once on start-up
	 */
	public void startGUI() {
		log.entering(GUIManager.class.getName(), "startGUI");
		//create new thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {            	
            	baseWindow = new JFrame("BearBudget");            	
            	baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Container content = baseWindow.getContentPane();
                content.setLayout(new BorderLayout());
                
                //add content panels here
                JTabbedPane tabs = new JTabbedPane();
                tabs.addTab("Overview", new OverviewPanel());
                tabs.addTab("Expenses", expensePane = new ExpensesPanel());
                
                userTray = new GUIUserInfoTray();
                content.add(userTray,BorderLayout.PAGE_START);
                
            	//TODO: have GUIManager handle menu bar action events
                menuBar = new JMenuBar();
                menuBar.add(new FileMenu());
                menuBar.add(new CategoryMenu());
            	baseWindow.setJMenuBar(menuBar);
                
                content.add(tabs,BorderLayout.CENTER);
            	
            	baseWindow.setSize(640, 480);
            	baseWindow.setVisible(true);
            }
        });
		log.exiting(GUIManager.class.getName(), "startGUI");
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
