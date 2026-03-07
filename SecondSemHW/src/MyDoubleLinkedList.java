import java.util.Objects;

public class MyDoubleLinkedList<T>{
    private NodeForDouble<T> head;
    private NodeForDouble<T> tail;
    private int size;

    public MyDoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    public void add(T item) {
        addLast(item);
    }
    public void addLast(T item){
        NodeForDouble<T> newNode = new NodeForDouble<>(item);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public void addFirst(T item){
        NodeForDouble<T> newNode = new NodeForDouble<>(item);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void add(int index, T item) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
            //Случай когда мы добавляем элемент на последнее место , нас устраивает , в других работах писал просто два метода
        }
        if(index == 0){
            addFirst(item);
        }
        else if(index == size){
            addLast(item);
        }
        else{
            NodeForDouble<T> newNode = new NodeForDouble<>(item);
            if(size / 2 <= index){
                //идем с хвоста
                NodeForDouble<T> current = tail;
                for(int i = size - 1; i > index ; i--){
                    current = current.prev;
                }
                NodeForDouble<T> prev = current.prev; // запоминаем предыдущий узел
                prev.next = newNode;
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev = newNode;
                size++;
            }
            else {
                //идем с головы
                NodeForDouble<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                NodeForDouble<T> prev = current.prev; // запоминаем предыдущий узел
                prev.next = newNode;
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev = newNode;
                size++;
            }
        }
    }

    public T get(int index) {
        checkIndex(index);
        if(size / 2 <= index){
            //идем с хвоста
            NodeForDouble<T> current = tail;
            for(int i = size - 1; i > index ; i--){
                current = current.prev;
            }
            return current.data;
        }
        else{
            //идем с головы
            NodeForDouble<T> current = head;
            for(int i = 0 ; i < index ; i++){
                current = current.next;
            }
            return current.data;
        }
    }
    public T removeLast(){
        if(size == 0){
            throw new IndexOutOfBoundsException();
        }
        T remember = tail.data;
        if(size == 1){
            head = tail = null;
        }
        else{
            tail.prev.next = null;
            tail = tail.prev;
        }
        size--;
        return remember;
    }
    public T removeFirst(){
        if(size == 0){
            throw new IndexOutOfBoundsException();
        }
        T remember = head.data;
        if(size == 1){
            head = tail = null;
        }
        else{
            head.next.prev = null;
            head = head.next;
        }
        size--;
        return remember;
    }
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            return removeFirst();
        }
        else if (index == size - 1) {
            return removeLast();
        }
        else {
            if (size / 2 <= index) {
                //идем с хвоста
                NodeForDouble<T> current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                T remember = current.data;
                NodeForDouble<T> prev = current.prev;
                NodeForDouble<T> next = current.next;
                prev.next = next;
                next.prev = prev;
                size--;
                return remember;
            } else {
                //идем с головы
                NodeForDouble<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                T remember = current.data;
                NodeForDouble<T> prev = current.prev;
                NodeForDouble<T> next = current.next;
                prev.next = next;
                next.prev = prev;
                size--;
                return remember;
            }
        }
    }
    public boolean remove(T item) {
        NodeForDouble<T> current = head;
        for(int i = 0 ; i < size ;i++){
            if(Objects.equals(current.data , item)){
                if(current == head){
                    removeFirst();
                }
                else if(current == tail){
                    removeLast();
                }
                else{
                    NodeForDouble<T> prev = current.prev;
                    NodeForDouble<T> next = current.next;
                    prev.next = next;
                    next.prev = prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean contains(T item) {
        NodeForDouble<T> current = head;
        for(int i = 0 ; i < size ;i++){
            if(Objects.equals(current.data , item)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public int indexOf(T item) {
        NodeForDouble<T> current = head;
        for(int i = 0 ; i < size ;i++){
            if(Objects.equals(current.data , item)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }
}
