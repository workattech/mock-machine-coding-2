public class Pair {
	//currently keeping it String, it can be User object as well
	private String lender;
	private String borrower;

	Pair(String lender, String borrower) {
		this.lender = lender;
		this.borrower = borrower;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + ((lender == null) ? 0 : lender.hashCode());
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
		Pair other = (Pair) obj;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (lender == null) {
			if (other.lender != null)
				return false;
		} else if (!lender.equals(other.lender))
			return false;
		return true;
	}




	public String getLender() {
		return lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

}