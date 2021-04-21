package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import esc.baylor.edu.groupProject.TransactionObjects.Types;

public class AddFrame extends JFrame implements ActionListener {
	private ListDisplay parent;
	private JPanel panel;
	private JTextField title, amount, recurrence;
	private JCheckBox recurring;
	private JComboBox<Object> type;
	private JButton confirm, cancel;
	private boolean recur = false;

	public AddFrame(ListDisplay parent) {
		super("Add New Transaction");
		this.parent = parent;
		//Setup 4x2 panel
		panel = new JPanel(new GridLayout(7, 2));
		
		//Type options
		Object [] options = {"----", Types.Expense, Types.Income};
		type = new JComboBox<Object>(options);
		panel.add(new JLabel("Type"));
		panel.add(type);

		//Setup title and amount fields
		title = new JTextField(50);
		amount = new JTextField(20);
		recurrence = new JTextField(5);
		recurrence.setEditable(false);
		//Limit amount to numbers
		amount.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == '.' && !amount.getText().contains(".")) {
					amount.setEditable(true);
				}  else if(ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					amount.setEditable(true);
				} else {
					amount.setEditable(false);
				}
			}
		});
		recurrence.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					amount.setEditable(true);
				} else {
					amount.setEditable(false);
				}
			}
		});

		//Recurrence checkbox
		recurring = new JCheckBox();
		recurring.setActionCommand("Recur");
		recurring.addActionListener(this);

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
		panel.add(new JLabel("Recurrence"));
		panel.add(recurrence);
		panel.add(confirm);
		panel.add(cancel);

		this.setContentPane(panel);
		this.setSize(new Dimension(300, 200));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			if(type.getSelectedIndex() == 0 || title.getText().equals("") || /*Date*/ amount.getText().equals("")
					|| (recur && recurrence.getText().equals(null))) {
				JOptionPane.showMessageDialog(this, "Missing Information", "Warning", JOptionPane.ERROR_MESSAGE);
			} else {
				int rec = -1;
				if(recur = true) {
					rec = Integer.parseInt(recurrence.getText());
				}
				parent.tLog.addTransaction((Types)type.getSelectedItem(), title.getText(), new Date(), Double.parseDouble(amount.getText()), rec);
				parent.model.fireTableDataChanged();
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}  else if (e.getActionCommand().equals("Cancel")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if(e.getActionCommand().equals("Recur")) {
			if(recur) {
				recur = false;
				recurrence.setText(null);
				recurrence.setEditable(false);
			} else if(!recur) {
				recur = true;
				recurrence.setText(null);
				recurrence.setEditable(true);
			}
		}
	}
}
