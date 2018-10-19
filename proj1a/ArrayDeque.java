import java.util.Arrays;

public class ArrayDeque {

    private int head;
    private int tail;
    private Object[] elements;
    private final int CAPACITY=8;

    public ArrayDeque(){
        elements = new Object[CAPACITY];
    }

    public void addFisrt(Object item){
        this.elements[head = (head - 1) & (this.elements.length - 1)] = item;
        if (head == tail){
            resize();
        }
    }

    public void addLast(Object item){
        this.elements[tail = (tail + 1) & (this.elements.length - 1)] = item;
        if (head == tail){
            resize();
        }
    }

    public void resize(){
        assert head == tail;
        Object[] a = new Object[this.elements.length << 1];
        int n = this.elements.length;
        int r = n - head;
        System.arraycopy(this.elements, head, a, 0, r);
        System.arraycopy(this.elements, 0, a, r, head);
        this.elements = a;
        head = 0;
        tail = n;
    }

    public boolean isEmpty(){
        return head == tail ? true:false;
    }

    public int size(){
        return elements.length;
    }

    public void printDeque(){
        for(Object o: elements){
            if(o != null){
                System.out.print(o.toString() + " ");
            }
        }
    }

    public Object removeFirst(){
        Object removedItem = elements[head];
        elements[head] = null;
        head = (head + 1) & (elements.length - 1);
        return removedItem;
    }

    public Object removeLast(){
        Object removedItem = elements[tail];
        elements[tail] = null;
        tail = (tail - 1) & (elements.length - 1);
        return removedItem;
    }

    public Object get(int index){
        int ptr = head;
        int count = index;
        while(count > 0){
            ptr = (ptr + 1) & (elements.length - 1);
            count -= 1;
        }
        return elements[ptr];
    }

    public static void main(String[] args) {
        ArrayDeque dq = new ArrayDeque();
        dq.addFisrt(1);
        dq.addFisrt(2);
        dq.addFisrt(3);
        dq.addFisrt(4);
        dq.addFisrt(5);
        dq.addFisrt(6);
        dq.addFisrt(7);
        dq.addFisrt(8);
        dq.addFisrt(9);
        System.out.println(dq.get(3));
        dq.printDeque();
    }
}
