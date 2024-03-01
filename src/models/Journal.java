package models;

import java.util.Vector;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Journal {
	
	private String name;
	private String issn;
	private int frequency;
	private double issuePrice;
	private Vector<Subscription> subscriptions;
	
	
	
	public Journal() {
	}


	public Journal(String name, String issn, int frequency, double issuePrice) {
		super();
		this.name = name;
		this.issn = issn;
		this.frequency = frequency;
		this.issuePrice = issuePrice;
	}
	

	void addSubscription(Subscription subscription) {
		if(this.subscriptions == null)
			this.subscriptions=new Vector();
		if(subscription.getSubscriber()!=null && subscription.getJournal() !=null)
			subscriptions.add(subscription);
	}
	
	Subscription searchSubscriberSubscription(Subscriber subscriber) {
		if(this.subscriptions != null &&  !this.subscriptions.isEmpty()) {
			for(Subscription subscription:this.subscriptions) {
				Subscriber thisSubscriber=subscription.getSubscriber();
				if(subscriber.equals(thisSubscriber)) {
					return subscription;
				}
			}
		}
		return null;
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


	@Override
	public String toString() {
		
		return "Journal [name=" + name + ", issn=" + issn + ", frequency=" + frequency + ", issuePrice=" + issuePrice
				+ ", subscriptions=" + subscriptions + "]";
	}
	
	
	
	
	
	
	
}
