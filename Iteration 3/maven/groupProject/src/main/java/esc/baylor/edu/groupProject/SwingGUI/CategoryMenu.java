package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CategoryMenu extends JMenu implements ActionListener{
	private static final Logger log = Logger.getLogger(CategoryMenu.class.getName());
	private JMenuItem catList;
	
	public CategoryMenu() {
		super("Category");
		log.entering(CategoryMenu.class.getName(), "CategoryMenu");
		//Initialize Menu Item
		
		catList = new JMenuItem("View Category List");
		catList.setActionCommand("List");
		catList.addActionListener(this);
		add(catList);
		log.exiting(CategoryMenu.class.getName(), "CategoryMenu");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(CategoryMenu.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("List")) {
			new CategoryTableFrame();
		}
		log.exiting(CategoryMenu.class.getName(), "actionPerformed");
	}
}
