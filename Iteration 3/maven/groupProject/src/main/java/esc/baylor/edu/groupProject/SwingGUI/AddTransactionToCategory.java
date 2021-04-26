package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import esc.baylor.edu.groupProject.SwingGUI.TransactionTable.DecimalFormatRenderer;
import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;

/*
 * Table used to add Transactions to a Category
 * 
 * @author Trae
 */
public class AddTransactionToCategory extends JFrame {
	
	private TransactionLog tLog;
	private Category category;
	private JTable table;
	private TranCatTableModel model;
	
	public AddTransactionToCategory(TransactionLog tLog, Category category) {
		this.tLog = tLog;
		this.category = category;
		
		model = new TranCatTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer());
		table.setCellSelectionEnabled(true);
		
		this.setContentPane(table);
		this.setSize(new Dimension(500, 200));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
	class TranCatTableModel extends AbstractTableModel {

		private final String [] columns = {"Title", "Amount", "Date", ""};
		private final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
		
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
    };
	
    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		private static final DecimalFormat formatter = new DecimalFormat( "#.00" );
	 
	      public Component getTableCellRendererComponent(
	         JTable table, Object value, boolean isSelected,
	         boolean hasFocus, int row, int column) {
	 
	         // First format the cell value as required
	         value = formatter.format((Number)value);
	 
	         // And pass it on to parent class
	         return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
	      }
	   }
	
}
