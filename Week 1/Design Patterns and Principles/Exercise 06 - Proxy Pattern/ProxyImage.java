package img_viewer;

public class ProxyImage implements Image
{
	private RealImage real;
	private String fileName;
	
	public ProxyImage(String fileName)
	{
		this.fileName=fileName;
	}
	
	@Override
	public void display()
	{
		if (real == null) 
			real = new RealImage(fileName);
		real.display();
	}
}
