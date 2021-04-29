package esc.baylor.edu.groupProject.Objects;

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
	String title;
	int recur;
	private static final long serialVersionUID = 3L;
	
	/**
	 * Constructs a Transaction of the given type with the given recursion period
	 * 
	 * @param type A Transaction Type. Either expense or Income
	 * @param recur Recursion period in days. -1 if no recursion
	 */
	public Transaction(Types type, int recur) {
		log.entering(Transaction.class.getName(), "Transaction", new Object[]{type,recur});
		this.type = type;
		this.recur = recur;
		log.exiting(Transaction.class.getName(), "Transaction");
	}

	/**
	 * Gets the type of Transaction
	 * 
	 * @return The Type of the Associated Transaction. Either Expense or Income
	 */
	public Types getType() {
		log.entering(Transaction.class.getName(), "getType");
		log.exiting(Transaction.class.getName(), "getType", type);
		return type;
	}

	/**
	 * Changes the Type of the Transaction to the given Type
	 * 
	 * @param type A Transaction Type. Either Expense or Income
	 */
	public void setType(Types type) {
		log.entering(Transaction.class.getName(), "setType", type);
		log.exiting(Transaction.class.getName(), "setType");
		this.type = type;
	}

	/**
	 * Gets the amount of the Transaction
	 * 
	 * @return The amount of the Transaction
	 */
	public Double getAmount() {
		log.entering(Transaction.class.getName(), "getAmount");
		log.exiting(Transaction.class.getName(), "getAmount", amount);
		return amount;
	}

	/**
	 * Sets the amount of the Transaction
	 * 
	 * @param amount An amount of money. Must be positive
	 */
	public void setAmount(Double amount) {
		log.entering(Transaction.class.getName(), "setAmount", amount);
		log.entering(Transaction.class.getName(), "setAmount");
		this.amount = amount;
	}

	/**
	 * Sets the Date of the Transaction
	 * 
	 * @return The Date of the Transaction
	 */
	public Date getDate() {
		log.entering(Transaction.class.getName(), "getDate");
		log.exiting(Transaction.class.getName(), "getDate", date);
		return date;
	}

	
	/**
	 * Changes the Date of the Transaction to the new given Date
	 * 
	 * @param date The new Date
	 */
	public void setDate(Date date) {
		log.entering(Transaction.class.getName(), "setDate", date);
		log.exiting(Transaction.class.getName(), "setDate");
		this.date = date;
	}

	/**
	 * Gets the Title of the Transaction
	 * @return The title of the Transaction
	 */
	public String getTitle() {
		log.entering(Transaction.class.getName(), "getTitle");
		log.exiting(Transaction.class.getName(), "getTitle", title);
		return title;
	}

	/**
	 * Changes the Title of a Transaction
	 * 
	 * @param title The new title
	 */
	public void setTitle(String title) {
		log.entering(Transaction.class.getName(), "setTitle", title);
		log.exiting(Transaction.class.getName(), "setTitle");
		this.title = title;
	}
	
	/**
	 * Gets the rate of recurrence for a Transactions
	 * 
	 * @return How often the Transaction recurs in days
	 */
	public int getRecur() {
		log.entering(Transaction.class.getName(), "getRecur");
		log.exiting(Transaction.class.getName(), "getRecur", recur);
		return recur;
	}

	/**
	 * Changes the recursion period to the newly given value
	 * 
	 * @param recur Recursion period in days
	 */
	public void setRecur(int recur) {
		log.entering(Transaction.class.getName(), "setRecur", recur);
		log.exiting(Transaction.class.getName(), "setRecur");
		this.recur = recur;
	}

	/**
	 * Checks if a Transaction recurs
	 * 
	 * @return True if the Transaction recurs; false otherwise
	 */
	public boolean isRecurring() {
		log.entering(Transaction.class.getName(), "isRecurring");
		log.exiting(Transaction.class.getName(), "isRecurring", recur);
		return recur != -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		log.entering(Transaction.class.getName(), "hashCode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + recur;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		log.exiting(Transaction.class.getName(), "hashCode",result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
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
