package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class TransactionFrame extends JFrame implements ActionListener {
	private TransactionTableModel model;
	private JPanel panel;
	private JTextField title, amount, recurrence;
	private JCheckBox recurring;
	private JComboBox<Object> type, date;
	private JButton confirm, cancel;
	private boolean recur = false;
	private Date selectedDate = null;
	private final Object [] types = {"----", Types.Expense, Types.Income};
	private int rowIndex;
	private final SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd, yyyy");

	
	public TransactionFrame(TransactionTableModel model, int rowIndex) {
		super("Add New Transaction");
		this.model = model;
		this.rowIndex = rowIndex;
		//Setup 4x2 panel
		panel = new JPanel(new GridLayout(7, 2));
		
		//Type options
		type = new JComboBox<Object>(types);
		panel.add(new JLabel("Type"));
		panel.add(type);

		//Setup title and amount fields
		title = new JTextField(50);
		amount = new JTextField(20);
		recurrence = new JTextField(5);
		recurrence.setEditable(false);
		//Limit amount to numbers
		recurrence.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if((ke.getKeyChar() < '0' || ke.getKeyChar() > '9') && ke.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
					recurrence.setText(recurrence.getText().substring(0, recurrence.getText().length()));
				}
			}
		});
		
		//Date comboxbox
		date = new JComboBox<Object>();
		date.addItem("Select Date");
		date.addActionListener(this);

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
		
		if(rowIndex != -1) init();
		
		panel.add(new JLabel("Title"));
		panel.add(title);
		panel.add(new JLabel("Amount"));
		panel.add(amount);
		panel.add(new JLabel("Date"));
		panel.add(date);
		date.setActionCommand("Date");
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
	
	/*
	 * Initializes field values if editing an existing transaction
	 */
	private void init() {
		type.setSelectedItem(model.getTransactionLog().getTransaction(rowIndex).getType());
		title.setText(model.getTransactionLog().getTransaction(rowIndex).getTitle());
		amount.setText(model.getTransactionLog().getTransaction(rowIndex).getAmount().toString());
		date.removeAllItems();
		selectedDate = model.getTransactionLog().getTransaction(rowIndex).getDate();
		date.addItem(sdf.format(model.getTransactionLog().getTransaction(rowIndex).getDate()));
		if(model.getTransactionLog().getTransaction(rowIndex).isRecurring()) {
			recurring.setSelected(true);
			recurrence.setText(Integer.toString(model.getTransactionLog().getTransaction(rowIndex).getRecur()));
			recurrence.setEditable(true);
		} else {
			recurring.setSelected(false);
			recurrence.setText(null);
			recurrence.setEditable(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			if(type.getSelectedIndex() == 0 || title.getText().equals(null) || selectedDate == null 
					|| amount.getText().equals(null) || (recur && recurrence.getText().equals(null))) {
				JOptionPane.showMessageDialog(this, "Missing Information", "Warning", JOptionPane.ERROR_MESSAGE);
			} else {
				int rec = -1;
				double am = 0.00;
				if(recur) {
					rec = Integer.parseInt(recurrence.getText());
				}
				try {
					am = Double.parseDouble(amount.getText());
					if(rowIndex == -1) {
						model.getTransactionLog().addTransaction((Types)type.getSelectedItem(), title.getText(), selectedDate, am, rec);
					} else {
						model.getTransactionLog().editTransaction(rowIndex, (Types)type.getSelectedItem(), title.getText(), selectedDate, am, rec);
					}
					model.fireTableDataChanged();
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				} catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "Double Formatted Incorrectly", "Warning", JOptionPane.ERROR_MESSAGE);
				}
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
		} else if(e.getActionCommand().equals("Date")) {
			String d = new DatePicker(this).setPickedDate();
			if(!d.equals("")) {
				selectedDate = new Date();
				try {
					selectedDate = sdf.parse(d);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				date.removeAllItems();
				date.addItem(d);
			}
		}
	}
}