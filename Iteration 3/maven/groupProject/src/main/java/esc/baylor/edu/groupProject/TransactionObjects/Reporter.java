package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class Reporter {
	private TreeSet<Report> reports;
	
	public Report generateReport(Date from, Date to, Collection<Transaction> t) {
		Report report = new Report();
		
		report.from = from;
		report.to = to;
		
		return report;
	}
}
