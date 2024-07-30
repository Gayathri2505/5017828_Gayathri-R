package home_auto;

public class RemoteControl 
{
	private Command cmd;
	public void setCommand(Command cmd)
	{
		this.cmd=cmd;
	}
	public void press() {
		cmd.execute();
	}

}
