package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import esc.baylor.edu.groupProject.Objects.Category;

public class ExpensesPanel extends JPanel{
	public ExpensesPanel() {
		setLayout(new BorderLayout());
		
		filter = new JComboBox<Object>();
		
		list = new TransactionTable(filter);
		add(list, BorderLayout.CENTER);
		
		filter.addActionListener(list);
		filter.setActionCommand("filter");
		
		JPanel subpanel = new JPanel();
		
		setSave = new JButton("Set Savings");
		setSave.setActionCommand("Set_Save");
		setSave.addActionListener(list);
		subpanel.add(setSave);
		
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
		
		subpanel.add(filter);
		
		add(subpanel, BorderLayout.PAGE_END);
	}
	private TransactionTable list;
	private JComboBox filter;
	private JButton setSave, addBtn, editBtn, deleteBtn;
	//action commands
	public static final String CMD_ADD_EXPENSE = "CMD_ADD_EXPENSE",
								CMD_EDIT_EXPENSE = "CMD_EDIT_EXPENSE",
								CMD_DELETE_EXPENSE = "CMD_DELETE_EXPENSE";
}
