package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;

public class CategoryTable extends JPanel{
	
	private TransactionLog tLog;
	private CategoryModel model;
	private JTable table;
	
	public CategoryTable(TransactionLog tLog) {
		super();
		this.tLog = tLog;
		model = new CategoryModel();
		
		//Sets up CategoryList and model
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		setSize(100, 100);
	}
	
	public TransactionLog getTransactionLog() {
		return tLog;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void update() {
		model.fireTableDataChanged();
	}
	
	class CategoryModel extends AbstractTableModel {

		private String [] columnNames = {"Category", "Notes"};
		
		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}
		
		@Override
		public int getRowCount() {
			return tLog.categoryCount();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0: 
				return tLog.getCategory(rowIndex).getName();
			case 1: 
				return tLog.getCategory(rowIndex).getNotes();
			default: return "Error";
			}
		}		
	}
}