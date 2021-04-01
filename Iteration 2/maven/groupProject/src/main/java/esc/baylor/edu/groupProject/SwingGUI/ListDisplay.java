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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import esc.baylor.edu.groupProject.TransactionLog;
import esc.baylor.edu.groupProject.Type;

public class ListDisplay extends JPanel implements ActionListener {
	private TransactionLog tLog;
	private JTable table;
	JPanel panel;
	JButton details, remove;

	public ListDisplay() {
		super(new BorderLayout());
		//Load Transaction List and Populate List Model
		tLog = new TransactionLog();
		tLog.load();
		table = new JTable(new TransactionTableModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.PAGE_START);

		//Button Panel
		panel = new JPanel(new GridLayout(1, 2));
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		//Details Button
		details = new JButton("Details");
		details.setActionCommand("Details");
		details.addActionListener(this);
		details.setEnabled(false);
		panel.add(details);
		panel.add(Box.createHorizontalStrut(15));

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
		
	}

}
