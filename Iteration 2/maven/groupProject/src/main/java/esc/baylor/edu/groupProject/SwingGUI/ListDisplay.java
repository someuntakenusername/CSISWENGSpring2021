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

import esc.baylor.edu.groupProject.TransactionLog;
import esc.baylor.edu.groupProject.Type;

public class ListDisplay extends JPanel implements ActionListener {
	private TransactionLog tLog;
	private JTable table;
	private TransactionTableModel model;
	JPanel panel;
	JButton add, details, remove;

	public ListDisplay() {
		super(new BorderLayout());
		//Load Transaction List and Populate List Model
		tLog = new TransactionLog();
		tLog.load();
		model = new TransactionTableModel();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new TableListSelection());
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.PAGE_START);

		//Button Panel
		panel = new JPanel(new GridLayout(1, 2));
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		//Add Button
		add = new JButton("New Transaction");
		add.setActionCommand("New");
		add.addActionListener(this);
		add.setEnabled(true);
		panel.add(add);
		panel.add(Box.createHorizontalStrut(30));

		//Details Button
		details = new JButton("Details");
		details.setActionCommand("Details");
		details.addActionListener(this);
		details.setEnabled(false);
		panel.add(details);
		panel.add(Box.createHorizontalStrut(30));

		//Remove Button
		remove = new JButton("Remove");
		remove.setActionCommand("Remove");
		remove.addActionListener(this);
		remove.setEnabled(false);
		panel.add(remove);
		panel.setBounds(new Rectangle(100, 100));
		add(panel, BorderLayout.PAGE_END);
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
				if(tLog.getTransaction(rowIndex).getType().equals(Type.Expense)) {
					return val*-1;
				}
				return val;
			case 2: 
				return tLog.getTransaction(rowIndex).getDate();      
			default: return "Error";
			}
		}	
	}
	
	class TableListSelection implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(table.getSelectedRow() == -1) {
				details.setEnabled(false);
				remove.setEnabled(false);
			}
			else {
				details.setEnabled(true);
				remove.setEnabled(true);
			}
		}
		
	}

	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ListDisplay();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Remove")) {
			int i = JOptionPane.showConfirmDialog(this,  "Are you sure you want to delete this transaction?");
			if(i == JOptionPane.YES_OPTION) {
				tLog.removeTransaction(tLog.getTransaction(table.getSelectedRow()));
				model.fireTableDataChanged();
			}
		} else if(e.getActionCommand().equals("New")) {
			new AddFrame(this);
		}
	}

}
