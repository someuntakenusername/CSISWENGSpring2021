package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Frame used for adding or editing categories
 * @author Trae
 */
public class CategoryFrame extends JFrame implements ActionListener{

	private CategoryTable table;
	private TransactionTableModel model;
	private JButton confirm, cancel;
	private JPanel panel;
	private JTextField name, notes;
	private int rowIndex;

	public CategoryFrame(TransactionTableModel model, CategoryTable table, int rowIndex) {
		super("Add Category");
		this.model = model;
		this.rowIndex = rowIndex;
		this.table = table;
		panel = new JPanel(new GridLayout(3, 2));

		//Initialize confirm/cancel buttons
		name = new JTextField(20);
		notes = new JTextField(2000);

		panel.add(new JLabel("Category Name:"));
		panel.add(name);
		panel.add(new JLabel("Notes:"));
		panel.add(notes);

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
		this.setSize(new Dimension(300, 500));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void init() {
		name.setText(model.getTransactionLog().getcList().get(rowIndex).getName());
		notes.setText(model.getTransactionLog().getcList().get(rowIndex).getNotes());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			if(name.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "Category must have a name", "Warning", JOptionPane.ERROR_MESSAGE);
			} else if(model.getTransactionLog().categoryExists(name.getText())) {
				JOptionPane.showMessageDialog(this, "Category with this name already exists", "Warning", JOptionPane.ERROR_MESSAGE);
			} else if(rowIndex == -1) {
				model.getTransactionLog().addCategory(name.getText(), notes.getText());
				table.update();
				quit();
			} else {
				model.getTransactionLog().editCategory(rowIndex, name.getText(), notes.getText());
				table.update();
				quit();
			}
		} else if(e.getActionCommand().equals("Cancel")) {
			quit();
		}
	}
	
	private void quit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}

