package nypProject;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class DistributorTest2 {

    private Distributor distributor;

    @Before
    public void setUp() {
        // Her testin başlangıcında yeni bir Distributor nesnesi, boş bir dergi Hashtable'ı ve boş bir abone Vector'ü oluşturulur
        distributor = new Distributor();
        distributor.setJournals(new Hashtable<>());
        distributor.setSubscribers(new Vector<>());
    }

    @Test
    public void testListInCompletePayments() {
        // Test edilmek üzere bir dergi, abone, tarih bilgisi, ödeme bilgisi ve abonelik oluşturulur
        Journal journal = new Journal("ISSN789", "Third Journal");
        Subscriber subscriber = new Individual("Bob Smith", "Another Address");
        DateInfo dateInfo = new DateInfo(2022, 1, 1);
        PaymentInfo paymentInfo = new PaymentInfo(); // Ödeme alınmamış abonelik
        Subscription subscription = new Subscription(dateInfo, 1, journal, subscriber);
        subscription.setPayment(paymentInfo);

        // Oluşturulan abonelik dergiye eklenir ve dergi, distributor nesnesine eklenir
        journal.addSubscription(subscription);
        distributor.getJournals().put("ISSN789", journal);

        // Tamamlanmamış ödemeleri listeleyen metot çalıştırılır ve sonuçlar alınır
        Vector<Subscription> result = distributor.listInCompletePayments();

        // Sonuçlar arasında oluşturulan abonelik bulunmalıdır
        assertTrue(result.contains(subscription));
    }

}
