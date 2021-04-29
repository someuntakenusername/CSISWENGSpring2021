package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.Objects.TransactionLog;

public class CategoryTable extends JPanel{
	private static final Logger log = Logger.getLogger(CategoryTable.class.getName());
	
	private CategoryModel model;
	private JTable table;
	
	public CategoryTable() {
		super();
		log.entering(CategoryTable.class.getName(), "CategoryTable");
		model = new CategoryModel();
		
		//Sets up CategoryList and model
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		setSize(100, 100);
		log.exiting(CategoryTable.class.getName(), "CategoryTable");
	}
	
	public JTable getTable() {
		log.entering(CategoryTable.class.getName(), "getTable");
		log.exiting(CategoryTable.class.getName(), "getTable",table);
		return table;
	}
	
	public void update() {
		log.entering(CategoryTable.class.getName(), "update");
		log.exiting(CategoryTable.class.getName(), "update");
		model.fireTableDataChanged();
	}
	
	class CategoryModel extends AbstractTableModel {
		private final Logger log = Logger.getLogger(CategoryModel.class.getName());

		private String [] columnNames = {"Category", "Notes"};
		
		@Override
		public String getColumnName(int columnIndex) {
			log.entering(CategoryModel.class.getName(), "getColumnName");
			log.exiting(CategoryModel.class.getName(), "getColumnName", columnNames[columnIndex]);
			return columnNames[columnIndex];
		}
		
		@Override
		public int getRowCount() {
			log.entering(CategoryModel.class.getName(), "getRowCount");
			log.exiting(CategoryModel.class.getName(), "getRowCount", TransactionTable.model.getTransactionLog().categoryCount());
			return TransactionTable.model.getTransactionLog().categoryCount();
		}

		@Override
		public int getColumnCount() {
			log.entering(CategoryModel.class.getName(), "getColumnCount");
			log.exiting(CategoryModel.class.getName(), "getColumnCount", columnNames.length);
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			log.entering(CategoryModel.class.getName(), "getValueAt", new Object[] {rowIndex,columnIndex});
			switch (columnIndex) {
			case 0: 
				log.exiting(CategoryModel.class.getName(), "getValueAt", TransactionTable.model.getTransactionLog().getCategory(rowIndex).getName());
				return TransactionTable.model.getTransactionLog().getCategory(rowIndex).getName();
			case 1: 
				log.exiting(CategoryModel.class.getName(), "getValueAt", TransactionTable.model.getTransactionLog().getCategory(rowIndex).getNotes());
				return TransactionTable.model.getTransactionLog().getCategory(rowIndex).getNotes();
			default:
				log.exiting(CategoryModel.class.getName(), "getValueAt", "Error");
				return "Error";
			}
		}		
	}
}
