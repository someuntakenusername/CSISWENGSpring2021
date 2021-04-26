package esc.baylor.edu.groupProject.SwingGUI;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;

public class TransactionTableModel extends AbstractTableModel {

	private String[] columnNames = {"Title", "Amount", "Date"};
	private TransactionLog tLog;
	private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");

	public TransactionTableModel() {
		tLog = new TransactionLog();
	}
	
	public TransactionLog getTransactionLog() {
		return tLog;
	}

	@Override
	public int getRowCount() {
		return tLog.size();
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
		switch (columnIndex) {
		case 0: 
			return tLog.getTransaction(rowIndex).getTitle();
		case 1: 
			return tLog.getTransaction(rowIndex).getAmount();
		case 2: 
			return new String(format.format(tLog.getTransaction(rowIndex).getDate()));
		default: return "Error";
		}
	}	
}
