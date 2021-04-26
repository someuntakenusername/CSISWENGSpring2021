package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

public class Report {
	private int[] transactionIds;
	
	public void setTransactions(Collection<Transaction> t) {
		transactionIds = new int[t.size()];
		
		int i = 0;
		
		for(Transaction n : t) {
			transactionIds[i] = n.getId();
			++i;
		}
	}
	
	public Collection<Transaction> getTransactions(Collection<Transaction> db){
		Collection<Transaction> t = new Vector<Transaction>();
		
		db.stream().sorted((a,b) -> a.getId() - b.getId()).;
		
		return t;
	}
	
	public Date weekOf;
	public double savingAvg, savingMed, savingHi, savingLo, expenseAvg, expenseMed, expenseHi, expenseLo;
}
