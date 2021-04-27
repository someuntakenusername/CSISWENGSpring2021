package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveFrame extends JFrame implements ActionListener {
	
	private TransactionTableModel model;
	private JTextField amount;
	private JButton cancel, confirm;
	
	public SaveFrame(TransactionTableModel model) {
		super("Set Current Savings");
		this.model = model;
		
		JPanel panel = new JPanel(new GridLayout(2, 2));
		
		amount = new JTextField(20);
		panel.add(new JLabel("Current Savings:"));
		panel.add(amount);
		
		cancel = new JButton("Cancel");
		cancel .setActionCommand("Cancel");
		cancel.addActionListener(this);
		panel.add(cancel);

		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);
		panel.add(confirm);
		
		this.pack();
		this.setContentPane(panel);
		this.setSize(new Dimension(300, 150));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			if(amount.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "Must put an amount", "Warning", JOptionPane.ERROR_MESSAGE);	
			} else {
				model.getTransactionLog().setCurrentSavings(Double.parseDouble(amount.getText()));
				quit();
			}
		} else if(e.getActionCommand().equals("Cancel")) {
			quit();
		}
	}
	
	private void quit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
