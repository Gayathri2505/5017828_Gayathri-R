package notification;

public class DecoratorMain {

	public static void main(String[] args) 
	{
		Notifier email = new EmailNotifier();

        Notifier sms = new SMSNotifierDecorator(email);

        Notifier slack = new SlackNotifierDecorator(sms);

        slack.send("This is a test message");
	}
}
