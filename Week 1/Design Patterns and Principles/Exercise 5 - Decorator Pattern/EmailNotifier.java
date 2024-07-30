package notification;

public class EmailNotifier implements Notifier
{
	@Override
	public void send(String msg) {
		System.out.println("Notifiaction:\n "+ msg +" - sent using email");
	}
}
