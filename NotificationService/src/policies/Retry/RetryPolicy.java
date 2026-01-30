package policies.Retry;

import java.util.concurrent.Callable;

public interface RetryPolicy {

    /*
    Callable represents a unit of work that can return a result and throw exceptions.
    In retry logic, this allows failures to propagate naturally so the retry policy can decide whether to retry or fail.
    */
    void executeRetry(Callable<Void> task) throws Exception;
}
