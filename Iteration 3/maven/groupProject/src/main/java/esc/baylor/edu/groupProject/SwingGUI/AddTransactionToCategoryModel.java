package esc.baylor.edu.groupProject.SwingGUI;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.Objects.Category;
import esc.baylor.edu.groupProject.Objects.TransactionLog;


/*
 * Table model for adding transactions to categories
 * 
 * @author Trae
 */
public class AddTransactionToCategoryModel extends AbstractTableModel {
	private static final Logger log = Logger.getLogger(AddTransactionToCategoryModel.class.getName());

	private final String [] columns = {"Title", "Amount", "Date", ""};
	private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private Category category;

	public AddTransactionToCategoryModel(Category category) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "AddTransactionToCategoryModel", category);
		log.exiting(AddTransactionToCategoryModel.class.getName(), "AddTransactionToCategoryModel");
		this.category = category;
	}

	@Override
	public String getColumnName(int columnIndex) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "getColumnName", columnIndex);
		log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnName", columns[columnIndex]);
		return columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		log.entering(AddTransactionToCategoryModel.class.getName(), "getRowCount");
		log.exiting(AddTransactionToCategoryModel.class.getName(), "getRowCount", TransactionTable.model.getTransactionLog().size());
		return TransactionTable.model.getTransactionLog().size();
	}

	@Override
	public int getColumnCount() {
		log.entering(AddTransactionToCategoryModel.class.getName(), "getColumnCount");
		log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnCount", columns.length);
		return columns.length;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "isCellEditable", new Object[] {row,column});
		log.exiting(AddTransactionToCategoryModel.class.getName(), "isCellEditable", column == 3);
		return column == 3;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "getValueAt", new Object[] {rowIndex,columnIndex});
		switch (columnIndex) {
		case 0: 
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getValueAt", TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getTitle());
			return TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getTitle();
		case 1: 
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getValueAt", TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getAmount());
			return TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getAmount();
		case 2: 
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getValueAt", new String(format.format(TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getDate())));
			return new String(format.format(TransactionTable.model.getTransactionLog().getTransaction(rowIndex).getDate()));
		case 3:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getValueAt", TransactionTable.model.getTransactionLog().isInCategory(rowIndex, category));
			return TransactionTable.model.getTransactionLog().isInCategory(rowIndex, category);
		default:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getValueAt", "Error");
			return "Error";
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "getColumnClass", column);
		switch (column) {
		case 0:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnClass", String.class);
			return String.class;
		case 1:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnClass", String.class);
			return String.class;
		case 2:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnClass", Integer.class);
			return Integer.class;
		case 3:
			log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnClass", Boolean.class);
			return Boolean.class;
		}
		log.exiting(AddTransactionToCategoryModel.class.getName(), "getColumnClass", null);
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		log.entering(AddTransactionToCategoryModel.class.getName(), "setValueAt", new Object[] {aValue,row,column});
		if (aValue instanceof Boolean && column == 3) {
			Boolean val = (Boolean) aValue;
			if(val) {
				category.addTransaction(TransactionTable.model.getTransactionLog().getTransaction(row));
			} else {
				category.removeTransaction(TransactionTable.model.getTransactionLog().getTransaction(row));
			}
			fireTableCellUpdated(row, column);
		}
		log.exiting(AddTransactionToCategoryModel.class.getName(), "setValueAt");
	}
};

