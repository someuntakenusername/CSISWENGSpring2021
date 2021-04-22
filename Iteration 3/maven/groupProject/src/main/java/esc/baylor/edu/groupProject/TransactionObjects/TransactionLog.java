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
	
	/*
	 * Initializes the transaction log and calls for the program to load stored data
	 */
	public TransactionLog() {
		tLog = new HashMap<Integer, Transaction>();
		cList = new ArrayList<Category>();
		tSort = new ArrayList<Integer>();
		load();
	}
	
	/*
	 * Adds a transaction to the Transaction Log
	 * 
	 * @param type The enumerated type of the transaction, either Income or Expense
	 * @param title The name of the transaction
	 * @param date The date on which the transaction occurred
	 * @param amount The amount of the transaction
	 * @param recur How often this transaction recurs. Null if it is not recurring
	 */
	public void addTransaction(Types type, String title, Date date, Double amount, Integer recur) {
		Transaction t = new Transaction(type, recur);
		t.setTitle(title);
		t.setDate(date);
		t.setAmount(amount);
		t.setId(id);
		tSort.add(id++);
		tLog.put(t.getId(), t);
		sort();
	}
	
	/*
	 * Returns a transaction given its sorted index
	 * 
	 * @param index The index of a transaction in the sorted list
	 * 
	 * @return The Transaction at the given index in the sorted list
	 */
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
	public ArrayList<Category> getcList() {
		return cList;
	}
	public void setcList(ArrayList<Category> cList) {
		this.cList = cList;
	}
	public int size() {
		return tLog.size();
	}
	
	/*
	 * Loads the users saved transactions to the log
	 */
	private void load() {
		id = 0;
		Transaction t = new Transaction(Types.Expense, -1);
		t.setTitle("Test Title");
		t.setAmount(15.439);
		t.setDate(new Date());
		t.setId(id);
		tLog.put(id, t);
		tSort.add(id++);
		/*
		 * Load previous info
		 */
	}
	
	private void save() {
		/*
		 * Save code
		 */
	}
	
	/*
	 * Internal sort function for the transaction log. Sorts the transactions by date
	 */
	private void sort() {
		tSort.sort(new Comparator<Object>() {
			public int compare(Object a, Object b) {
				return tLog.get(a).getDate().compareTo(tLog.get(b).getDate());
			}
		});
	}
}
