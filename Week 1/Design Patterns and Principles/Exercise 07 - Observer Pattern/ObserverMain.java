package stock_monitor;

public class ObserverMain 
{
	public static void main(String[] args) 
	{
		Stock sm=new StockMarket();
		Observer inv1=new MobileApp("Investor1");
		Observer inv2=new MobileApp("Investor2");
		Observer inv3=new WebApp("Investor3");
		
		sm.register(inv1);
		sm.register(inv2);
		sm.register(inv3);
		System.out.println("Three observers registered");
		
		System.out.println("Stock1 price is updated");
		System.out.println("Intial updation sent to 3 of them");
		((StockMarket) sm).setStockPrice("Stock1", 1250.0);
		
		System.out.println("One observer is removed");
		sm.deregister(inv1);
		
		System.out.println("Stock2 price is updated");
		System.out.println("Updation of stock is send only to 2 of them");
		((StockMarket) sm).setStockPrice("Stock2", 500.0);
	}
}
