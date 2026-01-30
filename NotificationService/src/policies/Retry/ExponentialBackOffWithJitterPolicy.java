package policies.Retry;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/*
All retries happen at the same time
Causes retry spikes
*/
public class ExponentialBackOffWithJitterPolicy implements RetryPolicy {
    private final int maxRetries;
    private final long baseDelayMillis; // in milliseconds

    public ExponentialBackOffWithJitterPolicy(int maxRetries, long baseDelay) {
        this.maxRetries = maxRetries;
        this.baseDelayMillis = baseDelay;
    }

    @Override
    public void executeRetry(Callable<Void> task) throws Exception {
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                task.call();
                return;
            } catch (Exception e) {
                lastException = e;
                if (attempt == maxRetries) break;

                long maxDelay = baseDelayMillis * (1L << (attempt - 1));
                long jitterDelay = ThreadLocalRandom.current()
                        .nextLong(0, maxDelay);

                Thread.sleep(jitterDelay);
            }
        }

        throw lastException;
    }

}
