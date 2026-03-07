import java.util.Objects;

public class MyArrayList<T> implements MyList<T>{
    private final static int CAPACITY = 10;
    private T[] data;
    private int  size;

    public MyArrayList() {
        data = (T[])new Object[CAPACITY];
        size = 0;
    }
    public MyArrayList(T[] array){
        data = (T[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
        size = array.length;
    }
    private void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    private void checkIndexRemoveAndGet(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    private void capacityIncreaser(){
        if (size == data.length) {
            int newCapacity = data.length * 3 / 2 + 1; // рост в 1.5 раза
            T[] newData = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }
    @Override
    public void add(T item) {
        capacityIncreaser();
        data[size++] = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndexAdd(index);
        capacityIncreaser();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = item;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndexRemoveAndGet(index);
        return data[index];
    }

    @Override
    public T remove(int index) {
        checkIndexRemoveAndGet(index);
        T remember = data[index];
        for(int i = index ; i < size - 1; i++){
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return remember;
    }

    @Override
    public boolean remove(T item) {
        for(int i = 0 ; i < size; i++){
            if(Objects.equals(item, data[i])){
                remove(i);
                return true;
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
        for(int i = 0 ; i < size ; i++){
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        for(int i = 0 ; i < size ; i++){
            if(Objects.equals(item, data[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T item) {
        for(int i = 0 ; i < size ; i++){
            if(Objects.equals(item, data[i])){
                return i;
            }
        }
        return -1;
    }
}
