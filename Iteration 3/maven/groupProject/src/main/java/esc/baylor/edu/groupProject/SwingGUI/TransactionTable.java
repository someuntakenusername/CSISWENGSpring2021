package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import esc.baylor.edu.groupProject.TransactionObjects.Category;

public class TransactionTable extends JPanel implements ActionListener {
	protected JTable table;
	protected TransactionTableModel model;
	private JComboBox<Object> filter;

	public TransactionTable(JComboBox<Object> filter) {
		super(new BorderLayout());
		this.filter = filter;
		//Load Transaction List and Populate List Model
		model = new TransactionTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer());
		JScrollPane scroll = new JScrollPane(table);
		
		populate();
		filter.setSelectedIndex(1);
		
		add(scroll);	
	}
	
	private void populate() {
		filter.removeAllItems(); 
		filter.addItem("Refresh List");
		filter.addItem("None");
		for(int i = 0; i < model.getTransactionLog().categoryCount(); ++i) {
			filter.addItem(model.getTransactionLog().getCategory(i));
		}
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CMD_ADD_EXPENSE")) {
			new TransactionFrame(model, -1);
		} else if(e.getActionCommand().equals("CMD_DELETE_EXPENSE")) {
			int i = JOptionPane.showConfirmDialog (this, "Are you sure you want to delete this transaction?", "Warning", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				model.getTransactionLog().removeTransaction(model.getTransactionLog().getTransaction(table.getSelectedRow()));
				model.fireTableDataChanged();
			}
		} else if(e.getActionCommand().equals("CMD_EDIT_EXPENSE")) {
			new TransactionFrame(model, table.getSelectedRow());
		} else if(e.getActionCommand().equals("filter")) {
			if(filter.getSelectedIndex() == 0) {
				populate();
				filter.setSelectedIndex(1);
			} else if (filter.getSelectedIndex() == 1) {
				model.filterTable(null);
			} else {
				model.filterTable((Category)filter.getSelectedItem());
			}
		}
	}
}
