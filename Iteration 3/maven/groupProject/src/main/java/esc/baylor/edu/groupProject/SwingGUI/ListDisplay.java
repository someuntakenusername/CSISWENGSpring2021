package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;

public class ListDisplay extends JPanel implements ActionListener {
	protected TransactionLog tLog;
	protected JTable table;
	protected TransactionTableModel model;
	private SimpleDateFormat format;
	JPanel panel;
	JButton add, details, remove;

	public ListDisplay() {
		super(new BorderLayout());
		//Load Transaction List and Populate List Model
		tLog = new TransactionLog();
		model = new TransactionTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer());
		JScrollPane scroll = new JScrollPane(table);
		
		format = new SimpleDateFormat("mm-dd-yyyy");
		
		add(scroll, BorderLayout.PAGE_START);	
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
	
	class TransactionTableModel extends AbstractTableModel {
		
		private String[] columnNames = {"Title", "Amount", "Date"};
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CMD_ADD_EXPENSE")) {
			new AddFrame(this);
		} else if(e.getActionCommand().equals("CMD_DELETE_EXPENSE")) {
			int i = JOptionPane.showConfirmDialog (this, "Are you sure you want to delete this transaction?", "Warning", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				tLog.removeTransaction(tLog.getTransaction(table.getSelectedRow()));
				model.fireTableDataChanged();
			}
			
		}
		
	}
}
