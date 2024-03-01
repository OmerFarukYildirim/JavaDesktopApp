package nypProject;

public class Individual extends Subscriber {
	
	String creaditCardNr;
	int expireeMonth;
	int expireYear;
	int CCV;
	

	public Individual() { // parametre almayan constructor
	}
	
	public Individual(String name, String address, String creaditCardNr, int expireeMonth, int expireYear, int cCV) {
		super(name, address); // extend ettigim sinifin constuructorun parametreleri ve Individual sinifinin parametrelerini 
		this.creaditCardNr = creaditCardNr;// alan constructor
		this.expireeMonth = expireeMonth;
		this.expireYear = expireYear;
		CCV = cCV;
	}


	public Individual(String name, String address) {//extend ettigim sinifin constuructorun parametrelerini alan constructor
		super(name, address);
	}
	
	
	
	String getBillingInformation() {
		return "Individual";
	}


	public String getCreaditCardNr() {//Kredi karti numarasini getirir
		return creaditCardNr;
	}


	public void setCreaditCardNr(String creaditCardNr) {// kredi kartı numarasına atama yapar
		this.creaditCardNr = creaditCardNr;
	}


	public int getExpireeMonth() {// son ayı getirir
		return expireeMonth;
	}


	public void setExpireeMonth(int expireeMonth) {// son aya atama yapar
		this.expireeMonth = expireeMonth;
	}


	public int getExpireYear() {// son yılı getirir
		return expireYear;
	}


	public void setExpireYear(int expireYear) {// son yıla atama yapar
		this.expireYear = expireYear;
	}


	public int getCCV() {// ccv yi getirir
		return CCV;
	}


	public void setCCV(int cCV) {// ccv ye atama yapar
		CCV = cCV;
	}


	@Override
	public String toString() {// kredi kartı numarası, son ay, son yıl, ccv bilgilerini yazdırır
		return "Individual [creaditCardNr=" + creaditCardNr + ", expireeMonth=" + expireeMonth + ", expireYear="
				+ expireYear + ", CCV=" + CCV + "]";
	}
	
	

}
