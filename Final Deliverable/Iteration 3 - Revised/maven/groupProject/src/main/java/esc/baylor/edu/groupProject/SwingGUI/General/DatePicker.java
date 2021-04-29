package esc.baylor.edu.groupProject.SwingGUI.General;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A pop-up menu for selecting dates
 * 
 * @author Jyoti Jha, Trae
 *
 */
public class DatePicker extends JPanel {
	private static final Logger log = Logger.getLogger(DatePicker.class.getName());
	private int month = Calendar.getInstance().get(Calendar.MONTH);
	private int year = Calendar.getInstance().get(Calendar.YEAR);;
	private JLabel l = new JLabel("", JLabel.CENTER);
	private String day = "";
	private JDialog d;
	private JButton[] button = new JButton[49];

	public DatePicker(JFrame parent) {
		log.entering(DatePicker.class.getName(), "DatePicker", parent);
		d = new JDialog();
		d.setModal(true);
		String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));

		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			if (x > 6)
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						d.dispose();
					}
				});
			if (x < 7) {
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			p1.add(button[x]);
		}
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton("Next >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(parent);
		displayDate();
		d.setVisible(true);
		log.exiting(DatePicker.class.getName(), "DatePicker");
	}

	public void displayDate() {
		log.entering(DatePicker.class.getName(), "displayDate");
		for (int x = 7; x < button.length; x++)
			button[x].setText("");
		SimpleDateFormat sdf = new SimpleDateFormat(
				"MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
			button[x].setText("" + day);
		l.setText(sdf.format(cal.getTime()));
		d.setTitle("Date Picker");
		log.exiting(DatePicker.class.getName(), "displayDate");
	}

	public String setPickedDate() {
		log.entering(DatePicker.class.getName(), "setPickedDate");
		if (day.equals(""))
			return day;
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd, yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		log.exiting(DatePicker.class.getName(), "setPickedDate", sdf.format(cal.getTime()));
		return sdf.format(cal.getTime());		
	}
}
