package nypProject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaymentInfoTest1 {

    @Test
    public void testSetDiscountRatio() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setDiscountRatio(10);
        assertEquals(10, paymentInfo.getDiscountRatio(), 0);
    }

    @Test
    public void testSetReceivedPayment() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setReceivedPayment(50.0);
        assertEquals(50.0, paymentInfo.getReceivedPayment(), 0);
    }

}
