package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue {
    protected int fillCount;
    protected int capacity;
    public abstract void enqueue(Object x);
    public abstract T peek();
    public abstract T dequeue();
}
