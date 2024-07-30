package pkg1;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		String search;
		Product[] products= {
			new Product("E1", "Laptop", "Electronics"),
			new Product("E2", "Phone", "Electronics"),
	        new Product("C1", "Shirt", "Clothing"),
	        new Product("A2", "Chain", "Accessories"),
	        new Product("B1", "Ikigai", "Books")	
		};
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Linear search");
		System.out.print("Enter name of product to search:");
		search=sc.nextLine();
		Product result_linear=LinearSearch.linear_search(products,search);
		if(result_linear!=null)
			System.out.println("   "+result_linear);
		else 
			System.out.println("Product Not found");
		
		System.out.println("\nBinary search");
		System.out.print("Enter name of product to search:");
		search=sc.nextLine();
		Product result_binary=BinarySearch.binary_search(products,search);
		if(result_binary!=null)
			System.out.println("   "+result_binary);
		else 
			System.out.println("Product Not found");
		
	sc.close();
	}
}
