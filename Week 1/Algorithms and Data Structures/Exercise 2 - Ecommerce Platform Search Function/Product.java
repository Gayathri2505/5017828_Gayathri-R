package pkg1;

import java.util.Arrays;
import java.util.Comparator;

class Product 
{
	private String productId,productName,category;
	public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
    @Override
    public String toString() {
        return productName + " is under the category " + category;
    }
}

class LinearSearch 
{
	public static Product linear_search(Product[] prod,String prodName)
	{
		for(Product p:prod)
		{
			if (p.getProductName().toLowerCase().equals(prodName))
                return p;
		}
		return null;
	}
}

class BinarySearch
{
	public static Product binary_search(Product[] prod,String prodName)
	{
		Arrays.sort(prod,Comparator.comparing(Product :: getProductName));
		int low = 0;
        int high = prod.length - 1;

        while (low <= high) 
        {
            int mid = low + (high - low) / 2;
            String curr_prod=prod[mid].getProductName().toLowerCase();
            int compare = curr_prod.compareTo(prodName);
            if (compare == 0) 
                return prod[mid];
            else if (compare < 0)
                low = mid + 1;
           else
                high = mid - 1;
        }
        return null;
	}
}
