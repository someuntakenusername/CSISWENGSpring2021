package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CategoryMenu extends JMenu implements ActionListener{

	TransactionTableModel model;
	JMenuItem addCat, delCat, editCat;
	
	public CategoryMenu(TransactionTableModel model) {
		super("Category");
		this.model = model;
		//Initialize Menu Items and Action Listeners
		
		//Add Category
		addCat = new JMenuItem("New Category");
		addCat.setActionCommand("New");
		addCat.addActionListener(this);
		add(addCat);
		//Delete Category
		delCat = new JMenuItem("View Category List");
		delCat.setActionCommand("List");
		delCat.addActionListener(this);
		add(delCat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New")) {
			//new CategoryFrame(model, -1);
		} else if(e.getActionCommand().equals("List")) {
			new CategoryTableFrame(model);
		}
	}
}
