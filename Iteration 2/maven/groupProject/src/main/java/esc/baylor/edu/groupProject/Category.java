package esc.baylor.edu.groupProject;

import java.util.ArrayList;

public class Category {
    Category parent = null;
    String name, notes;
    
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
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
	public void addTransaction(Transaction t) {
		index.add(t.getId());
	}
}
