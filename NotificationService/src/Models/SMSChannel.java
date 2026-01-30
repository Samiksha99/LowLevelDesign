package Models;

public class SMSChannel implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        // Logic to send SMS notification
        System.out.println("Sending SMS notification to " + notification.getRecipient() + ": " + notification.getMessage());
    }
}
