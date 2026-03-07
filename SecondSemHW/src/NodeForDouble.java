class NodeForDouble<T> {
    protected T data;
    protected NodeForDouble<T> next;
    protected NodeForDouble<T> prev;

    public NodeForDouble(T data) {
        this.data = data;
        this.next = null;
    }
}
