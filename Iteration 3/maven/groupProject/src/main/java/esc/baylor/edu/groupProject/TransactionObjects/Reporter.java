package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;

public class Reporter {
	private ArrayList<Report> reports;
	
	public Report generateReport(Date from, Date to, Collection<Transaction> t) {
		Report report = new Report();
		
		report.from = from;
		report.to = to;
		
		final int count = 0;
		
		List<Transaction> reported = t.stream().sorted((a,b) -> a.getDate().compareTo(b.getDate())).collect(Collectors.toList());
		
		return report;
	}
}
