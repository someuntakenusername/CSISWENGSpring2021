package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFrame extends JFrame implements ActionListener {
	private JPanel parent;
	private JPanel panel;
	private JTextField title, amount;
	private JCheckBox recurring;
	private JOptionPane type;
	private JButton confirm, cancel;

	public AddFrame(JPanel parent) {
		super("Add New Transaction");
		this.parent = parent;
		//Setup 4x2 panel
		panel = new JPanel(new GridLayout(5, 2));

		//Setup title and amount fields
		title = new JTextField(50);
		amount = new JTextField(20);

		//Recurrence checkbox
		recurring = new JCheckBox();

		//Buttons
		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		panel.add(new JLabel("Title"));
		panel.add(title);
		panel.add(new JLabel("Amount"));
		panel.add(amount);
		panel.add(new JLabel("Date"));
		panel.add(new JLabel("Date"));
		panel.add(new JLabel("Recurring"));
		panel.add(recurring);
		panel.add(confirm);
		panel.add(cancel);

		this.setContentPane(panel);
		this.setSize(new Dimension(300, 200));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked.equals(confirm)) {
			
		}  else if (clicked.equals(cancel)) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
}
