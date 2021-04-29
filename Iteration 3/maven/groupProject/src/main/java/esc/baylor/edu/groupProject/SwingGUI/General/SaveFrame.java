package esc.baylor.edu.groupProject.SwingGUI.General;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import esc.baylor.edu.groupProject.SwingGUI.Transactions.TransactionTable;

/**
 * The frame that allows the user to set their current savings and the
 * date of which they had those savings in the TransactionLog
 * 
 * @author Trae
 *
 */
public class SaveFrame extends JFrame implements ActionListener {
	private static final Logger log = Logger.getLogger(SaveFrame.class.getName());
	private JTextField amount;
	private JButton cancel, confirm;
	private JComboBox<Object> date;
	private Date selectedDate;
	private static final SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
	
	/**
	 * Initializes the frame with a textfield, date picker, and confirm/cancel buttons
	 */
	public SaveFrame() {
		super("Set Current Savings");
		log.entering(SaveFrame.class.getName(), "SaveFrame");
		
		JPanel panel = new JPanel(new GridLayout(3, 2));
		
		amount = new JTextField(20);
		amount.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if((ke.getKeyChar() < '0' || ke.getKeyChar() > '9') && ke.getKeyChar() != KeyEvent.VK_BACK_SPACE && ke.getKeyChar() != '.') {
					amount.setText(amount.getText().substring(0, amount.getText().length()));
				}
			}
		});
		panel.add(new JLabel("Current Savings:"));
		panel.add(amount);
		
		panel.add(new JLabel("Date:"));
		date = new JComboBox<Object>();
		date.addItem("Select Date");
		date.setActionCommand("Date");
		date.addActionListener(this);
		panel.add(date);
		
		cancel = new JButton("Cancel");
		cancel .setActionCommand("Cancel");
		cancel.addActionListener(this);
		panel.add(cancel);

		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);
		panel.add(confirm);
		
		this.pack();
		this.setContentPane(panel);
		this.setSize(new Dimension(300, 150));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		log.exiting(SaveFrame.class.getName(), "SaveFrame");
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		log.entering(SaveFrame.class.getName(), "actionPerformed", e);
		if(e.getActionCommand().equals("Confirm")) {
			if(amount.getText().equals(null) || selectedDate == null) {
				JOptionPane.showMessageDialog(this, "Must have a valid amount and select the date", "Warning", JOptionPane.ERROR_MESSAGE);	
			} else {
				TransactionTable.getTransactionLog().setCurrentSavings(Double.parseDouble(amount.getText()), selectedDate);
				quit();
			}
		} else if(e.getActionCommand().equals("Cancel")) {
			quit();
		} else if(e.getActionCommand().equals("Date")) {
			String d = new DatePicker(this).setPickedDate();
			if(!d.equals("")) {
				try {
					selectedDate = format.parse(d);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				date.removeAllItems();
				date.addItem(d);
			}
		}
		log.exiting(SaveFrame.class.getName(), "actionPerformed");
	}
	
	/**
	 * Internal function that closes the frame when called
	 */
	private void quit() {
		log.entering(SaveFrame.class.getName(), "quit");
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		log.exiting(SaveFrame.class.getName(), "quit");
	}
}
