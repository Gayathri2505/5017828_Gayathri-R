package package1;

import java.util.HashMap;
import java.util.Scanner;

class InventoryOperations 
{
	private HashMap<Integer, Product> products;

    public InventoryOperations() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
        } else {
            products.put(product.getProductId(), product);
            System.out.println("Product " + product.getProductName() + " added.");
        }
    }

    public void updateProduct(int productId, Integer quantity, Double price) {
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            if (quantity != null && quantity != -1) {
                product.setQuantity(quantity);
            }
            if (price != null && price != -1) {
                product.setPrice(price);
            }
            System.out.println("Product " + productId + " updated.");
        } else {
            System.out.println("Product with ID " + productId + " does not exist.");
        }
    }

    public void deleteProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            System.out.println("Product " + productId + " deleted.");
        } else {
            System.out.println("Product with ID " + productId + " does not exist.");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in the inventory.");
        } else {
        	System.out.printf("%-10s %-20s %-10s %-10s%n", "Product ID", "ProductName", "Quantity", "Price");
            for (Product product : products.values()) {
                System.out.println(product);
            }
        }
    }
}

public class Main 
{
	public static void main(String []args)
	{
		int productId,qty;
		String productName;
		double price;
		InventoryOperations inventory = new InventoryOperations();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    productId = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter product name: ");
                    productName = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    qty = sc.nextInt();

                    System.out.print("Enter price: ");
                    price = sc.nextDouble();

                    inventory.addProduct(new Product(productId, productName, qty, price));
                    break;

                case 2:
                	System.out.print("Enter product ID to update: ");
                    int u_pid = sc.nextInt();

                    System.out.print("Enter new quantity (enter -1 to keep unchanged): ");
                    int new_qty = sc.nextInt();

                    System.out.print("Enter new price (enter -1 to keep unchanged): ");
                    double new_price = sc.nextDouble();

                    inventory.updateProduct(u_pid, new_qty == -1 ? null : new_qty, new_price == -1 ? null : new_price);
                    break;
                case 3:
                    System.out.print("Enter product ID to delete: ");
                    int deleteProductId = sc.nextInt();
                    inventory.deleteProduct(deleteProductId);
                    break;

                case 4:
                    inventory.displayProducts();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
	}
}


