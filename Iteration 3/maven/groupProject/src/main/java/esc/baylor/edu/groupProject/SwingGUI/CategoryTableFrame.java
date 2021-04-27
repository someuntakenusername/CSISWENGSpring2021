package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Displays the list of categories for making a selection when editing
 * or removing a category
 * @author Trae
 */
public class CategoryTableFrame extends JFrame implements ActionListener {
	private JPanel top, panel;
	private TransactionTableModel model;
	private CategoryTable cats;
	private JButton cancel, edit, delete, add, addTran;

	public CategoryTableFrame(TransactionTableModel model) {
		super("Category List");
		this.model = model;
		
		top = new JPanel();
		cats = new CategoryTable(model.getTransactionLog());
		top.add(cats, BorderLayout.PAGE_START);

		//Confirm/Cancel buttons
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);
		delete = new JButton("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(this);
		edit = new JButton("Edit");
		edit.setActionCommand("Edit");
		edit.addActionListener(this);
		add = new JButton("New");
		add.setActionCommand("Add");
		add.addActionListener(this);
		addTran = new JButton("Add Transactions");
		addTran.setSize(100, 100);
		addTran.setActionCommand("Tran");
		addTran.addActionListener(this);
		panel.add(cancel);
		panel.add(delete);
		panel.add(edit);
		panel.add(add);
		panel.add(addTran);
		
		top.add(panel, BorderLayout.PAGE_END);
		
		add(top);
		this.pack();
		this.setSize(new Dimension(cats.getWidth(), cats.getHeight()+100));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add")) {
			new CategoryFrame(cats, -1);
		} else if(e.getActionCommand().equals("Delete") && cats.getTable().getSelectedRow() != -1) {
			int i = JOptionPane.showConfirmDialog (this, "Are you sure you want to delete this category?", "Warning", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				model.getTransactionLog().removeCategory(model.getTransactionLog().getCategory(cats.getTable().getSelectedRow()));
				cats.update();
			}
		} else if(e.getActionCommand().equals("Edit") && cats.getTable().getSelectedRow() != -1) {
			new CategoryFrame(cats, cats.getTable().getSelectedRow());
		} else if(e.getActionCommand().equals("Cancel")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if(e.getActionCommand().equals("Tran") && cats.getTable().getSelectedRow() != -1) {
			new AddTransactionToCategory(model.getTransactionLog(), model.getTransactionLog().getCategory(cats.getTable().getSelectedRow()));
		}
	}
	
}
