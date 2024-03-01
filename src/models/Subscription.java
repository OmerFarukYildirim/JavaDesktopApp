package models;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Subscription {
	private DateInfo dates;
	private PaymentInfo payment;
	private int copies;
	@JsonIgnore
	private Journal journal;
	private Subscriber subscriber;
	private String journalIssn;
	
	public Subscription() {
	}

	public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber) {
		this.dates = dates;
		this.copies = copies;
		this.journal = journal;
		this.subscriber = subscriber;
		if(journal!=null)
			setJournalIssn(journal.getIssn());
	}
	
	void acceptPayment(double amount) {
		
	}
	
	public boolean canSend(int issueMonth,int issueYear) {
		DateInfo dateInfo=getDates();
		int startYear=dateInfo.getStartYear();
		int startMonth=dateInfo.getStartMonth();
		int endMonth=dateInfo.getEndMonth();
		double receivedPayment=getPayment().getReceivedPayment();
		boolean isDateOk=(startYear==issueYear &&  issueMonth >= startMonth) || ((startYear+1)==issueYear &&  issueMonth <= endMonth);
		boolean isPaymentOk =receivedPayment==0;
		return isDateOk && isPaymentOk;

	}

	public DateInfo getDates() {
		return dates;
	}

	public void setDates(DateInfo dates) {
		this.dates = dates;
	}

	public PaymentInfo getPayment() {
		return payment;
	}

	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public Journal getJournal() {
		if(journal == null) {
			Journal searchJournal=Distributor.searchJournal(journalIssn);
			this.journal=searchJournal;  
		}
		return this.journal;
	}

	public void setJournal(Journal journal) {
		if(journal !=null) {
			setJournalIssn(journal.getIssn());
		}
		this.journal = journal;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subcriber) {
		this.subscriber = subcriber;
	}
	
	

	public String getJournalIssn() {
		return journalIssn;
	}

	public void setJournalIssn(String journalIssn) {
		if(this.journal == null) {
			setJournal(Distributor.searchJournal(journalIssn)); 
		}
		this.journalIssn = journalIssn;
	}

	@Override
	public String toString() {
		return "Subscription [dates=" + dates + ", payment=" + payment + ", copies=" + copies + ", journal=" + journal
				+ ", subscriber=" + subscriber + "]";
	}
	
	
}
