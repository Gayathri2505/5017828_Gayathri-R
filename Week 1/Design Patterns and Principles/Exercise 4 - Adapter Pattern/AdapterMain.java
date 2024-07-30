package payment;

import java.util.Scanner;

public class AdapterMain 
{
	public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPal());
        PaymentProcessor stripe = new StripeAdapter(new StripePaymentGateway());
        
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the amount to pay:");
        double amt = sc.nextDouble();
     
        paypal.processPayment(amt);
        stripe.processPayment(amt);
        
        sc.close();
    }
}
