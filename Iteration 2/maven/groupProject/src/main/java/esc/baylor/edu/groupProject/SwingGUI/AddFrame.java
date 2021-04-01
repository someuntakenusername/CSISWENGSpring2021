package esc.baylor.edu.groupProject.SwingGUI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import esc.baylor.edu.groupProject.Types;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFrame extends JFrame implements ActionListener, ItemListener {
	private ListDisplay parent;
	private JPanel panel;
	private JTextField title, amount;
	private JCheckBox recurring;
	private JComboBox<Types> type;
	private JButton confirm, cancel;
	private boolean recur = false;

	public AddFrame(ListDisplay parent) {
		super("Add New Transaction");
		this.parent = parent;
		//Setup 4x2 panel
		panel = new JPanel(new GridLayout(6, 2));
		
		//Type options
		Types [] options = {Types.Expense, Types.Income};
		type = new JComboBox<Types>(options);
		panel.add(new JLabel("Type"));
		panel.add(type);

		//Setup title and amount fields
		title = new JTextField(50);
		amount = new JTextField(20);
		//Limit amount to numbers
		amount.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == '.' && !amount.getText().contains(".")) {
					amount.setEditable(true);
				}  else if(ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					amount.setEditable(true);
				} else {
					amount.setEditable(false);
				}
			}
		});

		//Recurrence checkbox
		recurring = new JCheckBox();
		recurring.addActionListener(this);

		//Buttons
		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		panel.add(new JLabel("Title"));
		panel.add(title);
		panel.add(new JLabel("Amount"));
		panel.add(amount);
		panel.add(new JLabel("Date"));
		panel.add(new JLabel("Date"));
		panel.add(new JLabel("Recurring"));
		panel.add(recurring);
		panel.add(confirm);
		panel.add(cancel);

		this.setContentPane(panel);
		this.setSize(new Dimension(300, 200));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            recur = false;
        } else {
        	recur = true;
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked.equals(confirm)) {
			//Types type, String title, Date date, Double amount, boolean recurring
			parent.tLog.addTransaction((Types)type.getSelectedItem(), title.getText(), new Date(), Double.parseDouble(amount.getText()), recur);
			parent.model.fireTableDataChanged();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}  else if (clicked.equals(cancel)) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
}
