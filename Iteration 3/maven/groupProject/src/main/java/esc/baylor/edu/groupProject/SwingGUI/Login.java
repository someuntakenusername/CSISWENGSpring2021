package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener {
	
	JFrame frame;
	JPanel panel;
	JLabel passwordLabel;
	JButton loginButton;
	JButton registerButton;
	JLabel failure;
	JTextField usernameText;
	JTextField passwordText;
	
	public void login() {
		
		frame = new JFrame();
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("Username:");
		label.setBounds(10, 20, 80, 25);
		panel.add(label);
		
		usernameText = new JTextField();
		usernameText.setBounds(100, 20, 165, 25);
		panel.add(usernameText);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JTextField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		loginButton.addActionListener(this);
		
		registerButton = new JButton("Create Account");
		registerButton.setBounds(100, 80, 165, 25);
		panel.add(registerButton);
		registerButton.addActionListener(this);
		
		failure = new JLabel("");
		failure.setBounds(10, 110, 300, 25);
		panel.add(failure);
		
		frame.setVisible(true);
		
	}
	
	public static void storeCredentials(String username, String password) {
		
		// Store login
		
	}
	
	public static Boolean checkCredentials(String username, String password) {
		
		// Check login
		
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) {
			if (checkCredentials(usernameText.getText(), passwordText.getText())) {
				frame.dispose();
				GUIManager ui = new GUIManager();
				ui.startGUI();
			} else {
				failure.setText("Invalid login");
			}
		} else if (e.getSource() == registerButton) {
			storeCredentials(usernameText.getText(), passwordText.getText());
			frame.dispose();
			GUIManager ui = new GUIManager();
			ui.startGUI();
		}
		
	}
}