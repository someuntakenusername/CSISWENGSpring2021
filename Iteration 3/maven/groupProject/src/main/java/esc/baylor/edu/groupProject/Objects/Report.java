package esc.baylor.edu.groupProject.Objects;

import java.util.Date;

//basically just a struct
public class Report{
	//transaciton ids used to generate this report
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
