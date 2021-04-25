package esc.baylor.edu.groupProject.SwingGUI;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionObjects.Category;

public class CategoryTable extends JPanel{
	
	private ArrayList<Category> cList;
	private CategoryModel model;
	private JTable table;
	
	public CategoryTable(ArrayList<Category> cList) {
		super();
		this.cList = cList;
		model = new CategoryModel();
		
		//Sets up CategoryList and model
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		setSize(100, 100);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void update() {
		model.fireTableDataChanged();
	}
	
	public ArrayList<Category> getList(){
		return cList;
	}
	
	class CategoryModel extends AbstractTableModel {

		private String [] columnNames = {"Category"};
		
		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}
		
		@Override
		public int getRowCount() {
			return cList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return cList.get(rowIndex);
		}		
	}
}
