package esc.baylor.edu.groupProject.Objects;

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
import java.util.logging.Logger;

import esc.baylor.edu.groupProject.SwingGUI.Login;;

/**
 * The TransactionLog object stores the data for all Transactions and Categories
 * 
 * @author Trae, Timmy
 *
 */
public class TransactionLog implements Serializable {
	private static final Logger log = Logger.getLogger(Transaction.class.getName());
	private ArrayList<Category> cList;
	private ArrayList<Transaction> tLog;
	private String filename;
	private static final long serialVersionUID = 3L;
	private double savings;
	private Date currDate;
	
	/**
	 * Initializes the transaction log and calls for the program to load stored data
	 */
	public TransactionLog() {
		log.entering(Transaction.class.getName(), "TransactionLog");
		filename = Login.user.findFilename();
		tLog = new ArrayList<Transaction>();
		cList = new ArrayList<Category>();
		load();
		log.exiting(Transaction.class.getName(), "TransactionLog");
	}
	
	/**
	 * @return Returns a clone of the collection of transactions associated with this TransactionLog object
	 */
	public Collection<Transaction> getTransactionList(){
		log.entering(Transaction.class.getName(), "getTransactionList");
		log.exiting(Transaction.class.getName(), "getTransactionList", (Collection<Transaction>) tLog.clone());
		return (Collection<Transaction>) tLog.clone();
	}

	/**
	 * @param amount New value of user savings
	 */
	public void setCurrentSavings(double amount, Date date) {
		log.entering(Transaction.class.getName(), "getTransactionList", new Object[] {amount,date});
		currDate = date;
		savings = 0.00;
		savings += amount;
		log.exiting(Transaction.class.getName(), "getTransactionList");
	}
	
	/**
	 * Calculates the users current balance
	 * 
	 * @return A double representation of the users current balance calculated 
	 * by summing transactions after the date set by the balance and subtracting
	 * the current balance
	 */
	public Double getCurrentBalance() {
		log.entering(Transaction.class.getName(), "getCurrentBalance");
		if(currDate != null) {
			double tranSum = 0.00;
			for(Transaction t: tLog) {
				if(t.getDate().compareTo(currDate) >= 0) {
					if(t.getType() == Types.Expense) {
						tranSum -= t.getAmount();
					} else {
						tranSum += t.getAmount();
					}
				}
			}
			log.exiting(Transaction.class.getName(), "getCurrentBalance", savings - tranSum);
			return savings + tranSum;
		}
		log.exiting(Transaction.class.getName(), "getCurrentBalance", null);
		return null;
	}
	
	/**
	 * Creates and new transaction and adds it to the Transaction Log given the Transactions information.
	 * Checks for recurring dates and adds them to the transaction list as well if necessary
	 * 
	 * @param type The enumerated type of the transaction, either Income or Expense
	 * @param title The name of the transaction
	 * @param date The date on which the transaction occurred
	 * @param amount The amount of the transaction
	 * @param recur How often this transaction recurs. Null if it is not recurring
	 */
	public void addTransaction(Types type, String title, Date date, Double amount, Integer recur) {
		log.entering(Transaction.class.getName(), "addTransaction", new Object[] {type,title,date,amount,recur});
		Transaction t = new Transaction(type, recur);
		t.setTitle(title);
		t.setDate(date);
		t.setAmount(amount);
		if(!tLog.contains(t)) tLog.add(t);
		
		if(t.isRecurring()) {
			Calendar next = Calendar.getInstance();
			next.setTime(t.getDate());
			next.add(Calendar.DATE, t.getRecur());
			if(Calendar.getInstance().compareTo(next) >= 0) {
				addTransaction(t.getType(), t.getTitle(), next.getTime(), t.getAmount(), t.getRecur());
				next.add(Calendar.DATE, t.getRecur());
			}
		}
		
		sort();
		log.exiting(Transaction.class.getName(), "addTransaction");
	}
	
	/**
	 * Returns a transaction given its sorted index
	 * 
	 * @param index The index of a transaction in the sorted list
	 * 
	 * @return The Transaction at the given index in the sorted list
	 */
	public Transaction getTransaction(int index) {
		log.entering(Transaction.class.getName(), "getTransaction", index);
		log.exiting(Transaction.class.getName(), "getTransaction", tLog.get(index));
		return tLog.get(index);
	}
	
	/**
	 * Replaces the data of the transaction at the given index with the newly provided data. If the transaction is recurring
	 * it will check for recursive dates and add those to the transaction list as well
	 * 
	 * @param index The index of the transaction in the Transaction Sort list
	 * @param type The type of the transaction
	 * @param title The title of the transaction
	 * @param date The date of the transaction
	 * @param amount The amount of the transaction
	 * @param recur The recurrence of the Transaction in days
	 */
	public void editTransaction(int index, Types type, String title, Date date, Double amount, Integer recur) {
		log.entering(Transaction.class.getName(), "editTransaction", new Object[] {index,type,title,date,amount,recur});
		Transaction t = tLog.get(index);
		t.setType(type);
		t.setTitle(title);
		t.setAmount(amount);
		t.setDate(date);
		t.setRecur(recur);
		
		if(t.isRecurring()) {
			Calendar next = Calendar.getInstance();
			next.setTime(t.getDate());
			next.add(Calendar.DATE, t.getRecur());
			if(Calendar.getInstance().compareTo(next) >= 0) {
				addTransaction(t.getType(), t.getTitle(), next.getTime(), t.getAmount(), t.getRecur());
				next.add(Calendar.DATE, t.getRecur());
			}
		}
		
		log.exiting(Transaction.class.getName(), "editTransaction");
	}
	
	/**
	 * Removes a transaction from the transaction list
	 * 
	 * @param t The transaction to be removed from the transaction list
	 */
	public void removeTransaction(Transaction t) {
		log.entering(Transaction.class.getName(), "removeTransaction", t);
		tLog.remove(t);
		for(Category c : cList) {
			if(c.contains(t)) {
				c.removeTransaction(t);
			}
		}
		log.exiting(Transaction.class.getName(), "removeTransaction");
	}
	
	/**
	 * Adds a category to the category list
	 * 
	 * @param name The name of the category
	 * @param notes Notes for the category
	 */
	public void addCategory(String name, String notes) {
		log.entering(Transaction.class.getName(), "addCategory", new Object[] {name,notes});
		Category c = new Category(name, notes);
		cList.add(c);
		log.exiting(Transaction.class.getName(), "addCategory");
	}
	
	/**
	 * Edits the category at the given index in the category list
	 * 
	 * @param index The index in the category list of the category being edited
	 * @param name The new name of the Category
	 * @param notes The new set of notes for the Category
	 */
	public void editCategory(int index, String name, String notes) {
		log.entering(Transaction.class.getName(), "editCategory", new Object[] {index,name,notes});
		cList.get(index).setName(name);
		cList.get(index).setNotes(notes);
		log.exiting(Transaction.class.getName(), "editCategory");
	}
	
	/**
	 * Removes a category from the category list
	 * 
	 * @param cat The category to remove from the list
	 */
	public void removeCategory(Category cat) {
		log.entering(Transaction.class.getName(), "removeCategory", cat);
		cList.remove(cat);
		log.exiting(Transaction.class.getName(), "removeCategory");
	}
	
	/**
	 * @return the category at the given index in the category list
	 */
	public Category getCategory(int index) {
		log.entering(Transaction.class.getName(), "getCategory", index);
		log.exiting(Transaction.class.getName(), "getCategory", cList.get(index));
		return cList.get(index);
		
	}
	
	/**
	 * @return The size of the Transaction Log
	 */
	public int size() {
		log.entering(Transaction.class.getName(), "size");
		log.exiting(Transaction.class.getName(), "size", tLog.size());
		return tLog.size();
	}
	
	/**
	 * 
	 * @param index The index of a Transaction in the list of Transactions
	 * @param cat A Category from the Transaction Log
	 * @return True if the Transaction is in the given Category; false otherwise
	 */
	public boolean isInCategory(int index, Category cat) {
		log.entering(Transaction.class.getName(), "isInCategory", new Object[] {index,cat});
		log.exiting(Transaction.class.getName(), "isInCategory", cat.contains(tLog.get(index)));
		return cat.contains(tLog.get(index));
	}
	
	/**
	 * 
	 * @param name The name of the category to check for.
	 * @return True if a Category with the given name exists; false if not
	 */
	public boolean categoryExists(String name) {
		log.entering(Transaction.class.getName(), "categoryExists");
		for(Category c : cList) {
			if(c.getName().equalsIgnoreCase(name)) {
				log.exiting(Transaction.class.getName(), "categoryExists", true);
				return true;
			}
		}
		log.exiting(Transaction.class.getName(), "categoryExists", false);
		return false;
	}
	
	/**
	 * @return The number of categories associated with the transaction log
	 */
	public int categoryCount() {
		log.entering(Transaction.class.getName(), "categoryCount");
		log.exiting(Transaction.class.getName(), "categoryExists", cList.size());
		return cList.size();
	}
	
	/**
	 * Loads the users saved transactions to the log
	 */
	private void load() {
		log.entering(Transaction.class.getName(), "load");
		File f = new File(filename);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				log.throwing(Transaction.class.getName(), "load", e);
				e.printStackTrace();
			}
			log.exiting(Transaction.class.getName(), "load");
			return;
		}
		try {

			log.info("Loading \"" + filename + "\"...");
			
			FileInputStream fi = new FileInputStream(f);
	    	ObjectInputStream oi = new ObjectInputStream(fi);
	    
	    	TransactionLog t = (TransactionLog) oi.readObject();
	    	this.cList = t.cList;
	    	this.tLog = t.tLog;
	    	this.filename = t.filename;
	    	this.currDate = t.currDate;
	    	this.savings = t.savings;

	    	oi.close();
	    	fi.close();
	    	log.info("Successfully loaded \"" + filename + "\"");
	    	

	    	log.info("Parsing log for recursive Transactions...");

			ArrayList<Transaction> trans = (ArrayList<Transaction>) tLog.clone();
	    	for(Transaction tr : trans) {
				if(tr.isRecurring()) {
					Calendar next = Calendar.getInstance();
					next.setTime(tr.getDate());
					next.add(Calendar.DATE, tr.getRecur());
					while(Calendar.getInstance().compareTo(next) >= 0) {
						addTransaction(tr.getType(), tr.getTitle(), next.getTime(), tr.getAmount(), tr.getRecur());
						next.add(Calendar.DATE, tr.getRecur());
					}
				}
			}
	    	log.info("Successfully parsed all loaded Transactions");
	    	
		} catch (IOException e) {
			log.throwing(Transaction.class.getName(), "load", e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.throwing(Transaction.class.getName(), "load", e);
			e.printStackTrace();
		}

		log.exiting(Transaction.class.getName(), "load");
	}
	
	/**
	 * saves the users transactions when one is added or removed
	 */
	public void save() {
		log.entering(Transaction.class.getName(), "save");
		try {
			log.info("Saving \"" + filename + "\"...");
		    FileOutputStream f = new FileOutputStream(new File(this.filename));
		    ObjectOutputStream o = new ObjectOutputStream(f);
		    
		    o.writeObject(this);

		    o.close();
		    f.close();
		    log.info("Successfully saved \"" + filename + "\"");
		} catch (Exception e) {
			log.throwing(Transaction.class.getName(), "save", e);
			e.printStackTrace();
		}
		log.exiting(Transaction.class.getName(), "save");
	}
	
	/**
	 * Internal sort function for the transaction log. Sorts the transactions by date
	 */
	private void sort() {
		log.entering(Transaction.class.getName(), "sort");
		tLog.sort(new Comparator<Transaction>() {
			public int compare(Transaction a, Transaction b) {
				return a.getDate().compareTo(b.getDate());
			}
		});
		log.exiting(Transaction.class.getName(), "sort");
	}
}
