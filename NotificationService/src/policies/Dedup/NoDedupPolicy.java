package policies.Dedup;

import policies.Dedup.DedupPolicy;

public class NoDedupPolicy implements DedupPolicy {
    @Override
    public boolean isDuplicate(String messageId) {
        return false; // No deduplication, always return false
    }

    @Override
    public void recordNotification(String dedupId) {
        return; // No action needed
    }
}
