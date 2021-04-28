package esc.baylor.edu.groupProject.System;

import java.util.logging.Logger;

import esc.baylor.edu.groupProject.SwingGUI.Login;

public class Main {
	final static Logger LOGGER = Logger.getLogger("logger");
	
	 
	private static final Logger log = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {	
		log.info("Starting BearBudget...");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {			
            public void run() {
            	Login g = new Login();
        		g.login();
            }
        });
	}
}
