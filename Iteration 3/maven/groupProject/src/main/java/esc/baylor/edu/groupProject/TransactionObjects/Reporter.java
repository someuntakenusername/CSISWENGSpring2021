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
	
	/*
	 * Generates a report with all transaction object within the date range
	 */
	public Report generateReport(Date from, Date to, Collection<Transaction> t) {
		Report report = new Report();
		
		report.from = from;
		report.to = to;
		
		//get expenses within date range
		List<Transaction> expenses = t.parallelStream()
				.filter((n) -> n.getDate().after(from) && n.getDate().before(to) && n.getType() == Types.Expense)
				.collect(Collectors.toList());
		
		//compute stats
		if(!expenses.isEmpty()) {
			report.expenseAvg = 0;
			//max,min
			report.expenseHi = expenses.get(0).getAmount();
			report.expenseLo = expenses.get(0).getAmount();
			
			expenses.parallelStream().forEach((x) -> {
				report.expenseAvg += x.getAmount();
				report.expenseHi = report.expenseHi < x.getAmount() ? x.getAmount() : report.expenseHi;
				report.expenseLo = report.expenseLo > x.getAmount() ? x.getAmount() : report.expenseLo;
			});
			
			//calculate average
			report.expenseAvg /= expenses.size();
		}
		
		//get savings within range
		List<Transaction> savings = t.parallelStream()
				.filter((n) -> n.getDate().after(from) && n.getDate().before(to) && n.getType() == Types.Income)
				.collect(Collectors.toList());
		
		//do the same thing for savings as we did for expenses
		
		return report;
	}
}
