import Enums.ChannelType;
import Models.NotificationChannel;
import policies.Dedup.DedupPolicy;
import policies.Retry.RetryPolicy;

import java.util.Map;

public class NotificationService {

    /* For most notification systems, I’d use exponential backoff with jitter.
    For critical alerts like OTPs, I’d use limited fixed retries. For in-app notifications, I might skip retries entirely.*/

    private final DedupPolicy dedupPolicy;
    private final RetryPolicy retryPolicy;
    private final Map<ChannelType, NotificationChannel> channelMap;
    public NotificationService(DedupPolicy dedupPolicy, RetryPolicy retryPolicy, Map<ChannelType, NotificationChannel> channelMap) {
        this.dedupPolicy = dedupPolicy;
        this.retryPolicy = retryPolicy;
        this.channelMap = channelMap;
    }

    public void sendNotification(Models.Notification notification) throws Exception {
        String dedupKey = notification.getDedupKey();
        if(dedupPolicy.isDuplicate(dedupKey)){
            System.out.println("Duplicate notification detected: " + dedupKey);
            return;
        }
        boolean atLeastOneSuccess = false;
        for(ChannelType channelType : notification.getPreferredChannel()){
            NotificationChannel channel = channelMap.get(channelType);
            try {
                retryPolicy.executeRetry(() -> {
                    channel.sendNotification(notification);
                    return null;
                });
                atLeastOneSuccess = true;
            } catch (Exception e) {
                    System.err.println("Failed to send notification via " + channelType + ": " + e.getMessage());
            }
        }
        if(atLeastOneSuccess){
            dedupPolicy.recordNotification(dedupKey);
        } else {
            throw new Exception("Failed to send notification via all preferred channels.");
        }

    }
}
