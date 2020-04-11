package test3;

/**
 * @author zhangjihe
 *
 * @date 2020-04-11
 * @param <T> the element type of queue
 */
public class Queue<T> {

    private Object[] array;

    /**
     * size of array
     */
    private int arrSize;

    /**
     * the index pointing to the head element
     */
    private int head;

    /**
     * the index pointing to the next element which will be pushed
     */
    private int tail;

    public Queue(int capacity) {
        this.arrSize = capacity+1;
        this.array = new Object[arrSize];
        head = 0;
        tail = 0;
    }

    public void push(T t) throws FullException {
        if (full()) {
            throw new FullException();
        }
        array[tail] = t;
        tail = (tail == arrSize - 1) ? 0 : tail + 1;
    }

    @SuppressWarnings("unchecked")
    public T pop() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        }
        T res = (T) array[head];
        head = (head == arrSize - 1) ? 0 : head + 1;
        return res;
    }

    @SuppressWarnings("unchecked")
    public T peek() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        }
        return (T) array[head];
    }

    public boolean empty() {
        return tail == head;
    }

    public boolean full() {
        return (tail + 1) % arrSize == head;
    }
}

/**
 * throw it when trying to enqueue an element but the queue is full
 */
class FullException extends RuntimeException {
}

/**
 * throw it when trying to deque an element but the queue is empty
 */
class EmptyException extends RuntimeException {
}

