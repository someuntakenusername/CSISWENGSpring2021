package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu implements ActionListener{

	TransactionTableModel model;
	JMenuItem save;
	
	public FileMenu(TransactionTableModel model) {
		super("File");
		this.model = model;

		save = new JMenuItem("Save");
		save.setActionCommand("save");
		save.addActionListener(this);
		
		add(save);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("save")) {
			model.getTransactionLog().save();
		}
	}
}
