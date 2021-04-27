package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.Date;

public class Report{
	public int[] transactionIds = null;
	public Date from = null, to = null;
	public double savingAvg = Double.NaN,
			savingMed = Double.NaN,
			savingHi = Double.NaN,
			savingLo = Double.NaN,
			expenseAvg = Double.NaN,
			expenseMed = Double.NaN,
			expenseHi = Double.NaN,
			expenseLo = Double.NaN;
}
