import Enums.ChannelType;
import Models.*;
import policies.Dedup.DedupPolicy;
import policies.Dedup.InMemoryDedupPolicy;
import policies.Retry.ExponentialBackOffRetryPolicy;
import policies.Retry.RetryPolicy;

import java.util.List;
import java.util.Map;

/*
Retries for a single notification are executed sequentially within one thread to avoid duplicate sends.
However, multiple threads can execute the same retry policy concurrently for different notifications, such as when processing messages from a Kafka consumer group.
*/
public class Main {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackOffRetryPolicy(3, 100);
        DedupPolicy dedupPolicy = new InMemoryDedupPolicy();

        Map<ChannelType, NotificationChannel> channelMap = Map.of(
            ChannelType.EMAIL, new EmailChannel(),
            ChannelType.SMS, new SMSChannel(),
            ChannelType.PUSH_NOTIFICATION, new PushNotificationChannel()
        );

        NotificationService notificationService = new NotificationService(dedupPolicy, retryPolicy, channelMap);
        System.out.println("Notification Service initialized successfully.");
        Notification notification = new Notification("Order Shipped", "Shipping", "Amazon", "Samiksha", List.of(ChannelType.EMAIL, ChannelType.SMS));
        notificationService.sendNotification(notification); // Placeholder for actual notification sending
    }
}