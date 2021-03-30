package esc.baylor.edu.groupProject;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionLog {
	HashMap<Integer, Transaction> tLog;
	HashMap<Category, ArrayList<Integer>> cList;
	
	public TransactionLog() {
		tLog = new HashMap<Integer, Transaction>();
	}
	public void addTransaction(Transaction t) {
		tLog.put(t.getId(), t);
	}
	public void removeTransaction(Transaction t) {
		tLog.remove(t.getId());
	}
	
	public void addCategory(Category c) {
		cList.put(c, null);
	}
	
	public void removeCategory(Category c) {
		cList.remove(c);	
	}
	
	public HashMap<Integer, Transaction> gettLog() {
		return tLog;
	}
	public void settLog(HashMap<Integer, Transaction> tLog) {
		this.tLog = tLog;
	}
	public HashMap<Category, ArrayList<Integer>> getcList() {
		return cList;
	}
	public void setcList(HashMap<Category, ArrayList<Integer>> cList) {
		this.cList = cList;
	}
}
