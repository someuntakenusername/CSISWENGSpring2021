package esc.baylor.edu.groupProject;

import java.util.ArrayList;

public class TransactionLog {
	ArrayList<Transaction> tList;
	
	public TransactionLog() {
		tList = new ArrayList<Transaction>();
	}
	
	public void addTransaction(Transaction t) {
		tList.add(t);
	}
	
	public void removeTransaction(int i) {
		tList.remove(i);
	}
}
