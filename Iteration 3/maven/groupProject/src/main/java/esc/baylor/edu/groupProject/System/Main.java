package esc.baylor.edu.groupProject.System;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * The main class the initializes the software
 * 
 * @author BearBudget Team
 */
import esc.baylor.edu.groupProject.SwingGUI.General.Login;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) throws SecurityException, IOException {	
		Logger.getLogger("").addHandler(new FileHandler("log.txt"));
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {			
            public void run() {
            	Login g = new Login();
        		g.login();
            }
        });
	}
}
