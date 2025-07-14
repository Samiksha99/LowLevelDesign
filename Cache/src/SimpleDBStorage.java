import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleDBStorage <K, V> implements DBStorage<K, V>{
    private final Map<K, V> database = new ConcurrentHashMap<>();

    @Override
    public void write(K key, V value) throws Exception {
        database.put(key, value);
    }

    @Override
    public V read(K key) throws Exception {
        if(!database.containsKey(key)){
            throw  new Exception("Key not found" + key);
        }
        return database.get(key);
    }

    @Override
    public void delete(K key) throws Exception {
        if(!database.containsKey(key)){
            throw  new Exception("Key not found" + key);
        }
        database.remove(key);
    }
}
