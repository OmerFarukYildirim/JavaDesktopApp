package nypProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubscriptionTest2 {
	
	DateInfo dateInfo = new DateInfo(2022, 1, 12);
    PaymentInfo paymentInfo = new PaymentInfo();
    Journal journal = new Journal("Test Journal", "123-4567");
    Subscriber subscriber = new Individual("John Doe", "123 Main St");
    private Subscription subscription = new Subscription(dateInfo, 3, journal, subscriber);
	@Test
    public void testCanSendWithInvalidDate() {
        assertFalse(subscription.canSend(1, 2023));
    }

    @Test
    public void testGetJournalWithExistingJournal() {
        assertEquals("Test Journal", subscription.getJournal().getName());
    }

    @Test
    public void testGetJournalWithNullJournal() {
        Subscription subscriptionWithNullJournal = new Subscription();
        subscriptionWithNullJournal.setJournalIssn("123-4567");
        assertEquals("Test Journal", subscriptionWithNullJournal.getJournal().getName());
    }

    @Test
    public void testToString() {
        String expected = "Subscription [dates=DateInfo [startYear=2022, startMonth=1, endYear=2022, endMonth=12], "
                + "payment=PaymentInfo [discountRatio=0.0, receivedPayment=0.0], copies=3, journal=Journal [name=Test Journal, "
                + "issn=123-4567, publisher=Test Publisher], subscriber=Individual [name=John Doe, address=123 Main St]]";

        assertEquals(expected, subscription.toString());
    }

}
