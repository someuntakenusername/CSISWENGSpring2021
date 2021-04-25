package esc.baylor.edu.groupProject.SwingGUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CategoryMenu extends JMenu {

	TransactionTableModel model;
	JMenuItem addCat, delCat, editCat;
	
	public CategoryMenu(TransactionTableModel model) {
		this.model = model;
		addCat = new JMenuItem("Add Category");
	}
}
