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
    	}
    	log.exiting(Category.class.getName(), "Category");
    }
    
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
    
    public void removeTransaction(Transaction t) {
    	log.entering(Category.class.getName(), "removeTransaction", t);
    	transactions.remove(t);
    	log.exiting(Category.class.getName(), "removeTransaction");
    }
    
    public Transaction getTransaction(int index) {
    	log.entering(Category.class.getName(), "getTransaction", index);
    	log.exiting(Category.class.getName(), "getTransaction", transactions.get(index));
    	return transactions.get(index);
    }
    
    public int size() {
    	log.entering(Category.class.getName(), "size");
    	log.exiting(Category.class.getName(), "size", transactions.size());
    	return transactions.size();
    }
    
    public boolean contains(Transaction t) {
    	log.entering(Category.class.getName(), "contains", t);
    	log.exiting(Category.class.getName(), "contains", transactions.contains(t));
    	return transactions.contains(t);
    }
    
	public String getName() {
		log.entering(Category.class.getName(), "getName");
		log.exiting(Category.class.getName(), "getName", name);
		return name;
	}
	public void setName(String name) {
		log.entering(Category.class.getName(), "setName", name);
		log.exiting(Category.class.getName(), "setName");
		this.name = name;
	}
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
    	}
		log.exiting(Category.class.getName(), "setNotes");
	}
	
	@Override
	public String toString() {
		log.entering(Category.class.getName(), "toString");
		log.exiting(Category.class.getName(), "toString", name);
		return name;
	}
	@Override
	public int hashCode() {
		log.entering(Category.class.getName(), "hashCode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		log.exiting(Category.class.getName(), "hashCode", result);
		return result;
	}
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