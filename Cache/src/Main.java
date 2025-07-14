public class Main {
    public static void main(String[] args) {
        try {
            CacheStorage<String, String > cacheStorage = new InMemoryCache<>(5);
            DBStorage<String, String> dbStorage = new SimpleDBStorage<>();
            WritePolicy<String, String> writePolicy = new WriteThroughPolicy<>();
            EvictionPolicy<String> evictionPolicy = new LRUEvictionPolicy<>();
            Cache<String, String > cache = new Cache<>(cacheStorage, dbStorage, writePolicy, evictionPolicy, 4);

            cache.updateData("A", "Apple").join();
            cache.updateData("B", "Banana").join();
            cache.updateData("C", "Cherry").join();
            cache.updateData("D", "Durian").join();
            cache.updateData("E", "Elderberry").join();

            cache.updateData("F", "Flower").join();

            try {
                String valueA = cache.accessData("A").join();
                System.out.println("A: " + valueA);
            } catch(Exception e) {
                System.out.println("A is evicted or not found in cache.");
            }

            String valueF = cache.accessData("F").join();
            System.out.println("F: " + valueF);

//            cache.updateData("B", "Blueberry").join();
            String valueB = cache.accessData("B").join();
            System.out.println("B: " + valueB);

            // Shut down executors when finished.
            cache.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}