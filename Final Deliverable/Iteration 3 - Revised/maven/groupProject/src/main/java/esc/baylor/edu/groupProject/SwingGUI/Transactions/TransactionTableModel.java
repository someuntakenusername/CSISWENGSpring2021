package esc.baylor.edu.groupProject.SwingGUI.Transactions;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.Objects.Category;
import esc.baylor.edu.groupProject.Objects.Transaction;
import esc.baylor.edu.groupProject.Objects.TransactionLog;
import esc.baylor.edu.groupProject.Objects.Types;

/**
 * Table model for the Transaction display table
 * 
 * @author Trae
 */
public class TransactionTableModel extends AbstractTableModel {
	private static final Logger log = Logger.getLogger(TransactionTableModel.class.getName());

	private String[] columnNames = {"Title", "Amount", "Date"};
	private static final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	private Category filter;
	private TransactionLog tLog;

	public TransactionTableModel() {
		log.entering(TransactionTableModel.class.getName(), "TransactionTableModel");
		tLog = new TransactionLog();
		filter = null;
		log.exiting(TransactionTableModel.class.getName(), "TransactionTableModel");
	}

	public TransactionLog getTransactionLog() {
		log.entering(TransactionTableModel.class.getName(), "getTransactionLog");
		log.exiting(TransactionTableModel.class.getName(), "getTransactionLog",tLog);
		return tLog;
	}

	public void filterTable(Category category) {
		log.entering(TransactionTableModel.class.getName(), "filterTable", category);
		filter = category;
		fireTableDataChanged();
		log.exiting(TransactionTableModel.class.getName(), "filterTable");
	}
	
	public Transaction getTransaction(int rowIndex) {
		log.entering(TransactionTableModel.class.getName(), "getTransaction", rowIndex);
		if(filter != null) {
			log.exiting(TransactionTableModel.class.getName(), "getTransaction", filter.getTransaction(rowIndex));
			return filter.getTransaction(rowIndex);
		} else {
			log.exiting(TransactionTableModel.class.getName(), "getTransaction", tLog.getTransaction(rowIndex));
			return tLog.getTransaction(rowIndex);
		}
	}

	@Override
	public int getRowCount() {
		log.entering(TransactionTableModel.class.getName(), "getRowCount");
		if(filter == null) {
			log.exiting(TransactionTableModel.class.getName(), "getRowCount", tLog.size());
			return tLog.size();
		}
		else {
			log.exiting(TransactionTableModel.class.getName(), "getRowCount", filter.size());
			return filter.size();
		}
	}

	@Override
	public int getColumnCount() {
		log.entering(TransactionTableModel.class.getName(), "getColumnCount");
		log.exiting(TransactionTableModel.class.getName(), "getColumnCount", columnNames.length);
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		log.entering(TransactionTableModel.class.getName(), "getColumnName", columnIndex);
		log.exiting(TransactionTableModel.class.getName(), "getColumnName", columnNames[columnIndex]);
		return columnNames[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		log.entering(TransactionTableModel.class.getName(), "isCellEditable", new Object[] {rowIndex,columnIndex});
		log.exiting(TransactionTableModel.class.getName(), "isCellEditable", false);
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		log.entering(TransactionTableModel.class.getName(), "getValueAt", new Object[] {rowIndex,columnIndex});
		if(filter != null) {
			switch (columnIndex) {
			case 0: 
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", filter.getTransaction(rowIndex).getTitle());
				return filter.getTransaction(rowIndex).getTitle();
			case 1:
				int mul = 1;
				if(filter.getTransaction(rowIndex).getType() == Types.Expense) mul = -1;
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", mul * filter.getTransaction(rowIndex).getAmount());
				return mul * filter.getTransaction(rowIndex).getAmount();
			case 2:
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", new String(format.format(filter.getTransaction(rowIndex).getDate())));
				return new String(format.format(filter.getTransaction(rowIndex).getDate()));
			default:
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", null);
				return null;
			}
		} else {
			switch (columnIndex) {
			case 0: 
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", tLog.getTransaction(rowIndex).getTitle());
				return tLog.getTransaction(rowIndex).getTitle();
			case 1: 
				int mul = 1;
				if(tLog.getTransaction(rowIndex).getType() == Types.Expense) mul = -1;
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", mul * tLog.getTransaction(rowIndex).getAmount());
				return mul * tLog.getTransaction(rowIndex).getAmount();
			case 2: 
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", new String(format.format(tLog.getTransaction(rowIndex).getDate())));
				return new String(format.format(tLog.getTransaction(rowIndex).getDate()));
			default: 
				log.exiting(TransactionTableModel.class.getName(), "getValueAt", null);
				return null;
			}
		}
	}
}
