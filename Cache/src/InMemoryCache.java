import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache <K, V> implements CacheStorage<K, V>{
    private final int capacity;
    private final Map<K, V> cache;
    public InMemoryCache(int capacity) {

        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public void put(K key, V value) throws Exception {
        cache.put(key, value);
    }

    @Override
    public V get(K key) throws Exception {
        if(!cache.containsKey(key)){
            throw  new Exception("Key not found" + key);
        }
        return cache.get(key);
    }

    @Override
    public void remove(K key) throws Exception {
        if(!cache.containsKey(key)){
            throw  new Exception("Key not found" + key);
        }
        cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) throws Exception {
        return cache.containsKey(key);
    }

    @Override
    public int size() throws Exception {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
