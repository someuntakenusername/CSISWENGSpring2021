package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	/*
	 * Replaces the data of the transaction at the given index with the newly provided data
	 * 
	 * @param index The index of the transaction in the Transaction Sort list
	 * @param type The type of the transaction
	 * @param title The title of the transaction
	 * @param date The date of the transaction
	 * @param amount The amount of the transaction
	 * @param recur The recurrence of the Transaction in days
	 */
	public void editTransaction(int index, Types type, String title, Date date, Double amount, Integer recur) {
		Transaction t = tLog.get(tSort.get(index));
		t.setType(type);
		t.setTitle(title);
		t.setAmount(amount);
		t.setDate(date);
		t.setRecur(recur);
	}
	
	/*
	 * Removes a transaction from the transaction list
	 * 
	 * @param t The transaction to be removed from the transaction list
	 */
	public void removeTransaction(Transaction t) {
		tSort.remove(tSort.indexOf(t.getId()));
		tLog.remove(t.getId());
	}
	
	/*
	 * Adds a category to the category list
	 * 
	 * @param name The name of the category
	 * @param notes Notes for the category
	 */
	public void addCategory(String name, String notes) {
		Category c = new Category();
		c.setName(name);
		c.setNotes(notes);
		cList.add(c);
	}
	
	/*
	 * Edits the category at the given index in the category list
	 * 
	 * @param index The index in the category list of the category being edited
	 * @param name The new name of the Category
	 * @param notes The new set of notes for the Category
	 */
	public void editCategory(int index, String name, String notes) {
		cList.get(index).setName(name);
		cList.get(index).setNotes(notes);
	}
	
	/*
	 * Removes a category from the category list
	 * 
	 * @param cat The category to remove from the list
	 */
	public void removeCategory(Category cat) {
		cList.remove(cat);
	}
	
	/*
	 * @return the category at the given index in the category list
	 */
	public Category getCategory(int index) {
		return cList.get(index);
	}
	
	/*
	 * @return The size of the Transaction Log
	 */
	public int size() {
		return tLog.size();
	}
	
	/*
	 * Checks if a category exists with the given name
	 */
	public boolean categoryExists(String name) {
		for(Category c : cList) {
			if(c.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * @return The number of categories associated with the transaction log
	 */
	public int categoryCount() {
		return cList.size();
	}
	
	/*
	 * Loads the users saved transactions to the log
	 */
	private void load() {
		//Test Transaction
		id = 0;
		Transaction t = new Transaction(Types.Expense, -1);
		t.setTitle("Test Title");
		t.setAmount(15.439);
		t.setDate(new Date());
		t.setId(id);
		tLog.put(id, t);
		tSort.add(id++);
		
		//Test Category
		Category c = new Category();
		c.setName("Test Category");
		c.setNotes(null);
		cList.add(c);
		
		
		/*
		 * Load previous info
		 */
	}
	
	/*
	 * saves the users transactions when one is added or removed
	 */
	private void saveTransactions() {
		
	}
	
	private void saveCategories() {
		
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
