
public class ArrayDeque<T> {

    private int head;
    private int tail;
    private T[] elements;
    private final int CAPACITY = 8;

    public ArrayDeque() {
        elements = (T[]) new Object[CAPACITY];
    }

    public void addFirst(T item) {
        this.elements[head = (head - 1) & (this.elements.length - 1)] = item;
        if (head == tail) {
            resize();
        }
    }

    public void addLast(T item) {
        this.elements[tail = (tail + 1) & (this.elements.length - 1)] = item;
        if (head == tail) {
            resize();
        }
    }

    public void resize() {
        assert head == tail;
        T[] a = (T[]) new Object[this.elements.length << 1];
        int n = this.elements.length;
        int r = n - head;
        System.arraycopy(this.elements, head, a, 0, r);
        System.arraycopy(this.elements, 0, a, r, head);
        this.elements = a;
        head = 0;
        tail = n;
    }

    public boolean isEmpty() {
        return head == tail ? true : false;
    }

    public int size() {
        return elements.length;
    }

    public void printDeque() {
        for (Object o: elements) {
            if (o != null) {
                System.out.print(o.toString() + " ");
            }
        }
    }

    public T removeFirst() {
        T removedItem = elements[head];
        elements[head] = null;
        head = (head + 1) & (elements.length - 1);
        return removedItem;
    }

    public T removeLast() {
        T removedItem = elements[tail];
        elements[tail] = null;
        tail = (tail - 1) & (elements.length - 1);
        return removedItem;
    }

    public Object get(int index) {
        int ptr = head;
        int count = index;
        while (count > 0) {
            ptr = (ptr + 1) & (elements.length - 1);
            count -= 1;
        }
        return elements[ptr];
    }
}
