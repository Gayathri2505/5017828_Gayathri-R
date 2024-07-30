package notification;

public abstract class NotifierDecorator implements Notifier
{
	private Notifier ref;
	public NotifierDecorator(Notifier ref)
	{
		this.ref=ref;
	}
	@Override
    public void send(String msg) {
        ref.send(msg);
    }
}

class SMSNotifierDecorator extends NotifierDecorator
{
	public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        sendSMS(msg);
    }

    private void sendSMS(String msg) {
        System.out.println("Notifiation:\n " + msg+ " - sent using SMS");
    }
}

class SlackNotifierDecorator extends NotifierDecorator
{
	public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        sendSMS(msg);
    }

    private void sendSMS(String msg) {
        System.out.println("Notifiation:\n " + msg+ " - sent using Slack");
    }
}