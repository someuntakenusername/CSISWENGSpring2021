package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExpensesPanel extends JPanel{
	public ExpensesPanel() {
		setLayout(new BorderLayout());
		
		list = new TransactionTable();
		add(list, BorderLayout.CENTER);
		
		JPanel subpanel = new JPanel();
		
		addBtn = new JButton("Add Expense");
		addBtn.setActionCommand(CMD_ADD_EXPENSE);
		addBtn.addActionListener(list);
		subpanel.add(addBtn);
		
		editBtn = new JButton("Edit Expense");
		editBtn.setActionCommand(CMD_EDIT_EXPENSE);
		editBtn.addActionListener(list);
		subpanel.add(editBtn);
		
		deleteBtn = new JButton("Delete Expense");
		deleteBtn.addActionListener(list);
		deleteBtn.setActionCommand(CMD_DELETE_EXPENSE);
		subpanel.add(deleteBtn);
		
		add(subpanel, BorderLayout.PAGE_END);
	}
	
	public TransactionTable list;
	private JButton addBtn, editBtn, deleteBtn;
	//action commands
	public static final String CMD_ADD_EXPENSE = "CMD_ADD_EXPENSE",
								CMD_EDIT_EXPENSE = "CMD_EDIT_EXPENSE",
								CMD_DELETE_EXPENSE = "CMD_DELETE_EXPENSE";
}
