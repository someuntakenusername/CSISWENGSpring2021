package esc.baylor.edu.groupProject.System;

import java.util.logging.Logger;

import esc.baylor.edu.groupProject.SwingGUI.Login;

public class Main {
	final static Logger LOGGER = Logger.getLogger("logger");
	
	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {        
            	Login g = new Login();
        		g.login();
            }
        });
	}
}
