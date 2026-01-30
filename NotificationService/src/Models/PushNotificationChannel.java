package Models;

public class PushNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        // Logic to send push notification
        System.out.println("Sending push notification: " + notification.getMessage());
    }
}
