package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.ArrayList;

/*
 * Storage object for the information of each category
 * @author Trae
 */
public class Category {
    ArrayList<Transaction> transactions;
    String name, notes;
    
    public Category() {
    	transactions = new ArrayList<Transaction>();
    }
    public Category(String name, String notes) {
    	transactions = new ArrayList<Transaction>();
    	this.name = name;
    	this.notes = notes;
    }
    
    public void addTransaction(Transaction t) {
    	transactions.add(t);
    }
    
    public void removeTransaction(Transaction t) {
    	transactions.remove(t);
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}