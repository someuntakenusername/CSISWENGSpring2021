package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import esc.baylor.edu.groupProject.Transaction;
import esc.baylor.edu.groupProject.TransactionLog;
import esc.baylor.edu.groupProject.Type;

public class ListDisplay extends JPanel implements ListSelectionListener {
	private JList list;
	private DefaultListModel listModel;
	TransactionLog tLog;
	
	public ListDisplay() {
		super(new BorderLayout());
		tLog = new TransactionLog();
		populateList();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(15);
		JScrollPane listScrollPane = new JScrollPane(list);
		
		add(listScrollPane);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
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
    
    private void populateList() {
    	tLog.load();
    	listModel = new DefaultListModel();
    	Transaction t = new Transaction(Type.Expense, false);
    	t.setTitle("Test Title");
    	t.setAmount(5.0);
    	t.setDate(new Date());
    	listModel.addElement(t.getTitle());
    }

}
