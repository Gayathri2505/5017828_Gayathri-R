package payment;

class CreditCardPayment implements PaymentStrategy
{
	@SuppressWarnings("unused")
	private String cardNo;
	 
    public CreditCardPayment(String cardNo) {
        this.cardNo = cardNo;
    }
    
	@Override
	public void pay(double amt) {
		System.out.println("Paid " + amt + " using credit card");
	}	
}

class PayPalPayment implements PaymentStrategy {
    @SuppressWarnings("unused")
	private String email;
 
    public PayPalPayment(String email) {
        this.email = email;
    }
 
    @Override
    public void pay(double amt) {
        System.out.println("Paid " + amt + " using PayPal");
    }
}