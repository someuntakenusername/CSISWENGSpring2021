package esc.baylor.edu.groupProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TransactionLog {
	HashMap<Integer, Transaction> tLog;
	HashMap<Category, ArrayList<Integer>> cList;
	
	public TransactionLog() {
		tLog = new HashMap<Integer, Transaction>();
		cList = new HashMap<Category, ArrayList<Integer>>();
	}
	public void addTransaction(Type type, String title, Date date, Double amount, int id, boolean recurring) {
		Transaction t = new Transaction(type, recurring);
		t.setTitle(title);
		t.setDate(date);
		t.setAmount(amount);
		t.setId(id);
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
	public void load() {
		
	}
}
