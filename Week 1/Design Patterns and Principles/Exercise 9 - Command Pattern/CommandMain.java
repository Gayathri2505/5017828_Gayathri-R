package home_auto;

public class CommandMain 
{
	public static void main(String []args)
	{
		Light light=new Light();
		
		Command on=new LightOnCommand(light);
		Command off=new LightOffCommand(light);
		
		RemoteControl ctrl=new RemoteControl();
		
		ctrl.setCommand(on);
		ctrl.press();
		
		ctrl.setCommand(off);
		ctrl.press();
	}
}
