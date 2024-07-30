package payment;

class PayPalAdapter implements PaymentProcessor {
    private PayPal gateway;

    public PayPalAdapter(PayPal gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amt) {
    	gateway.makePayment(amt);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripePaymentGateway gateway;

    public StripeAdapter(StripePaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amt) {
    	gateway.charge(amt);
    }
}