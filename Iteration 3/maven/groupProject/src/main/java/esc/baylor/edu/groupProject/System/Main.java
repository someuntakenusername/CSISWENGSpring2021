package esc.baylor.edu.groupProject.System;

import java.awt.event.*;
import javax.swing.*;
import esc.baylor.edu.groupProject.SwingGUI.Login;

public class Main {
	public static void main(String[] args) {
		Login g = new Login();
		g.login();
      GUIManager ui = new GUIManager();
      ui.startGUI();
	}
}
