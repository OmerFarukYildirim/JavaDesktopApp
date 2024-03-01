package nypProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DistributorTest1 {

    private Distributor distributor = new Distributor();

    
    @Test
    public void testAddJournal() {
        // Yeni bir dergi oluşturulur
        Journal journal = new Journal("123", "Journal Name");
        
        // Dergi distributor nesnesine eklenir; bu durumda, çift giriş olması beklenir, bu nedenle false dönmelidir
        assertFalse(distributor.addJournal(journal));
    }

    @Test
    public void testSearchJournal() {
        // Yeni bir dergi oluşturulur
        Journal journal = new Journal("123", "Journal Name");
        
        // Dergi distributor nesnesine eklenir
        distributor.addJournal(journal);

        // Belirli bir issn numarasına sahip dergi aranır; bu durumda, bulunamaması beklenir
        Journal notFoundJournal = Distributor.searchJournal("456");
        
        // Bulunamayan dergi null olmalıdır
        assertNull(notFoundJournal);
    }

    @Test
    public void testAddSubscriber() {
        // Yeni bir bireysel abone oluşturulur
        Individual subscriber = new Individual("John Doe", "adres2");
        
        // Abone distributor nesnesine eklenir; bu durumda, başarıyla eklenmesi beklenir
        assertTrue(distributor.addSubscriber(subscriber));
    }

    @Test
    public void testSearchSubscriber() {
        // Yeni bir bireysel abone oluşturulur
        Individual subscriber = new Individual("John Doe", "adres1");
        
        // Abone distributor nesnesine eklenir
        distributor.addSubscriber(subscriber);

        // Belirli bir isme sahip abone aranır; bu durumda, bulunması beklenir
        Subscriber foundSubscriber = distributor.searchSubscriber("John Doe");
        
        // Bulunan abone, oluşturulan abone ile aynı olmalıdır
        assertEquals(subscriber, foundSubscriber);

        // Belirli bir isme sahip olmayan abone aranır; bu durumda, bulunamaması beklenir
        Subscriber notFoundSubscriber = distributor.searchSubscriber("Jane Doe");
        
        // Bulunamayan abone null olmalıdır
        assertNull(notFoundSubscriber);
    }


}
