package esc.baylor.edu.groupProject.SwingGUI.General;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import esc.baylor.edu.groupProject.Objects.Category;
import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;

/**
 * Constructs the Expenses panel of the GUI
 * 
 * @author Will, Trae
 *
 */
public class ExpensesPanel extends JPanel{
	private static final Logger log = Logger.getLogger(ExpensesPanel.class.getName());
	
	/**
	 * Constructs the expenses panel with the TransactionTable and buttons to modify the Transactions
	 */
	public ExpensesPanel() {
		log.entering(ExpensesPanel.class.getName(), "ExpensesPanel");
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
		log.exiting(ExpensesPanel.class.getName(), "ExpensesPanel");
	}
	private TransactionTable list;
	private JComboBox<Object> filter;
	private JButton setSave, addBtn, editBtn, deleteBtn;
	//action commands
	public static final String CMD_ADD_EXPENSE = "CMD_ADD_EXPENSE",
								CMD_EDIT_EXPENSE = "CMD_EDIT_EXPENSE",
								CMD_DELETE_EXPENSE = "CMD_DELETE_EXPENSE";
}
