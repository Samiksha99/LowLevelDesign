import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Cache<K, V> {
    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionPolicy<K> evictionPolicy;
    private final KeyBasedExecutor keyBasedExecutor;

    public Cache(CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage, WritePolicy<K, V> writePolicy, EvictionPolicy<K> evictionPolicy, int numExecutors ) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionPolicy = evictionPolicy;
        this.keyBasedExecutor = new KeyBasedExecutor(numExecutors);
    }

    public CompletableFuture<V> accessData(K key) {
        return keyBasedExecutor.submitTask(key,  () ->{
            try {
                if(!cacheStorage.containsKey(key)){
                    throw new Exception("Key not found" + key);
                }
                evictionPolicy.keyAccessed(key);
                return cacheStorage.get(key);
            }
            catch (Exception e){
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key, V value){
        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if(cacheStorage.containsKey(key)){
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionPolicy.keyAccessed(key);
                }
                else{
                    if(cacheStorage.size() >= cacheStorage.getCapacity()) {
                        K evictedKey = evictionPolicy.evictKey();
                        if(evictedKey!=null){
                            int currIdx = keyBasedExecutor.getExecutorsIdxForKey(key);
                            int evictedKeyIdx = keyBasedExecutor.getExecutorsIdxForKey(evictedKey);

                            if(currIdx == evictedKeyIdx) {
                                cacheStorage.remove(evictedKey);
                            }
                            else{
                                CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey, ()->{
                                    try {
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    }catch (Exception e) {
                                        throw new CompletionException(e);
                                    }
                                });
                                removalFuture.join();
                            }
                        }
                    }
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionPolicy.keyAccessed(key);
                }
                return null;
            }catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }
    public void shutdown() {
        keyBasedExecutor.shutDown();
    }
}
