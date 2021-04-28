package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CategoryMenu extends JMenu implements ActionListener{

	private JMenuItem catList;
	
	public CategoryMenu() {
		super("Category");
		//Initialize Menu Item
		
		catList = new JMenuItem("View Category List");
		catList.setActionCommand("List");
		catList.addActionListener(this);
		add(catList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("List")) {
			new CategoryTableFrame();
		}
	}
}
