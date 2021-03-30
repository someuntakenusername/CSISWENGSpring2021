package esc.baylor.edu.groupProject;

import java.util.Date;

public class Transaction {
	Double amount;
	Date date;
	String title, comment;
	int id;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

}
