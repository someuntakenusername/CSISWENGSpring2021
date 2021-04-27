package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton registerButton;
	private JLabel failure;
	private JTextField usernameText, passwordText;

	public static User user;

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

	public static Boolean fileExists(User u) {

		Boolean isFile = false;
		String filename = null;

		filename = u.findFilename();
		File f = new File(filename);
		if (f.isFile()) {
			isFile = true;
		}

		return isFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		User user = new User(usernameText.getText(), passwordText.getText());
		if (e.getSource() == loginButton) {
			if (fileExists(user)) {
				//load
				frame.dispose();
				Login.user = user;
				GUIManager ui = new GUIManager();
				ui.startGUI();
			} else {
				failure.setText("Invalid credentials");
			}
		} else if (e.getSource() == registerButton) {
			//create file for this new person
			if(fileExists(user)) {
				JOptionPane.showMessageDialog(new JFrame("Error"), "Please choose a different username", "Warning", JOptionPane.ERROR_MESSAGE);
			} else {
				Login.user = user;
				frame.dispose();
				GUIManager ui = new GUIManager();
				ui.startGUI();
			}
		}
	}
}