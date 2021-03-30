package esc.baylor.edu.groupProject;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionLog {
	ArrayList<Transaction> tLog;
	ArrayList<Category> cList;
	
	public TransactionLog() {
		tLog = new ArrayList<Transaction>();
	}
	
	public void addTransaction(Transaction t) {
		tLog.add(t);
	}
	
	public void removeTransaction(Transaction t) {
		tLog.remove(t.getId());
	}
}
