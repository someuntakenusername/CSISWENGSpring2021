package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.Date;

public class Report implements Comparable<Report>{
	public int[] transactionIds = null;
	public Date from = null, to = null;
	public double savingAvg = 0,
			savingMed = 0,
			savingHi = 0,
			savingLo = 0,
			expenseAvg = 0,
			expenseMed = 0,
			ExpenseHi = 0,
			ExpenseLo = 0;
	
	@Override
	//sort by date
	public int compareTo(Report o) {
		return this.weekOf.compareTo(o.weekOf);
	}
}
