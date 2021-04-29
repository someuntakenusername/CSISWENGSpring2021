package esc.baylor.edu.groupProject.System;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import esc.baylor.edu.groupProject.SwingGUI.Login;

/**
 *	<b>Main</b>
 *
 *	The entry class of the program.
 *
 *	@author will
 */
public class Main {
	private static final Logger log = Logger.getLogger(Main.class.getName());
	
	/**
	 * <b>main</b>
	 * 
	 * <i>The</i> main method of the program.
	 * @param args
	 * @throws SecurityException
	 * @throws IOException
	 */
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
