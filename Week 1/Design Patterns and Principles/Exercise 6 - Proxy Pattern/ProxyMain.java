package img_viewer;

public class ProxyMain 
{
	public static void main(String[] args)
	{
		Image img1 = new ProxyImage("image1.jpg");
	    Image img2 = new ProxyImage("image2.jpg");

	    System.out.println("For the First time");
	    img1.display();
	    img2.display();
	    
	    System.out.println("For the Second time - loading will not happen due to caching");
	    img1.display();
	    img2.display();
	}
}
