package nypProject;

import java.util.Vector;

public class Journal {
	
	private String name;
	private String issn;
	private int frequency;
	private double issuePrice;
	private Vector<Subscription> subscriptions;
	
	// Boş constructor
	public Journal() {
	}

	// İsim ve ISSN parametreleriyle constructor
	public Journal(String name, String issn) {
	    super();
	    this.name = name;
	    this.issn = issn;
	}

	// Tüm özellikleri içeren constructor
	public Journal(String name, String issn, int frequency, double issuePrice) {
	    super();
	    this.name = name;
	    this.issn = issn;
	    this.frequency = frequency;
	    this.issuePrice = issuePrice;
	}

	// Abone ekleme metod
	void addSubscription(Subscription subscription) {

	    // Eğer abonelik listesi null ise, yeni bir Vector oluştur
	    if(this.subscriptions == null)
	        this.subscriptions = new Vector<>();

	    // Abonelik nesnesi ve ilgili journal null değilse, aboneliği ekle
	    if(subscription.getSubscriber()!=null && subscription.getJournal() !=null)
	        subscriptions.add(subscription);
	}

	// Belirli bir abonenin aboneliğini arama metod
	Subscription searchSubscriberSubscription(Subscriber subscriber) {
	    // Abonelik listesi boş değilse ve içeriyorsa
	    if(this.subscriptions != null &&  !this.subscriptions.isEmpty()) {
	        for(Subscription subscription:this.subscriptions) {
	            Subscriber thisSubscriber=subscription.getSubscriber();
	            // Aranan aboneden eşleşen bir abonelik bulunursa, aboneliği döndür
	            if(subscriber.equals(thisSubscriber)) {
	                return subscription;
	            }
	        }
	    }
	    // Eşleşen abonelik bulunamazsa null döndür
	    return null;
	}

	// Getter ve Setter metotları

	// toString metodu, nesnenin bilgilerini String olarak döndürür
	@Override
	public String toString() {
	    return "Journal [name=" + name + ", issn=" + issn + ", frequency=" + frequency + ", issuePrice=" + issuePrice
	            + ", subscriptions=" + subscriptions + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIssn() {
		return issn;
	}


	public void setIssn(String issn) {
		this.issn = issn;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public double getIssuePrice() {
		return issuePrice;
	}


	public void setIssuePrice(double issuePrice) {
		this.issuePrice = issuePrice;
	}


	public Vector<Subscription> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(Vector<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}