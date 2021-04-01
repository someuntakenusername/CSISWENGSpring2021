package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	JPanel panel;
	JButton details, remove;
	
	public ListDisplay() {
		super(new BorderLayout());
		//Load Transaction List and Populate List Model
		tLog = new TransactionLog();
		populateList();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(15);
		JScrollPane listPane = new JScrollPane(list);
		
		//Add List to frame
		add(listPane, BorderLayout.PAGE_START);
		
		//Button Panel
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		//Details Button
		details = new JButton("Details");
		details.setActionCommand("Details");
		details.addActionListener(new DetailListener());
		details.setEnabled(false);
		panel.add(details);
		panel.add(Box.createHorizontalStrut(15));
		
		//Remove Button
		remove = new JButton("Remove");
		remove.setActionCommand("Remove");
		remove.addActionListener(new RemoveListener());
		remove.setEnabled(false);
		panel.add(remove);

		add(panel, BorderLayout.PAGE_END);
	}
	
	class DetailListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class RemoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Fetch Selected Transaction and remove from list
			listModel.remove(list.getSelectedIndex());
			
			//Reset Selection
			list.setSelectedIndex(-1);
			details.setEnabled(false);
			remove.setEnabled(false);
		}
		
	}
	

	//This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
            //No selection, disable detail and remove buttons
                details.setEnabled(false);
                remove.setEnabled(false);
            } else {
            //Opposite of above
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
