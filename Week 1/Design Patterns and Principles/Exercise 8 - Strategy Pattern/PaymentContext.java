package payment;

public class PaymentContext 
{
	private PaymentStrategy pay_strategy;
	 
    public void setPaymentStrategy(PaymentStrategy pay_strategy) {
        this.pay_strategy = pay_strategy;
    }
 
    public void executePayment(double amount) {
        pay_strategy.pay(amount);
    }
}
