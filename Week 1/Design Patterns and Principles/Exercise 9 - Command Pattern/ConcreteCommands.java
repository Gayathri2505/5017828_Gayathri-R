package home_auto;

class LightOnCommand implements Command
{
	private Light light;
	
	public LightOnCommand(Light light)
	{
		this.light=light;
	}
	@Override
	public void execute() {
		light.turnOn();
	}
}

class LightOffCommand implements Command
{
	private Light light;
	
	public LightOffCommand(Light light)
	{
		this.light=light;
	}
	@Override
	public void execute() {
		light.turnOff();
	}
}