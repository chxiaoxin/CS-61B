package synthesizer;
import java.io.IOException;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue implements Iterable {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        if (capacity > 8) {
            capacity |= capacity >>> 1;
            capacity |= capacity >>> 2;
            capacity |= capacity >>> 4;
            capacity |= capacity >>> 8;
            capacity |= capacity >>> 16;
        } else {
            capacity = 3;
        }
        this.capacity = capacity + 1;
        rb = (T[]) new Object[this.capacity];
        this.fillCount = 0;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(Object x) {
        if (isFull()){
            try {
                throw new IOException("already Full");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rb[this.last] = (T) x;
        this.last = (this.last + 1) & (this.capacity - 1);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()){
            try {
                throw new RuntimeException("already empty");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        T result = rb[this.first];
        rb[this.first] = null;
        this.first = (this.first + 1) & (this.capacity -1);
        this.fillCount -= 1;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            try {
                throw new RuntimeException("already empty");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return rb[first];
    }

    private class bufferIterator implements Iterator<T> {

        private int ptr;

        public bufferIterator() {
            ptr = first;
        }

        public T peek(int ptr) {
            return rb[ptr++];
        }

        @Override
        public boolean hasNext() {
            return (ptr == last) ? false:true;
        }

        @Override
        public T next() {
            return peek(ptr);
        }
    }

    public Iterator iterator() {
        return new bufferIterator();
    }

}
