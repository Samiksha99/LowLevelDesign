import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K>{
    private final DoublyLinkedList<K> dll;
    private final Map<K, DoublyLinkedListNode<K>> keyToNode;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.keyToNode = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) throws Exception {
        if(keyToNode.containsKey(key)){
            DoublyLinkedListNode<K> node = keyToNode.get(key);
            dll.detachNode(node);
            dll.addNodeAtTail(node);
        }
        else {
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(newNode);
            keyToNode.put(key, newNode);
        }
    }

    @Override
    public K evictKey() throws Exception {
        DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
        if(nodeToEvict == null){
            return  null;
        }
        K evictKey = nodeToEvict.getValue();
        dll.removeHead();
        keyToNode.remove(evictKey);
        return evictKey;
    }
}
