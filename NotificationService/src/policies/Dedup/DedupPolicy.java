package policies.Dedup;

public interface DedupPolicy {
    boolean isDuplicate(String messageId);
    void recordNotification(String dedupId);
}
