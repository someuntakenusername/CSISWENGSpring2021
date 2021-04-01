package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExpensesPanel extends JPanel implements ActionListener{
	public ExpensesPanel() {
		setLayout(new BorderLayout());
		
		list = new ListDisplay();
		add(list, BorderLayout.CENTER);
		
		JPanel subpanel = new JPanel();
		
		addBtn = new JButton("Add Expense");
		addBtn.setActionCommand(CMD_ADD_EXPENSE);
		addBtn.addActionListener(this);
		subpanel.add(addBtn);
		
		editBtn = new JButton("Edit Expense");
		editBtn.setActionCommand(CMD_EDIT_EXPENSE);
		subpanel.add(editBtn);
		
		deleteBtn = new JButton("Delete Expense");
		deleteBtn.setActionCommand(CMD_DELETE_EXPENSE);
		subpanel.add(deleteBtn);
		
		add(subpanel, BorderLayout.PAGE_END);
	}
	
	public void showAddExpenseDialog() {
		AddFrame add = new AddFrame(this);
	}
	
	/*
	 * 	addActionListener
	 * 
	 * 	relays action listeners to child elements
	 */
	public void addActionListener(ActionListener l) {
		addBtn.addActionListener(l);
		editBtn.addActionListener(l);
		deleteBtn.addActionListener(l);
	}
	
	public ListDisplay list;
	private JButton addBtn, editBtn, deleteBtn;
	//action commands
	public static final String CMD_ADD_EXPENSE = "CMD_ADD_EXPENSE",
								CMD_EDIT_EXPENSE = "CND_EDIT_EXPENSE",
								CMD_DELETE_EXPENSE = "CMD_DELETE_EXPENSE";
	@Override
	public void actionPerformed(ActionEvent e) {
		new AddFrame(this);		
	}
}
