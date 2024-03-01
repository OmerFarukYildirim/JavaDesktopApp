package nypProject;
// JSon serileştirmesinden dışlamak için
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
		//Journal null değilse issn numarasını bu this.journala atama yapar
		if(journal!=null)
			setJournalIssn(journal.getIssn());
	}
	
	public Subscription(Subscriber subscriber2, Journal journal2) {
		
	}

	void acceptPayment(double amount) {
		
	}
	
	// Gönderim yapılıp yapılamayacağını kontrol eden metot
	public boolean canSend(int issueMonth, int issueYear) {
	    // Aboneliğin başlangıç ve bitiş tarih bilgileri alınır
	    DateInfo dateInfo = getDates();
	    int startYear = dateInfo.getStartYear();
	    int startMonth = dateInfo.getStartMonth();
	    int endMonth = dateInfo.getEndMonth();

	    // Abonelik başlangıç yılı ve bitiş yılı kontrolü yapılır
	    // Gönderim ayı, başlangıç ayından büyük veya bitiş ayından küçük olmalıdır
	    boolean isDateOk = (startYear == issueYear && issueMonth >= startMonth) ||
	                       ((startYear + 1) == issueYear && issueMonth <= endMonth);

	    // Ödeme kontrolü yapılır; alınan ödeme 0 ise gönderim yapılabilir
	    double receivedPayment = getPayment().getReceivedPayment();
	    boolean isPaymentOk = receivedPayment == 0;

	    // Hem tarih hem de ödeme koşulları sağlanıyorsa true, aksi halde false döner
	    return isDateOk && isPaymentOk;
	}

	// Nesneyi String olarak temsil eden metot
	@Override
	public String toString() {
	    return "Subscription [dates=" + dates + ", payment=" + payment + ", copies=" + copies + ", journal=" + journal
	            + ", subscriber=" + subscriber + "]";
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
}
