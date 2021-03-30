package esc.baylor.edu.groupProject;

import java.util.HashMap;

public class TransactionLog {
	HashMap<Integer, Transaction> tLog;
	
	public TransactionLog() {
		tLog = new HashMap<Integer, Transaction>();
	}
	public void addTransaction(Transaction t) {
		tLog.put(t.getId(), t);
	}
	
	public void removeTransaction(Transaction t) {
		tLog.remove(t.getId());
	}
}
