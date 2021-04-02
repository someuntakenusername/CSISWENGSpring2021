package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class TransactionLog {
	HashMap<Integer, Transaction> tLog;
	ArrayList<Category> cList;
	ArrayList<Integer> tSort;
	private int id;
	
	public TransactionLog() {
		tLog = new HashMap<Integer, Transaction>();
		cList = new ArrayList<Category>();
		tSort = new ArrayList<Integer>();
		load();
	}
	public void addTransaction(Types type, String title, Date date, Double amount, boolean recurring) {
		Transaction t = new Transaction(type, recurring);
		t.setTitle(title);
		t.setDate(date);
		t.setAmount(amount);
		t.setId(id);
		tSort.add(id++);
		tLog.put(t.getId(), t);
		//sort();
	}
	
	public Transaction getTransaction(int index) {
		return tLog.get(tSort.get(index));
	}
	public void removeTransaction(Transaction t) {
		tSort.remove(tSort.indexOf(t.getId()));
		tLog.remove(t.getId());
	}
	
	public void addCategory(String name) {
		Category c = new Category();
		c.setName(name);
		cList.add(c);
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

	public int size() {
		return tLog.size();
	}
	public void load() {
		id = 0;
		Transaction t = new Transaction(Types.Expense, false);
		t.setTitle("Test Title");
		t.setAmount(15.0);
		t.setDate(new Date());
		t.setId(id);
		tLog.put(id, t);
		tSort.add(id++);
		/*
		 * Load previous info
		 */
	}
	public void save() {
		/*
		 * Save code
		 */
	}
	
	//Internal Sort Function
	private void sort() {
		tSort.sort(new Comparator<Object>() {
			public int compare(Object a, Object b) {
				return tLog.get(a).getDate().compareTo(tLog.get(b).getDate());
			}
		});
	}
}
