package stock_monitor;

public interface Stock 
{
	void register(Observer obs);
	void deregister(Observer obs);
	void notify(String name,double price);
}
