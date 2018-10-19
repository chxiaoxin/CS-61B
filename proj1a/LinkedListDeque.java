public class LinkedListDeque<T> {

    private class Node<T> {

        private T value;
        private Node next;
        private Node prev;

        public Node(T value, Node nextNode, Node prevNode) {
            this.value = value;
            this.next = nextNode;
            this.prev = prevNode;
        }
    }

    private Node head;
    private int size;

    public LinkedListDeque() {
        Node sentinel = new Node(0, null, null);
        this.head = new Node(0, sentinel,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = 0;
    }

//    public LinkedListDeque(T item) {
//        Node sentinel = new Node(0, null, null);
//        this.head = new Node(0, sentinel,null);
//        Node newNode = new Node(item, sentinel, sentinel);
//        sentinel.next = newNode;
//        sentinel.prev = newNode;
//        this.size += 1;
//    }

    public void addFirst(T item) {
        Node sentinel = this.head.next;
        Node prevFirst = sentinel.next;
        Node currFirst = new Node(item, prevFirst, sentinel);
        sentinel.next = currFirst;
        prevFirst.prev = currFirst;
        this.size += 1;
    }

    public void addLast(T item){
       Node sentinel = this.head.next;
       Node prevLast = sentinel.prev;
       Node currLast = new Node(item, sentinel, prevLast);
       prevLast.next = currLast;
       sentinel.prev = currLast;
       this.size += 1;
    }

    public boolean isEmpty() {
        return this.size() == 0 ? true: false;
    }
    public int size(){
        return this.size;
    }

    public T get(int index) {
        if (this.size() <= index){
            return null;
        }
        int count = 0;
        Node ptr = this.head.next.next;
        while (count < index){
            count += 1;
            ptr = ptr.next;
        }
        return (T) ptr.value;
    }

    private T getRecurHelper(int index, Node node) {
        if (index == 0) {
            return (T) node.value;
        } else {
            return (T) getRecurHelper(index - 1, node.next);
        }
    }

    public T getRecursive(int index) {
        if (index >= this.size()) {
            return null;
        } else {
            return (T) getRecurHelper(index, this.head.next.next);
        }
    }
    public T removeFirst() {
        Node sentinel = this.head.next;
        Node currFirst = sentinel.next;
        Node currSecond = currFirst.next;
        sentinel.next = currSecond;
        currSecond.prev = sentinel;
        this.size -= 1;
        return (T) currFirst.value;
    }

    public T removeLast() {
        Node sentinel = this.head.next;
        Node currLast = sentinel.prev;
        Node currSecond = currLast.prev;
        sentinel.prev = currSecond;
        currSecond.next = sentinel;
        this.size -= 1;
        return (T) currLast.value;
    }

    public void printDeque() {
        int count = 0;
        Node ptr = this.head.next.next;
        while (count < this.size()) {
            count += 1;
            System.out.print(ptr.value.toString() + " ");
            ptr = ptr.next;
        }
    }
}
