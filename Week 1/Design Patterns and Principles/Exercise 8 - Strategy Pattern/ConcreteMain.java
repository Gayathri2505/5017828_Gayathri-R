package payment;

import java.util.Scanner;

public class ConcreteMain 
{
	public static void main(String[] args) {
        PaymentContext ctxt = new PaymentContext();
        
        int choice;
        String cardno,email;
        Scanner sc=new Scanner(System.in);
        System.out.println("Payment\n 1.Credit Card\n 2.PayPal");
        System.out.print("Enter choice: ");
        choice=sc.nextInt();
        sc.nextLine();
        
        switch(choice) 
        {
	        case 1:
	        	System.out.print("Enter card number:");
	        	cardno=sc.nextLine();
	        	ctxt.setPaymentStrategy(new CreditCardPayment(cardno));
	            ctxt.executePayment(100.0);
	            break;
	        case 2:
	        	System.out.print("Enter Email:");
	        	email=sc.nextLine();
	        	ctxt.setPaymentStrategy(new PayPalPayment(email));
	            ctxt.executePayment(50.0);
	            break;
	        default:
	        	System.out.println("Wrong choice. Payment Failed");
	        	break;
        } 
        sc.close();
    }
}
