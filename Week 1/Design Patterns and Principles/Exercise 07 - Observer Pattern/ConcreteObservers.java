package stock_monitor;

class MobileApp implements Observer
{
	private String user;
	
	public MobileApp(String user)
	{
		this.user=user;
	}
	
	@Override
	public void update(String name, double price) {
		System.out.println(user + " received stock price update for : "+name+": " + price);
	}	
}

class WebApp implements Observer
{
	private String user;
	
	public WebApp(String user)
	{
		this.user=user;
	}
	
	@Override
	public void update(String name, double price) {
		System.out.println(user + " received stock price update for : "+name+": " + price);
	}	
}