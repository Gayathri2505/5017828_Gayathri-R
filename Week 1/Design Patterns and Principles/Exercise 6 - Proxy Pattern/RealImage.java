package img_viewer;

public class RealImage implements Image
{
	String fileName;
	public RealImage(String fileName)
	{
		this.fileName=fileName;
		loadImageFromDisk();
	}
	private void loadImageFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	@Override
	public void display()
	{
		System.out.println(fileName+"\n");
	}
}
