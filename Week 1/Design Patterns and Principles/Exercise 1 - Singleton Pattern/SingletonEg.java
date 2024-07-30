package s_pkg1;

class Logger 
{
	private static Logger instance;
	private int num;
	private Logger()
	{
		this.num=10;
	}
	public static Logger getInstance()
	{
		if(instance==null)
		{
			synchronized(Logger.class){
				if(instance==null) {
					instance=new Logger();
				}
			}
		}
		return instance;
	}
	
	public int getNum() {
		return num;
	}
}

public class SingletonEg
{
	public static void main(String []args)
	{
		Logger obj1=Logger.getInstance();
		Logger obj2=Logger.getInstance();
		if(obj1 == obj2)
		{
			System.out.println("Both instances are the same");
		}
		else{
			System.out.println("The instances are different");
		}
		System.out.println("Num value: " + obj1.getNum());
	}
}

