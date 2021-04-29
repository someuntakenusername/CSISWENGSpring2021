package esc.baylor.edu.groupProject.SwingGUI.Category;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.Objects.Category;
import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;


/**
 * Table model for adding transactions to categories
 * 
 * @author Trae
 */
public class AddTransactionToCategoryModel extends AbstractTableModel {

	private static final long serialVersionUID = -3799754474000376148L;
	private final String [] columns = {"Title", "Amount", "Date", ""};
	private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private Category category;

	/**
	 * Initializes the TableModel to allow the user to add Transactions to a Category
	 * 
	 * @param category The Category the user wants to add Transactions to
	 */
	public AddTransactionToCategoryModel(Category category) {
		this.category = category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount() {
		return TransactionTable.getTransactionLog().size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 3;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: 
			return TransactionTable.getTransactionLog().getTransaction(rowIndex).getTitle();
		case 1: 
			return TransactionTable.getTransactionLog().getTransaction(rowIndex).getAmount();
		case 2: 
			return new String(format.format(TransactionTable.getTransactionLog().getTransaction(rowIndex).getDate()));
		case 3:
			return TransactionTable.getTransactionLog().isInCategory(rowIndex, category);
		default:
			return "Error";
		}
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && column == 3) {
			Boolean val = (Boolean) aValue;
			if(val) {
				category.addTransaction(TransactionTable.getTransactionLog().getTransaction(row));
			} else {
				category.removeTransaction(TransactionTable.getTransactionLog().getTransaction(row));
			}
			fireTableCellUpdated(row, column);
		}
	}
};

