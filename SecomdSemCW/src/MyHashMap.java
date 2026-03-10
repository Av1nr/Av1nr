public class MyHashMap<K , V> implements MyMap<K , V> {
    private Node<K, V>[] buckets;
    private int size;
    private static final int CAPACITY = 16;

    public MyHashMap() {
        buckets = (Node<K, V>[]) new Node[CAPACITY];
        size = 0;

    }

    private static final class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public V put(K key, V value) {
        resize();
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return null;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }
        return null;
    }
    @Override
    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> prev = null;
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    // Remove head
                    buckets[index] = current.next;
                } else {
                    // Bypass current
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < CAPACITY; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }
    public void resize(){
        if (size == buckets.length){
             int l = buckets.length * 2;
             Node<K , V>[] newBuckets = (Node<K , V>[]) new Node[l];
             for (int i = 0 ; i < buckets.length ;i++ ) {
                Node<K, V> current = buckets[i];
                while (current != null) {
                    Node<K, V> next = current.next;
                    int newIndex = current.key.hashCode() % l;
                    current.next = newBuckets[newIndex];
                    newBuckets[newIndex] = current;
                    current = next;
                }
            }

            buckets = newBuckets;
        }

    }
}
