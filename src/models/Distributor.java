package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.codehaus.jackson.map.ObjectMapper;

public class Distributor {
	private static Hashtable<String,Journal> journals;
    private Vector<Subscriber> subscribers;
	
	
	
	public Distributor() {
		journals=new Hashtable<String, Journal>();
		subscribers=new Vector<Subscriber>();
	}
	public  boolean addJournal(Journal journal) {
		
		if(journals.put(journal.getIssn(), journal)!=null)
			return true;
		return false;
	}
	public static Journal searchJournal(String issn) {
		return journals.get(issn);
	}
	public boolean addSubscriber(Subscriber subscriber) {
		return subscribers.add(subscriber);
	}
	public Subscriber searchSubscriber(String name) {
		for(Subscriber subscriber : subscribers) {
			if(name.equals(subscriber.getName())) {
				return subscriber;
			}
		}
		return null;
	}
	
	public boolean addSubscription(String issn,Subscription subscription) {
		Journal journal=searchJournal(issn);
		Subscriber subscriber=subscription.getSubscriber();
		if(subscriber !=null) {
			Subscription hasSubscription=journal.searchSubscriberSubscription(subscriber);
			if(hasSubscription != null) {
				hasSubscription.setCopies(hasSubscription.getCopies()+1);
				return true;
			}else {
				journal.addSubscription(subscription);
			}
		}else {
			journal.addSubscription(subscription);
		}
		return true;
	}
	
	void listAllSendingOrders(int month,int year) {
		
		
	}
	void listSendingOrders(String issn,int month,int year) {
		
	}
	void listInCompletePayments() {
		
	}

    public Vector<Subscription> listSubscriptions(String searchKey) {
    	Vector<Subscription> subscriptionList=new Vector<Subscription>();
    	if(isNumeric(searchKey)) {
    		//issn
    		Journal journal=searchJournal(searchKey);
    		if(journal !=null) {
    			subscriptionList.addAll(journal.getSubscriptions());
    		}
    	}else {
    		//Subscriber Name
    		Subscriber subscriber=searchSubscriber(searchKey);
    		if(subscriber !=null) {
	    		for(Journal journal:getJournals().values()) {
	    			Vector<Subscription> journalSubscription=journal.getSubscriptions();
	    			for(Subscription js:journalSubscription) {
	    				if(subscriber.getName().equals(js.getSubscriber().getName())) {
	    					subscriptionList.add(js);
	    				}
	    			}
	    		}
    		}
    	}
        return subscriptionList;
    }


	public void saveState(String fileName) {
		 try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(new File(fileName), this);
	            System.out.println("Distributor nesnesi JSON formatına çevrildi ve dosyaya yazıldı.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	public void loadState(String fileName) {
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            Distributor distributor = objectMapper.readValue(new File(fileName), Distributor.class);
            setJournals(distributor.getJournals());
            setSubscribers(distributor.getSubscribers());
            System.out.println("Distributor nesnesi dosyadan okundu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	void report() {
		
	}
	public Hashtable<String, Journal> getJournals() {
		return journals;
	}
	public void setJournals(Hashtable<String, Journal> journals) {
		this.journals = journals;
	}
	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Vector<Subscriber> subscribes) {
		this.subscribers = subscribes;
	}
	
	public static boolean isNumeric(String str) {
	    return str.matches("-?\\d+(\\.\\d+)?");
	}
}
