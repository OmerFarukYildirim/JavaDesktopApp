package models;

public class PaymentInfo {
	private double discountRatio = 0;
	private double receivedPayment;
	
	public PaymentInfo() {
	}


	void increasePayment(double amount) {
		
	}
	
	
	public double getReceivedPayment() {
		return receivedPayment;
	}
	public void setReceivedPayment(double receivedPayment) {
		this.receivedPayment = receivedPayment;
	}
	public double getDiscountRatio() {
		return discountRatio;
	}
	
	public void setDiscountRatio(double discountRatio) {
		this.discountRatio=discountRatio;
	}


	@Override
	public String toString() {
		return "PaymentInfo [discountRatio=" + discountRatio + ", receivedPayment=" + receivedPayment + "]";
	}
	
	
	
	
	
	

}
