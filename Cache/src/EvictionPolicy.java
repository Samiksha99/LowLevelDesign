public interface EvictionPolicy <K> {
    void keyAccessed(K key) throws Exception;
    K evictKey() throws Exception;
}
