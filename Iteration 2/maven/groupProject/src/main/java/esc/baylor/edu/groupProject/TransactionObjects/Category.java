package esc.baylor.edu.groupProject.TransactionObjects;

import java.util.ArrayList;

/*
 * Storage object for the information of each category,
 * including the parent and sub-categories
 * @author Trae
 */
public class Category {
	Category parent = null;
    ArrayList<Category> children = null;
    ArrayList<Transaction> transactions;
    String name, notes;
    
    public Category() {
    	children = new ArrayList<Category>();
    	transactions = new ArrayList<Transaction>();
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
}