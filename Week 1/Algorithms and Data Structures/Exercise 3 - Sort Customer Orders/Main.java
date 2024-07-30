package ecomm;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
        Order[] orders = {
            new Order("1", "aaa", 250.50),
            new Order("2", "bbb", 150.75),
            new Order("3", "ccc", 325.00),
            new Order("4", "ddd", 200.25),
            new Order("5", "eee", 175.90)
        };

        System.out.println("Original Orders:");
        display(orders);
        
        System.out.println("Sort Using:\n 1.Bubble Sort\n 2.Quick Sort");
        Scanner sc=new Scanner(System.in);
        
        System.out.print("Enter action: ");
        int ch=sc.nextInt();
        
        switch(ch)
        {
        case 1: 
        	BubbleSort.bubbleSort(orders);
            System.out.println("\nBubble Sorted Orders:");
            display(orders);
            break;
        case 2:
        	QuickSort.quickSort(orders, 0, orders.length - 1);
            System.out.println("\nQuick Sorted Orders:");
            display(orders);
        }
        
        sc.close();
    }

    private static void display(Order[] orders) {
    	System.out.printf("%-10s %-15s %-10s%n", "OrderId", "CustomerName", "TotalPrice");
        System.out.println("------------------------------------------");
        for (Order order : orders) {
            System.out.printf("%-10s %-15s %-10.2f%n", order.getOrderId(), order.getCustomerName(), order.getTotalPrice());
        }
    }
    
}
