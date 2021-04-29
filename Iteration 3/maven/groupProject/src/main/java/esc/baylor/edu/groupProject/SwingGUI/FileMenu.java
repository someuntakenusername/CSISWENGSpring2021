package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu implements ActionListener{
	private final static Logger log = Logger.getLogger(FileMenu.class.getName());

	private JMenuItem save;
	
	public FileMenu() {
		super("File");
		log.entering(FileMenu.class.getName(), "FileMenu");

		save = new JMenuItem("Save");
		save.setActionCommand("save");
		save.addActionListener(this);
		
		add(save);
		log.exiting(FileMenu.class.getName(), "FileMenu");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(FileMenu.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("save")) {
			TransactionTable.model.getTransactionLog().save();
		}
		log.exiting(FileMenu.class.getName(), "actionPerformed");
	}
}
