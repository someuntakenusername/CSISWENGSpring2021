package esc.baylor.edu.groupProject.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Storage object for the information of each category
 * 
 * @author Trae
 */
public class Category implements Serializable {
	private static final Logger log = Logger.getLogger(Category.class.getName());
	private static final long serialVersionUID = 4L;
    ArrayList<Transaction> transactions;
    String name;
    ArrayList<String> notes;
    /**
     * Constructs a Category
     * @param name The name of the new Category
     * @param notes Any of the notes for the Category
     */
    public Category(String name, String notes) {
    	log.entering(Category.class.getName(), "Category", new Object[]{name,notes});
    	transactions = new ArrayList<Transaction>();
    	this.name = name;
    	this.notes = new ArrayList<String>();
    	if(!notes.contains("\n")) this.notes.add(notes);
    	else {
    		Scanner sc = new Scanner(notes);
    		sc.useDelimiter("\n");
    		while(sc.hasNext()) {
    			this.notes.add(sc.next());
    		}
    		sc.close();
    	}
    	log.exiting(Category.class.getName(), "Category");
    }
    
    /**
     * Adds a Transaction to a Category object
     * 
     * @param t The transaction to be added to the Category
     */
    public void addTransaction(Transaction t) {
    	log.entering(Category.class.getName(), "addTransaction", t);
    	transactions.add(t);
    	transactions.sort(new Comparator<Transaction>() {
			public int compare(Transaction a, Transaction b) {
				return a.getDate().compareTo(b.getDate());
			}
		});
    	log.exiting(Category.class.getName(), "addTransaction");
    }
    
    /**
     * Removes a Transaction from a Category object
     * 
     * @param t The Transaction object to be removed from the Category
     */
    public void removeTransaction(Transaction t) {
    	log.entering(Category.class.getName(), "removeTransaction", t);
    	transactions.remove(t);
    	log.exiting(Category.class.getName(), "removeTransaction");
    }
    
    /**
     * Gets a Transaction from the Category
     * 
     * @param index The index of a Transaction in the Categories Transaction list
     * 
     * @return The Transaction object at the index in the list
     */
    public Transaction getTransaction(int index) {
    	log.entering(Category.class.getName(), "getTransaction", index);
    	log.exiting(Category.class.getName(), "getTransaction", transactions.get(index));
    	return transactions.get(index);
    }
    
    /**
     * Gives the size of the Category
     * 
     * @return The number of Transactions in the Category
     */
    public int size() {
    	log.entering(Category.class.getName(), "size");
    	log.exiting(Category.class.getName(), "size", transactions.size());
    	return transactions.size();
    }
    
    /**
     * Determines if a Transaction is contained in a Category
     * 
     * @param t A Transaction object
     * @return True if the Category contains the Transaction; false otherwise
     */
    public boolean contains(Transaction t) {
    	log.entering(Category.class.getName(), "contains", t);
    	log.exiting(Category.class.getName(), "contains", transactions.contains(t));
    	return transactions.contains(t);
    }
    
    /**
     * Gets the name of the Category
     * @return The name of the Category
     */
	public String getName() {
		log.entering(Category.class.getName(), "getName");
		log.exiting(Category.class.getName(), "getName", name);
		return name;
	}
	
	/**
	 * Sets the name of the Category
	 * 
	 * @param name The new name of the Category object
	 */
	public void setName(String name) {
		log.entering(Category.class.getName(), "setName", name);
		log.exiting(Category.class.getName(), "setName");
		this.name = name;
	}
	
	/**
	 * Gets the notes of a Category
	 * 
	 * @return The notes of a Category. Will be returned as a semi-colon separated string
	 */
	public String getNotes() {
		log.entering(Category.class.getName(), "getNotes");
		String ret = notes.get(0);
		for(int i = 1; i < notes.size(); ++i) {
			ret+= "; ";
			ret+= notes.get(i);
		}
		log.exiting(Category.class.getName(), "getNotes", notes);
		return ret;
	}
	
	/**
	 * Sets the notes of a Category
	 * 
	 * @param notes The string to set as the notes of the Category
	 */
	public void setNotes(String notes) {
		log.entering(Category.class.getName(), "setNotes", notes);
		this.notes.clear();
		if(!notes.contains("\n")) this.notes.add(notes);
    	else {
    		Scanner sc = new Scanner(notes);
    		sc.useDelimiter("\n");
    		while(sc.hasNext()) {
    			this.notes.add(sc.next());
    		}
    		sc.close();
    	}
		log.exiting(Category.class.getName(), "setNotes");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		log.entering(Category.class.getName(), "toString");
		log.exiting(Category.class.getName(), "toString", name);
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		log.entering(Category.class.getName(), "hashCode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		log.exiting(Category.class.getName(), "hashCode", result);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		log.entering(Category.class.getName(), "equals", obj);
		if (this == obj) {
			log.entering(Category.class.getName(), "equals", true);
			return true;
		}
		if (obj == null) {
			log.entering(Category.class.getName(), "equals", false);
			return false;
		}
		if (getClass() != obj.getClass()) {
			log.entering(Category.class.getName(), "equals", false);
			return false;
		}
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				log.entering(Category.class.getName(), "equals", false);
				return false;
		} else if (!name.equals(other.name)) {
			log.entering(Category.class.getName(), "equals", false);
			return false;
		}
		log.entering(Category.class.getName(), "equals", true);
		return true;
	}
}