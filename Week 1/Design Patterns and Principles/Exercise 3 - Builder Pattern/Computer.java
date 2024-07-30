package comp;

public class Computer 
{
	private String cpu,ram,storage,gpu;
	private Computer(Builder comp)
	{
		this.cpu=comp.cpu;
		this.ram=comp.ram;
		this.storage=comp.storage;
		this.gpu=comp.gpu;
	}
	public String getCPU() {
        return cpu;
    }

    public String getRAM() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGPU() {
        return gpu;
    }
    @Override
    public String toString()
    {
    	return "Computer :\n CPU: "+cpu+", RAM: "+ram+", Storage: "+storage+", GPU: "+gpu;
    }
    
    
    
    public static class Builder
    {
    	private String cpu,ram,storage,gpu;
    	
    	public Builder setCPU(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRAM(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGPU(String gpu) {
            this.gpu = gpu;
            return this;
        }
    	
    	public Computer build()
    	{
    		return new Computer(this);
    	}   
    }
}
