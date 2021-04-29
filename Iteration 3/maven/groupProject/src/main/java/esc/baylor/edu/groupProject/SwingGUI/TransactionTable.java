package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import esc.baylor.edu.groupProject.Objects.Category;
import esc.baylor.edu.groupProject.Objects.TransactionLog;

public class TransactionTable extends JPanel implements ActionListener {
	private static final Logger log = Logger.getLogger(TransactionTable.class.getName());
	private JTable table;
	protected static TransactionTableModel model;
	private JComboBox<Object> filter;

	public TransactionTable(JComboBox<Object> filter) {
		super(new BorderLayout());
		log.entering(TransactionTable.class.getName(), "TransactionTable", filter);
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
		log.exiting(TransactionTable.class.getName(), "TransactionTable");
	}
	
	private void populate() {
		log.entering(TransactionTable.class.getName(), "populate");
		filter.removeAllItems(); 
		filter.addItem("Refresh List");
		filter.addItem("None");
		for(int i = 0; i < model.getTransactionLog().categoryCount(); ++i) {
			filter.addItem(model.getTransactionLog().getCategory(i));
		}
		log.exiting(TransactionTable.class.getName(), "populate");
	}
	
	public static TransactionLog getTransactionLog() {
		log.entering(TransactionTable.class.getName(), "getTransactionLog");
		log.exiting(TransactionTable.class.getName(), "getTransactionLog", model.getTransactionLog());
		return model.getTransactionLog();
	}
	
	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		private static final Logger log = Logger.getLogger(DecimalFormatRenderer.class.getName());
		private static final DecimalFormat formatter = new DecimalFormat( "#.00" );
	 
	      public Component getTableCellRendererComponent(
	         JTable table, Object value, boolean isSelected,
	         boolean hasFocus, int row, int column) {
	    	  log.entering(DecimalFormatRenderer.class.getName(), "getTableCellRendererComponent");
	 
	         // First format the cell value as required
	         value = formatter.format((Number)value);
	 
	         // And pass it on to parent class
	         log.exiting(DecimalFormatRenderer.class.getName(), "getTableCellRendererComponent", super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column ));
	         return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
	      }
	   }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(TransactionTable.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("CMD_ADD_EXPENSE")) {
			new TransactionFrame(null);
		} else if(e.getActionCommand().equals("CMD_DELETE_EXPENSE")) {
			int i = JOptionPane.showConfirmDialog (this, "Are you sure you want to delete this transaction?", "Warning", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				model.getTransactionLog().removeTransaction(model.getTransaction(table.getSelectedRow()));
				model.fireTableDataChanged();
				TransactionTable.model.getTransactionLog().save();
			}
		} else if(e.getActionCommand().equals("CMD_EDIT_EXPENSE") && table.getSelectedRow() != -1) {
			new TransactionFrame(model.getTransaction(table.getSelectedRow()));
		} else if(e.getActionCommand().equals("filter")) {
			if(filter.getSelectedIndex() == 0) {
				populate();
				filter.setSelectedIndex(1);
			} else if (filter.getSelectedIndex() == 1) {
				model.filterTable(null);
			} else {
				model.filterTable((Category)filter.getSelectedItem());
			}
		} else if(e.getActionCommand().equals("Set_Save")) {
			new SaveFrame();
		}
		log.exiting(TransactionTable.class.getName(), "actionPerformed");
	}
}
