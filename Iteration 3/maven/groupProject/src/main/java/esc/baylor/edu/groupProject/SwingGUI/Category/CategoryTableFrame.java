package esc.baylor.edu.groupProject.SwingGUI.Category;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.tools.sjavac.Log;

import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;

/**
 * Displays a list of Categories for adding, removing, or editing Categories. In addition
 * allows the user to add Transactions to a Category
 * 
 * @author Trae
 */
public class CategoryTableFrame extends JFrame implements ActionListener {
	private static final Logger log = Logger.getLogger(CategoryTableFrame.class.getName());
	private JPanel top, panel;
	private CategoryTable cats;
	private JButton cancel, edit, delete, add, addTran;

	/**
	 * Constructs the Frame in which the Category Table and Buttons are displayed
	 */
	public CategoryTableFrame() {
		super("Category List");
		log.entering(CategoryTableFrame.class.getName(), "CategoryTableFrame");
		top = new JPanel();
		cats = new CategoryTable();
		top.add(cats, BorderLayout.PAGE_START);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		//Cancel button
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);
		//Delete Button
		delete = new JButton("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(this);
		//Edit Button
		edit = new JButton("Edit");
		edit.setActionCommand("Edit");
		edit.addActionListener(this);
		//New Transaction Button
		add = new JButton("New");
		add.setActionCommand("Add");
		add.addActionListener(this);
		//Adding transactions to category
		addTran = new JButton("Add Transactions");
		addTran.setSize(100, 100);
		addTran.setActionCommand("Tran");
		addTran.addActionListener(this);
		//Add buttons to panel
		panel.add(cancel);
		panel.add(delete);
		panel.add(edit);
		panel.add(add);
		panel.add(addTran);
		
		//Append buttons to bottom of panel with table
		top.add(panel, BorderLayout.PAGE_END);
		
		add(top);
		this.pack();
		this.setSize(new Dimension(cats.getWidth(), cats.getHeight()+100));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		log.exiting(CategoryTableFrame.class.getName(), "CategoryTableFrame");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(CategoryTableFrame.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("Add")) {
			new CategoryFrame(cats, -1);
		} else if(e.getActionCommand().equals("Delete") && cats.getTable().getSelectedRow() != -1) {
			int i = JOptionPane.showConfirmDialog (this, "Are you sure you want to delete this category?", "Warning", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				TransactionTable.getTransactionLog().
				removeCategory(TransactionTable.getTransactionLog().getCategory(cats.getTable().getSelectedRow()));
				TransactionTable.getTransactionLog().save();
			}
			cats.update();
		} else if(e.getActionCommand().equals("Edit") && cats.getTable().getSelectedRow() != -1) {
			new CategoryFrame(cats, cats.getTable().getSelectedRow());
		} else if(e.getActionCommand().equals("Cancel")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if(e.getActionCommand().equals("Tran") && cats.getTable().getSelectedRow() != -1) {
			new AddTransactionToCategory(TransactionTable.getTransactionLog().getCategory(cats.getTable().getSelectedRow()));
		}
		log.exiting(CategoryTableFrame.class.getName(), "actionPerformed");
	}
	
}
