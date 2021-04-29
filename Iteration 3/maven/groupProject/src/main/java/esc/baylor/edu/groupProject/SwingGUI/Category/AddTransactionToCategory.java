package esc.baylor.edu.groupProject.SwingGUI.Category;

import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import esc.baylor.edu.groupProject.Objects.Category;

/**
 * Table used to add Transactions to a Category
 * 
 * @author Trae
 */
public class AddTransactionToCategory extends JFrame {

	private JTable table;
	private AddTransactionToCategoryModel model;

	/**
	 * Constructs a Table with all Transactions and check boxes to add or remove them from the
	 * given Category
	 * 
	 * @param category A reference to a Category
	 */
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

	/**
	 * Tells the JTable to render doubles with 2 decimals
	 * 
	 * @author LearnJavaByExample
	 */
	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		private static final DecimalFormat formatter = new DecimalFormat( "#.00" );

		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {

			value = formatter.format((Number)value);

			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
		}
	}

}
