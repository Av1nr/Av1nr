import java.util.Objects;

public class MyLinkedList<T> implements MyList<T>{
    private Node<T> head;
    private int size;

    // Конструктор
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T item) {
        add(size, item);
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> newNode = new Node<>(item);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            removedData = prev.next.data;
            prev.next = prev.next.next;
        }
        size--;
        return removedData;
    }

    @Override
    public boolean remove(T item) {
        if (head == null) return false;
        if (head.data == null && item == null) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        for(int i = 0 ; i < size ; i++){
            if(Objects.equals(current.data , item)){
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
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(T item) {
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (item == null) {
                if (current.data == null) return index;
            } else {
                if (item.equals(current.data)) return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
}
