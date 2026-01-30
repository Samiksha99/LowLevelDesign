package policies.Retry;

import java.util.concurrent.Callable;

public class FixedRetryPolicy implements RetryPolicy{
    private final int maxRetries;
    private final long delayMillis;

    public FixedRetryPolicy(int maxRetries, long delayMillis) {
        this.maxRetries = maxRetries;
        this.delayMillis = delayMillis;
    }

    @Override
    public void executeRetry(Callable<Void> task) throws Exception {
        Exception lastException = null;
        for(int attempt = 0; attempt<=maxRetries; attempt++){
            try{
                task.call();
                return;
            } catch(Exception e) {
                lastException = e;
                if(attempt < maxRetries){
                    Thread.sleep(delayMillis);
                }
            }
        }
        throw lastException;
    }
}
