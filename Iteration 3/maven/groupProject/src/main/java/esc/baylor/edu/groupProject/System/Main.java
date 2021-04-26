package esc.baylor.edu.groupProject.System;

import esc.baylor.edu.groupProject.SwingGUI.Login;

public class Main {
	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {            	
            	Login g = new Login();
        		g.login();
            }
        });
	}
}
