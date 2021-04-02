package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;
import esc.baylor.edu.groupProject.TransactionObjects.Types;

public class ListDisplay extends JPanel implements ActionListener {
	protected TransactionLog tLog;
	protected JTable table;
	protected TransactionTableModel model;
	JPanel panel;
	JButton add, details, remove;

	public ListDisplay() {
		super(new BorderLayout());
		//Load Transaction List and Populate List Model
		tLog = new TransactionLog();
		model = new TransactionTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.PAGE_START);	
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
				double val = tLog.getTransaction(rowIndex).getAmount();
				if(tLog.getTransaction(rowIndex).getType().equals(Types.Expense)) {
					return val*-1;
				}
				return val;
			case 2: 
				return tLog.getTransaction(rowIndex).getDate();      
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
