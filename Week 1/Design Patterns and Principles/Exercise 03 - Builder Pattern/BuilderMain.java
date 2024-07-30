package comp;

public class BuilderMain 
{
	public static void main(String []args)
	{
		Computer spec1= new Computer.Builder().setCPU("Intel i5").setRAM("32GB").setStorage("1TB HDD").build();
		System.out.println(spec1);
		
		Computer spec2= new Computer.Builder().setCPU("Intel i9").setRAM("128GB").setStorage("1TB SSD").setGPU("NVIDIA RTX 3080").build();
		System.out.println(spec2);
	}
}
