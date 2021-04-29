package esc.baylor.edu.groupProject.Objects;

import java.util.Date;

/**
 * 
 * @author Will
 *
 */
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
