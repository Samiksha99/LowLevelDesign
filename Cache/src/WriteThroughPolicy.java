import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class WriteThroughPolicy <K, V> implements WritePolicy<K, V>{
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) {
        CompletableFuture<Void> cacheFuture = CompletableFuture.runAsync(() -> {
           try {
               cacheStorage.put(key, value);
           }catch (Exception e) {
               throw  new CompletionException(e);
           }
        });

        CompletableFuture<Void> dbFuture = CompletableFuture.runAsync(() -> {
            try {
                dbStorage.write(key, value);
            } catch (Exception e){
                throw new CompletionException(e);
            }
        });
        CompletableFuture.allOf(cacheFuture, dbFuture).join();
    }
}
