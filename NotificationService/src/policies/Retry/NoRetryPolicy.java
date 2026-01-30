package policies.Retry;

import java.util.concurrent.Callable;

public class NoRetryPolicy implements RetryPolicy{

    @Override
    public void executeRetry(Callable<Void> task) throws Exception {
        task.call();
    }
}
