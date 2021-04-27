package esc.baylor.edu.groupProject.SwingGUI;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;


/*
 * Table model for adding transactions to categories
 * 
 * @author Trae
 */
public class AddTransactionToCategoryModel extends AbstractTableModel {

	private final String [] columns = {"Title", "Amount", "Date", ""};
	private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private Category category;

	public AddTransactionToCategoryModel(Category category) {
		this.category = category;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		return TransactionTable.model.getTransactionLog().size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: 
			return TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getTitle();
		case 1: 
			return TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getAmount();
		case 2: 
			return new String(format.format(TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getDate()));
		case 3:
			return TransactionTable.model.getTransactionLog().isInCategory(rowIndex, category);
		default: return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		case 3:
			return Boolean.class;
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && column == 3) {
			Boolean val = (Boolean) aValue;
			if(val) {
				category.addTransaction(TransactionTable.model.getTransactionLog().getTransaction(row));
			} else {
				category.addTransaction(TransactionTable.model.getTransactionLog().getTransaction(row));
			}
			fireTableCellUpdated(row, column);
		}
	}
};

