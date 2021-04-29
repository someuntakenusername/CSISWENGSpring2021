package esc.baylor.edu.groupProject.SwingGUI.Category;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;

/**
 * Frame used for adding or editing categories
 * 
 * @author Trae
 */
public class CategoryFrame extends JFrame implements ActionListener{
	private static final Logger log = Logger.getLogger(CategoryFrame.class.getName());

	private CategoryTable table;
	private JButton confirm, cancel;
	private JPanel panel;
	private JTextField name;
	private JTextArea notes;
	private int rowIndex;

	/**
	 * Creates a frame allowing the user to set the Name and Notes of a Category
	 * 
	 * @param table The Category table which the frame needs access to
	 * @param rowIndex The rowIndex of the Category being modified. -1 If a Category is not being modified
	 */
	public CategoryFrame(CategoryTable table, int rowIndex) {
		super("Add Category");
		log.entering(CategoryFrame.class.getName(), "CategoryFrame", new Object[] {table,rowIndex});
		this.table = table;
		this.rowIndex = rowIndex;
		
		panel = new JPanel(new GridLayout(3, 2));

		//Initialize confirm/cancel buttons
		name = new JTextField(20);
		notes = new JTextArea(25, name.getWidth());
		JScrollPane pane = new JScrollPane(notes);
		
		panel.add(new JLabel("Category Name:"));
		panel.add(name);
		panel.add(new JLabel("Notes:"));
		panel.add(pane);

		cancel = new JButton("Cancel");
		cancel .setActionCommand("Cancel");
		cancel.addActionListener(this);

		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);

		panel.add(cancel);
		panel.add(confirm);

		add(panel);

		//Check for edit and initialize
		if(rowIndex != -1) init();

		this.pack();
		this.setSize(new Dimension(300, 150));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		log.exiting(CategoryFrame.class.getName(), "CategoryFrame");
	}

	/**
	 * Initializes the values in the text field
	 */
	private void init() {
		log.entering(CategoryFrame.class.getName(), "init");
		name.setText(TransactionTable.getTransactionLog().getCategory(rowIndex).getName());
		notes.setText(TransactionTable.getTransactionLog().getCategory(rowIndex).getNotes());
		log.exiting(CategoryFrame.class.getName(), "init");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(CategoryFrame.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("Confirm")) {
			if(name.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "Category must have a name", "Warning", JOptionPane.ERROR_MESSAGE);	
			} else if(rowIndex == -1) {
				if(TransactionTable.getTransactionLog().categoryExists(name.getText())) {
					JOptionPane.showMessageDialog(this, "Category with this name already exists", "Warning", JOptionPane.ERROR_MESSAGE);
				} else {
					TransactionTable.getTransactionLog().addCategory(name.getText(), notes.getText());
					table.update();
					TransactionTable.getTransactionLog().save();
					quit();
				}
			} else {
				TransactionTable.getTransactionLog().editCategory(rowIndex, name.getText(), notes.getText());
				table.update();
				TransactionTable.getTransactionLog().save();
				quit();
			}
		} else if(e.getActionCommand().equals("Cancel")) {
			quit();
		}
		log.exiting(CategoryFrame.class.getName(), "actionPerformed");
	}
	
	/**
	 * Internal function that disposes of the frame when called
	 */
	private void quit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}

