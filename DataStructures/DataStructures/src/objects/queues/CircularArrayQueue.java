package objects.queues;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.QueueADT;

import java.util.Arrays;

public class CircularArrayQueue<T> implements QueueADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    protected T[] array;
    protected int count, firstIndex, lastIndex;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, 1);
        array = (T[]) new Object[initialCapacity];
        count = 0;
        firstIndex = 0;
        lastIndex = 0;
    }

    @Override
    public T enqueue(T element) {

        if (size() == array.length - 1) expandCapacity();
        array[lastIndex] = element;
        lastIndex = (lastIndex + 1) % array.length;
        count++;

        return element;
    }

    @Override
    public T dequeue()  {
        T element = array[firstIndex];
        firstIndex = (isEmpty()) ? 0 : (firstIndex + 1) % array.length;
        lastIndex = (isEmpty()) ? 0 : lastIndex;
        count--;

        return element;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Queue is empty");

        return array[firstIndex];
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
        for (int i = firstIndex; i != lastIndex; i = (i + 1) % array.length) {
            message.append(array[i]).append((i == (lastIndex - 1)) ? "]" : ", ");
        }

        return String.valueOf(message);
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] newArray = (T[]) new Object[(array.length == 1) ? 2 : array.length + (array.length / 2)];

        int counter = 0;
        for (int i = firstIndex; counter < size(); i = (i + 1) % array.length) {
            newArray[counter++] = array[i];
        }
        firstIndex = 0;
        lastIndex = counter;

        array = newArray;
    }
}
