import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutor {
    private final ExecutorService[] executors;
    private final int numExecutors;

    public KeyBasedExecutor(int numExecutors) {
        this.numExecutors = numExecutors;
        this.executors = new ExecutorService[numExecutors];
        for(int i=0;i<numExecutors;i++){
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public <T>CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int idx = getExecutorsIdxForKey(key);
        ExecutorService executor = executors[idx];
        return CompletableFuture.supplyAsync(task, executor);
    }

    public int getExecutorsIdxForKey(Object key) {
        return Math.abs(key.hashCode() % numExecutors);
    }

    public void shutDown() {
        for(ExecutorService executor: executors) {
            executor.shutdown();
        }
    }
}
