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
public class TranToCatTableModel extends AbstractTableModel {

	private final String [] columns = {"Title", "Amount", "Date", ""};
	private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private TransactionLog tLog;
	private Category category;

	public TranToCatTableModel(TransactionLog tLog, Category category) {
		this.tLog = tLog;
		this.category = category;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		return tLog.size();
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
			return tLog.getTransaction(rowIndex).getTitle();
		case 1: 
			return tLog.getTransaction(rowIndex).getAmount();
		case 2: 
			return new String(format.format(tLog.getTransaction(rowIndex).getDate()));
		case 3:
			return tLog.getTransaction(rowIndex).isInCategory(category);
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
				tLog.getTransaction(row).addCategory(category);
				category.addTransaction(tLog.getTransaction(row));
			} else {
				tLog.getTransaction(row).removeCategory(category);
				category.addTransaction(tLog.getTransaction(row));
			}
			fireTableCellUpdated(row, column);
		}
	}
};

