package com.expense;

public class ExpenseLog {
	private String piadby;
	private String owedBy;
	private double amount;
	public ExpenseLog(String piadby, String owedBy, double amount) {
		this.piadby = piadby;
		this.owedBy = owedBy;
		this.amount = amount;
	}
	
	public String getPiadby() {
		return piadby;
	}
	public void setPiadby(String piadby) {
		this.piadby = piadby;
	}
	public String getOwedBy() {
		return owedBy;
	}
	
	public void setOwedBy(String owedBy) {
		this.owedBy = owedBy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((owedBy == null) ? 0 : owedBy.hashCode());
		result = prime * result + ((piadby == null) ? 0 : piadby.hashCode());
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
		ExpenseLog other = (ExpenseLog) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (owedBy == null) {
			if (other.owedBy != null)
				return false;
		} else if (!owedBy.equals(other.owedBy))
			return false;
		if (piadby == null) {
			if (other.piadby != null)
				return false;
		} else if (!piadby.equals(other.piadby))
			return false;
		return true;
	}

}
