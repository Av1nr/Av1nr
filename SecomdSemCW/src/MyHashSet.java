public class MyHashSet<E> {
    private static final Object PRESENT = new Object();
    private MyHashMap <E , Object> map;
    public MyHashSet(){
        map = new MyHashMap<>();
    }
    public boolean add(E key){
        map.resize();
        return map.put(key , PRESENT) == null;
    }
    public boolean remove(Object o){
        return map.remove((E) o) != null;
    }
    public boolean contains(Object o){
        return map.containsKey((E) o);
    }
    public int size(){
        return map.size();
    }
    public boolean isEmpty(){
        return map.isEmpty();
    }
    public void clear(){
        map.clear();
    }
}
