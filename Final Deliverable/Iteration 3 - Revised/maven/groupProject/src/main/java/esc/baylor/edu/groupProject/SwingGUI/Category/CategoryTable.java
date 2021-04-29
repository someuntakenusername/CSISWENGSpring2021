package esc.baylor.edu.groupProject.SwingGUI.Category;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.Objects.TransactionLog;
import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;

/**
 * Creates a panel containing a table of all Categories in the TransactionLog
 * 
 * @author Trae
 *
 */
public class CategoryTable extends JPanel{
	private static final Logger log = Logger.getLogger(CategoryTable.class.getName());
	
	private CategoryModel model;
	private JTable table;
	
	/**
	 * Constructs the category table panel
	 */
	public CategoryTable() {
		super();
		model = new CategoryModel();
		
		//Sets up CategoryList and model
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		setSize(100, 100);
	}
	
	/**
	 * 
	 * @return The JTable object associated with the table in the panel
	 */
	public JTable getTable() {
		log.entering(CategoryTable.class.getName(), "getTable");
		log.exiting(CategoryTable.class.getName(), "getTable",table);
		return table;
	}
	
	/**
	 * Used to notify the TableModel of changes in the Table
	 */
	public void update() {
		log.entering(CategoryTable.class.getName(), "update");
		log.exiting(CategoryTable.class.getName(), "update");
		model.fireTableDataChanged();
	}
	
	/**
	 * The custom model for the Category Table
	 * 
	 * @author Trae
	 *
	 */
	class CategoryModel extends AbstractTableModel {

		private String [] columnNames = {"Category", "Notes"};
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getRowCount() {
			return TransactionTable.getTransactionLog().categoryCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0: 
				return TransactionTable.getTransactionLog().getCategory(rowIndex).getName();
			case 1: 
				return TransactionTable.getTransactionLog().getCategory(rowIndex).getNotes();
			default:
				return "Error";
			}
		}		
	}
}
