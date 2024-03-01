package nypProject;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.codehaus.jackson.map.ObjectMapper;

public class Distributor {
	private static Hashtable<String,Journal> journals;
    private Vector<Subscriber> subscribers;
	
	
    public Distributor() {
        journals = new Hashtable<String, Journal>();
        subscribers = new Vector<Subscriber>();
    }

    // Yeni bir dergi ekler
    public boolean addJournal(Journal journal) {
        // Dergi, issn numarasına göre aranır
        Journal hasJournal = searchJournal(journal.getIssn());

        // Eğer aynı issn numarasına sahip dergi yoksa ve eklenme başarılıysa true döner
        if (hasJournal == null && journals.put(journal.getIssn(), journal) != null)
            return true;
        return false;
    }

    // Issn numarasına göre dergi arar
    public static Journal searchJournal(String issn) {
        return journals.get(issn);
    }

    // Yeni bir abone ekler
    public boolean addSubscriber(Subscriber subscriber) {
        return subscribers.add(subscriber);
    }

    // Verilen parametredeki isimle abone arar
    public Subscriber searchSubscriber(String name) {
        for (Subscriber subscriber : subscribers) {
            if (name.equals(subscriber.getName())) {
                return subscriber;
            }
        }
        return null;
    }

    // Bir aboneliği ekler
    public boolean addSubscription(String issn, Subscription subscription) {
    	// issn si verilen journali bulur
        Journal journal = searchJournal(issn);
        // aboneligi verilen aboneyi cagirir
        Subscriber subscriber = subscription.getSubscriber();

        // Eğer abone null değilse
        if (subscriber != null) {
            // Abonenin mevcut bir aboneliği varsa, kopya sayısını artırır
            Subscription hasSubscription = journal.searchSubscriberSubscription(subscriber);
            if (hasSubscription != null) {
                hasSubscription.setCopies(hasSubscription.getCopies() + 1);
                return true;
            } else {
                // Yeni bir abonelik ekler
                journal.addSubscription(subscription);
            }
        } else {
            // Abone null ise yine yeni bir abonelik ekler
            journal.addSubscription(subscription);
        }
        return true;
    }

    // Belirli bir tarihte gönderilecek tüm abonelikleri listeler
    public Vector<Subscription> listAllSendingOrders(int month, int year) {
        Vector<Subscription> subscriptionList = new Vector<Subscription>();

        for (Journal journal : getJournals().values()) {
            if (journal != null && journal.getSubscriptions() != null) {
                for (Subscription s : journal.getSubscriptions()) {
                    if (s.canSend(month, year)) {
                        subscriptionList.add(s);
                    }
                }
            }
        }
        return subscriptionList;
    }

    // Belirli bir derginin belirli bir tarihte gönderilecek aboneliklerini listeler
    public Vector<Subscription> listSendingOrders(String issn, int month, int year) {
        Vector<Subscription> subscriptionList = new Vector<Subscription>();

        Journal journal = searchJournal(issn);
        if (journal != null) {
            for (Subscription s : journal.getSubscriptions()) {
                if (s.canSend(month, year)) {
                    subscriptionList.add(s);
                }
            }
        }
        return subscriptionList;
    }

    // Tamamlanmamış ödemeleri listeler
    public Vector<Subscription> listInCompletePayments() {
        Vector<Subscription> subscriptionList = new Vector<Subscription>();

        for (Journal journal : getJournals().values()) {
            if (journal != null && journal.getSubscriptions() != null) {
                for (Subscription s : journal.getSubscriptions()) {
                    PaymentInfo payment = s.getPayment();
                    if (payment != null && payment.getReceivedPayment() == 0) {
                        subscriptionList.add(s);
                    }
                }
            }
        }
        return subscriptionList;
    }

    // Belirli bir anahtar kelimeye göre abonelikleri listeler (issn veya abone ismi)
    public Vector<Subscription> listSubscriptions(String searchKey) {
        Vector<Subscription> subscriptionList = new Vector<Subscription>();
        if (isNumeric(searchKey)) {
            // Eğer anahtar kelime bir sayıysa (issn), ilgili derginin aboneliklerini listeler
            Journal journal = searchJournal(searchKey);
            if (journal != null) {
                subscriptionList.addAll(journal.getSubscriptions());
            }
        } else {
            // Eğer anahtar kelime bir isimse (abone ismi), ilgili abonenin aboneliklerini listeler
            Subscriber subscriber = searchSubscriber(searchKey);
            if (subscriber != null) {
                for (Journal journal : getJournals().values()) {
                    Vector<Subscription> journalSubscription = journal.getSubscriptions();
                    for (Subscription js : journalSubscription) {
                        if (subscriber.getName().equals(js.getSubscriber().getName())) {
                            subscriptionList.add(js);
                        }
                    }
                }
            }
        }
        return subscriptionList;
    }

    // Distributor nesnesini belirtilen dosyaya kaydeder
    public void saveState(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), this);
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println(e.getMessage());
        }
    }

    // Belirtilen dosyadan bir Distributor nesnesi yükler
    public void loadState(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Distributor distributor = objectMapper.readValue(new File(fileName), Distributor.class);
            setJournals(distributor.getJournals());
            setSubscribers(distributor.getSubscribers());
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
		Distributor.journals = journals;
	}
	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Vector<Subscriber> subscribes) {
		this.subscribers = subscribes;
	}
	
	// Verilen bir String'in sayısal bir değer olup olmadığını kontrol eder
	public static boolean isNumeric(String str) {
	    // String, bir veya daha fazla basamak ve isteğe bağlı bir ondalık kısmı içerebilir
	    return str.matches("-?\\d+(\\.\\d+)?");
	}

}