package esc.baylor.edu.groupProject.TransactionObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Each transaction object represents the information for a single unique transaction
 * @author Trae
 *
 */
public class Transaction implements Serializable {
	private static final Logger log = Logger.getLogger(Transaction.class.getName());
	Types type;
	Double amount;
	Date date;
	String title, comment;
	int recur;
	private static final long serialVersionUID = 3L;
	
	public Transaction(Types type, int recur) {
		log.entering(Transaction.class.getName(), "Transaction", new Object[]{type,recur});
		this.type = type;
		this.recur = recur;
		log.exiting(Transaction.class.getName(), "Transaction");
	}

	public Types getType() {
		log.entering(Transaction.class.getName(), "getType");
		log.exiting(Transaction.class.getName(), "getType", type);
		return type;
	}

	public void setType(Types type) {
		log.entering(Transaction.class.getName(), "setType", type);
		log.exiting(Transaction.class.getName(), "setType");
		this.type = type;
	}

	public Double getAmount() {
		log.entering(Transaction.class.getName(), "getAmount");
		log.exiting(Transaction.class.getName(), "getAmount", amount);
		return amount;
	}

	public void setAmount(Double amount) {
		log.entering(Transaction.class.getName(), "setAmount", amount);
		log.entering(Transaction.class.getName(), "setAmount");
		this.amount = amount;
	}

	public Date getDate() {
		log.entering(Transaction.class.getName(), "getDate");
		log.exiting(Transaction.class.getName(), "getDate", date);
		return date;
	}

	public void setDate(Date date) {
		log.entering(Transaction.class.getName(), "setDate", date);
		log.exiting(Transaction.class.getName(), "setDate");
		this.date = date;
	}

	public String getTitle() {
		log.entering(Transaction.class.getName(), "getTitle");
		log.exiting(Transaction.class.getName(), "getTitle", title);
		return title;
	}

	public void setTitle(String title) {
		log.entering(Transaction.class.getName(), "setTitle", title);
		log.exiting(Transaction.class.getName(), "setTitle");
		this.title = title;
	}

	public String getComment() {
		log.entering(Transaction.class.getName(), "getComment");
		log.exiting(Transaction.class.getName(), "getComment", comment);
		return comment;
	}

	public void setComment(String comment) {
		log.entering(Transaction.class.getName(), "setComment", comment);
		log.exiting(Transaction.class.getName(), "setComment");
		this.comment = comment;
	}
	
	public int getRecur() {
		log.entering(Transaction.class.getName(), "getRecur");
		log.exiting(Transaction.class.getName(), "getRecur", recur);
		return recur;
	}

	public void setRecur(int recur) {
		log.entering(Transaction.class.getName(), "setRecur", recur);
		log.exiting(Transaction.class.getName(), "setRecur");
		this.recur = recur;
	}

	public boolean isRecurring() {
		log.entering(Transaction.class.getName(), "isRecurring");
		log.exiting(Transaction.class.getName(), "isRecurring", recur);
		return recur != -1;
	}

	@Override
	public int hashCode() {
		log.entering(Transaction.class.getName(), "hashCode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + recur;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		log.exiting(Transaction.class.getName(), "hashCode",result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.entering(Transaction.class.getName(), "equals");
		if (this == obj) {
			log.exiting(Transaction.class.getName(), "equals", true);
			return true;
		}
		if (obj == null) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (getClass() != obj.getClass()) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null) {
				log.exiting(Transaction.class.getName(), "equals", false);
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (comment == null) {
			if (other.comment != null) {
				log.exiting(Transaction.class.getName(), "equals", false);
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				log.exiting(Transaction.class.getName(), "equals", false);
				return false;
			}
		} else if (!date.equals(other.date)) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (recur != other.recur){
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				log.exiting(Transaction.class.getName(), "equals", false);
				return false;
			}
		} else if (!title.equals(other.title)) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		if (type != other.type) {
			log.exiting(Transaction.class.getName(), "equals", false);
			return false;
		}
		log.exiting(Transaction.class.getName(), "equals", true);
		return true;
	}
}
