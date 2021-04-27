package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import esc.baylor.edu.groupProject.TransactionObjects.Category;
import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;

/*
 * Table used to add Transactions to a Category
 * 
 * @author Trae
 */
public class AddTransactionToCategory extends JFrame {
	
	private JTable table;
	private AddTransactionToCategoryModel model;
	
	public AddTransactionToCategory(Category category) {
		model = new AddTransactionToCategoryModel(category);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer());
		table.setCellSelectionEnabled(true);
		
		JScrollPane scroll = new JScrollPane(table);
		
		this.setContentPane(scroll);
		this.setSize(new Dimension(500, 200));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
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
