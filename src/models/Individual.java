package models;

public class Individual extends Subscriber {
	
	String creaditCardNr;
	int expireeMonth;
	int expireYear;
	int CCV;
	
	
	
	public Individual() {
	}


	public Individual(String name, String address, String creaditCardNr, int expireeMonth, int expireYear, int cCV) {
		super(name, address);
		this.creaditCardNr = creaditCardNr;
		this.expireeMonth = expireeMonth;
		this.expireYear = expireYear;
		CCV = cCV;
	}


	public Individual(String name, String address) {
		super(name, address);
	}
	
	
	@Override
	String getBillingInformation() {
		// TODO Auto-generated method stub
		return "Individual";
	}


	public String getCreaditCardNr() {
		return creaditCardNr;
	}


	public void setCreaditCardNr(String creaditCardNr) {
		this.creaditCardNr = creaditCardNr;
	}


	public int getExpireeMonth() {
		return expireeMonth;
	}


	public void setExpireeMonth(int expireeMonth) {
		this.expireeMonth = expireeMonth;
	}


	public int getExpireYear() {
		return expireYear;
	}


	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}


	public int getCCV() {
		return CCV;
	}


	public void setCCV(int cCV) {
		CCV = cCV;
	}


	@Override
	public String toString() {
		return "Individual [creaditCardNr=" + creaditCardNr + ", expireeMonth=" + expireeMonth + ", expireYear="
				+ expireYear + ", CCV=" + CCV + "]";
	}
	
	

}
