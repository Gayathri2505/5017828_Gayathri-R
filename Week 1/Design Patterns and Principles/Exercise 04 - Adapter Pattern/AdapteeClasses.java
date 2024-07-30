package payment;

class PayPal {
    public void makePayment(double amt) {
        System.out.println("Paid " + amt + " via PayPal");
    }
}

class StripePaymentGateway {
    public void charge(double amt) {
        System.out.println("Charged " + amt + " using Stripe");
    }
}