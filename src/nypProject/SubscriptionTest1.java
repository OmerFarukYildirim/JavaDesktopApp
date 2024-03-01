package nypProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SubscriptionTest1 {

    
    DateInfo dateInfo = new DateInfo(2022, 1, 12);
    PaymentInfo paymentInfo = new PaymentInfo();
    Journal journal = new Journal("Test Journal", "123-4567");
    Subscriber subscriber = new Individual("John Doe", "123 Main St");
    private Subscription subscription = new Subscription(dateInfo, 3, journal, subscriber);
    


    @Test
    public void testCanSendWithValidDateAndNoPayment() {
        assertTrue(subscription.canSend(6, 2022));
    }

    
}
