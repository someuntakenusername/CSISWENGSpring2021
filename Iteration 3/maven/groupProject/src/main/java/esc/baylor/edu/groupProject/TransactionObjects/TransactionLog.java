package esc.baylor.edu.groupProject.TransactionObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import esc.baylor.edu.groupProject.SwingGUI.Login;;

/**
 * The TransactionLog object stores the data for all Transactions and Categories
 * 
 * @author Trae
 *
 */
public class TransactionLog implements Serializable {
	ArrayList<Category> cList;
	ArrayList<Transaction> tLog;
	private int id;
	private String filename;
	private static final long serialVersionUID = 2L;
	private double savings;
	private Date currDate;
	
	/**
	 * Initializes the transaction log and calls for the program to load stored data
	 */
	public TransactionLog() {
		filename = Login.user.findFilename();
		tLog = new ArrayList<Transaction>();
		cList = new ArrayList<Category>();
		load();
	}
	
	/**
	 * @return Returns the collection of transactions associated with this TransactionLog object
	 */
	public Collection<Transaction> getTransactionList(){
		return (Collection<Transaction>) tLog.clone();
	}

	/**
	 * @param amount New value of user savings
	 */
	public void setCurrentSavings(double amount, Date date) {
		currDate = date;
		savings = 0.00;
		savings += amount;
	}
	
	/**
	 * Calculates the users current balance
	 * 
	 * @return A double representation of the users current balance calculated 
	 * by summing transactions after the date set by the balance and subtracting
	 * the current balance
	 */
	public Double getCurrentBalance() {
		if(currDate != null) {
			double tranSum = 0.00;
			for(Transaction t: tLog) {
				if(t.getDate().compareTo(currDate) < 0) {
					tranSum += t.getAmount();
				}
			}
			return savings - tranSum;
		}
		return null;
	}
	
	/**
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
		tLog.add(t);
		sort();
		save();
	}
	
	/**
	 * Returns a transaction given its sorted index
	 * 
	 * @param index The index of a transaction in the sorted list
	 * 
	 * @return The Transaction at the given index in the sorted list
	 */
	public Transaction getTransaction(int index) {
		return tLog.get(index);
	}
	
	/**
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
		Transaction t = tLog.get(index);
		t.setType(type);
		t.setTitle(title);
		t.setAmount(amount);
		t.setDate(date);
		t.setRecur(recur);
		save();
	}
	
	/**
	 * Removes a transaction from the transaction list
	 * 
	 * @param t The transaction to be removed from the transaction list
	 */
	public void removeTransaction(Transaction t) {
		tLog.remove(t);
		for(Category c : cList) {
			if(c.contains(t)) {
				c.removeTransaction(t);
			}
		}
		save();
	}
	
	/**
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
		save();
	}
	
	/**
	 * Edits the category at the given index in the category list
	 * 
	 * @param index The index in the category list of the category being edited
	 * @param name The new name of the Category
	 * @param notes The new set of notes for the Category
	 */
	public void editCategory(int index, String name, String notes) {
		cList.get(index).setName(name);
		cList.get(index).setNotes(notes);
		save();
	}
	
	/**
	 * Removes a category from the category list
	 * 
	 * @param cat The category to remove from the list
	 */
	public void removeCategory(Category cat) {
		cList.remove(cat);
		save();
	}
	
	/**
	 * @return the category at the given index in the category list
	 */
	public Category getCategory(int index) {
		return cList.get(index);
	}
	
	/**
	 * @return The size of the Transaction Log
	 */
	public int size() {
		return tLog.size();
	}
	
	public boolean isInCategory(int index, Category cat) {
		return cat.contains(tLog.get(index));
	}
	
	/**
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
		File f = new File(filename);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			FileInputStream fi = new FileInputStream(new File(filename));
	    	ObjectInputStream oi = new ObjectInputStream(fi);
	    
	    	TransactionLog t = (TransactionLog) oi.readObject();
	    	this.cList = t.cList;
	    	this.id = t.id;
	    	this.tLog = t.tLog;
	    	this.filename = t.filename;
	    	this.currDate = t.currDate;
	    	this.savings = t.savings;

	    	oi.close();
	    	fi.close();
	    	
	    	ArrayList<Transaction> newTrans = new ArrayList<Transaction>();
	    	for(Transaction tr : tLog) {
				if(tr.isRecurring()) {
					Calendar next = Calendar.getInstance();
					next.setTime(tr.getDate());
					next.add(Calendar.DATE, tr.getRecur());
					if(Calendar.getInstance().compareTo(next) >= 0) {
						//Transaction n = new Transaction(tr.getType(), tr.getRecur());
						
					}
				}
			}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * saves the users transactions when one is added or removed
	 */
	public void save() {
		try {
		    FileOutputStream f = new FileOutputStream(new File(this.filename));
		    ObjectOutputStream o = new ObjectOutputStream(f);
		    
		    o.writeObject(this);

		    o.close();
		    f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Internal sort function for the transaction log. Sorts the transactions by date
	 */
	private void sort() {
		tLog.sort(new Comparator<Transaction>() {
			public int compare(Transaction a, Transaction b) {
				return a.getDate().compareTo(b.getDate());
			}
		});
	}


}
