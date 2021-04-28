package esc.baylor.edu.groupProject.SwingGUI;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;
import esc.baylor.edu.groupProject.TransactionObjects.Types;

/*
 * Table model for the Transaction display table
 * 
 * @author Trae
 */
public class TransactionTableModel extends AbstractTableModel {

	private String[] columnNames = {"Title", "Amount", "Date"};
	private static final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private Category filter;
	private TransactionLog tLog;

	public TransactionTableModel() {
		tLog = new TransactionLog();
		filter = null;
	}

	public TransactionLog getTransactionLog() {
		return tLog;
	}

	public void filterTable(Category category) {
		filter = category;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if(filter == null) return tLog.size();
		else return filter.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(filter != null) {
			switch (columnIndex) {
			case 0: 
				return filter.getTransaction(rowIndex).getTitle();
			case 1:
				int mul = 1;
				if(filter.getTransaction(rowIndex).getType() == Types.Expense) mul = -1;
				return mul * filter.getTransaction(rowIndex).getAmount();
			case 2:
				return new String(format.format(filter.getTransaction(rowIndex).getDate()));
			default:
				return null;
			}
		} else {
			switch (columnIndex) {
			case 0: 
				return tLog.getTransaction(rowIndex).getTitle();
			case 1: 
				int mul = 1;
				if(tLog.getTransaction(rowIndex).getType() == Types.Expense) mul = -1;
				return mul * tLog.getTransaction(rowIndex).getAmount();
			case 2: 
				return new String(format.format(tLog.getTransaction(rowIndex).getDate()));
			default: 
				return null;
			}
		}
	}
}
