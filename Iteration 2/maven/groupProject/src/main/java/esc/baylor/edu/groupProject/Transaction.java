package esc.baylor.edu.groupProject;

import java.util.Date;

public class Transaction {
	Double amount;
	Date date;
	String title;
	private static int index = -1;
	
	public Transaction() {
		++index;
	}
	
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
	public int getIndex() {
		return index;
	}
}
