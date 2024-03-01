package nypProject;

public class Corporation extends Subscriber {
	
	// Boş constructor
	public Corporation() {
	}

	// İsim ve adres parametreleriyle constructor
	public Corporation(String name, String address) {
	    super(name, address);
	}

	// toString metodu, nesnenin bilgilerini String olarak döndürür
	@Override
	public String toString() {
	    return "Corporation [bankCode=" + bankCode + ", bankName=" + bankName + ", issueDay=" + issueDay
	            + ", issueMonth=" + issueMonth + ", issueYear=" + issueYear + ", accountNumber=" + accountNumber + "]";
	}

	int bankCode;
	String bankName;
	int issueDay;
	int issueMonth;
	int issueYear;
	int accountNumber;
	@Override
	// Abstract metod
	String getBillingInformation() {
		return "Corporation";
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(int issueDay) {
		this.issueDay = issueDay;
	}
	public int getIssueMonth() {
		return issueMonth;
	}
	public void setIssueMonth(int issueMonth) {
		this.issueMonth = issueMonth;
	}
	public int getIssueYear() {
		return issueYear;
	}
	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
}