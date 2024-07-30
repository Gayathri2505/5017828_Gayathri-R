package stock_monitor;
import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock
{
	private List<Observer> observer=new ArrayList<>();
	
	public void setStockPrice(String name, double price) {
        notify(name, price);
    }
	
	@Override
	public void register(Observer obs) {
		observer.add(obs);
	}

	@Override
	public void deregister(Observer obs) {
		observer.remove(obs);
	}

	@Override
	public void notify(String name, double price) {
		for(Observer ob:observer) {
			ob.update(name, price);
		}
	}
		
}
