package objects.stacks;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.StackADT;

public class ArrayStack<T> implements StackADT<T> {
    private static final int DEFAULT_CAPACITY = 50;
    protected T[] array;
    protected int count;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, 1);
        array = (T[]) new Object[initialCapacity];
        count = 0;
    }

    @Override
    public T push(T element)  {

        if (size() == array.length) expandCapacity();
        array[count++] = element;

        return element;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Stack is empty");

        return array[--count];
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Stack is empty");

        return array[count - 1];
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
