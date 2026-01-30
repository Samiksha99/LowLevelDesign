package Models;

public class EmailChannel implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        // Logic to send email notification
        System.out.println("Sending Email Notification: " + notification.getMessage());
    }
}
