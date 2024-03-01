package nypProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

class JournalTest2 {
    
    @Test
    public void testSearchSubscriberSubscription() {
        // Test senaryosu için bir abone ve bir dergi oluşturulur
        Subscriber subscriber = new Individual("John Doe", "123 Main St", "1234-5678-9012-3456", 5, 2023, 123);
        Journal journal = new Journal("ISSN123", "Test Journal");

        // Abonelik oluşturulur ve dergiye eklenir
        Subscription subscription = new Subscription(subscriber, journal);
        journal.addSubscription(subscription);

        // Dergide arama yapılır ve oluşturulan aboneliğin bulunması beklenir
        Subscription foundSubscription = journal.searchSubscriberSubscription(subscriber);
        assertEquals(subscription, foundSubscription);

        // Farklı bir abone ile arama yapılır ve bu aboneliğin bulunmaması beklenir
        Subscriber differentSubscriber = new Individual("Jane Doe", "456 Oak St", "5678-9012-3456-7890", 6, 2022, 456);
        Subscription notFoundSubscription = journal.searchSubscriberSubscription(differentSubscriber);
        assertNull(notFoundSubscription);
    }

}
