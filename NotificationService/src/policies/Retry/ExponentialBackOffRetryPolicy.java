package policies.Retry;

import java.util.concurrent.Callable;

public class ExponentialBackOffRetryPolicy implements RetryPolicy{

    private final int maxRetries;
    private final long baseDelayMillis;

    public ExponentialBackOffRetryPolicy(int maxRetries, long baseDelayMillis) {
        this.maxRetries = maxRetries;
        this.baseDelayMillis = baseDelayMillis;
    }

    @Override
    public void executeRetry(Callable<Void> task) throws Exception {
        Exception lastException = null;
        for (int attempt=0; attempt<=maxRetries; attempt++) {
            try {
                task.call();
                return;
            } catch (Exception e) {
                lastException = e;
                if(attempt < maxRetries) {
                    long delay = baseDelayMillis * (1L << (attempt-1)); // Exponential back-off
                    Thread.sleep(delay);
                }
            }
        }
    }
}
