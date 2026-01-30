package policies.Dedup;


import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDedupPolicy implements DedupPolicy {
    private final Set<String> messageIds = ConcurrentHashMap.newKeySet();
    @Override
    public boolean isDuplicate(String messageId){
        return !messageIds.add(messageId);
    }

    @Override
    public void recordNotification(String dedupId) {
        messageIds.add(dedupId);
    }
}
