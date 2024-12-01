package objects.queues;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.QueueADT;

import java.util.Arrays;

public class ArrayQueue<T> implements QueueADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    protected T[] array;
    protected int count;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, 1);
        array = (T[]) new Object[initialCapacity];
        count = 0;
    }

    @Override
    public T enqueue(T element)  {

        if (size() == array.length) expandCapacity();
        array[count++] = element;

        return element;
    }

    @Override
    public T dequeue()  {

        T element = array[0];
        count--;
        System.arraycopy(array, 1, array, 0, size());

        return element;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Queue is empty");

        return array[0];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        StringBuilder message = new StringBuilder("(" + size() + ")[");
        for (int i = 0; i < size(); i++) {
            message.append(array[i]).append((i == size() - 1) ? "]" : ", ");
        }

        return String.valueOf(message);
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] tempArray = (T[]) new Object[(array.length == 1) ? 2 : array.length + (array.length / 2)];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }
}
