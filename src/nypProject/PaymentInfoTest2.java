package nypProject;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentInfoTest2 {
	PaymentInfo paymentInfo = new PaymentInfo();
	@Test
    public void testIncreasePayment() {
        paymentInfo.increasePayment(30.0);
        assertEquals(30.0, paymentInfo.getReceivedPayment(), 0);
        paymentInfo.increasePayment(20.0);
        assertEquals(50.0, paymentInfo.getReceivedPayment(), 0);
    }

    @Test
    public void testSetDiscountRatioWithIncreasePayment() {
        paymentInfo.increasePayment(100.0);
        paymentInfo.setDiscountRatio(20);
        assertEquals(80.0, paymentInfo.getReceivedPayment(), 0);
    }

}
