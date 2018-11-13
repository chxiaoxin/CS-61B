package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public abstract int fillCount();
    public abstract void enqueue(T x);
    public abstract T peek();
    public abstract T dequeue();
}
