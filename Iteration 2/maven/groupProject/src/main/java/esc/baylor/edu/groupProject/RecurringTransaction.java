package esc.baylor.edu.groupProject;

public class RecurringTransaction extends Transaction {
	
	int recurrence;
	
	public RecurringTransaction(Type type, int recurrence) {
		super(type);
		this.recurrence = recurrence;
	}

	public int getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(int recurrence) {
		this.recurrence = recurrence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + recurrence;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecurringTransaction other = (RecurringTransaction) obj;
		if (recurrence != other.recurrence)
			return false;
		return true;
	}


}
