package abstractobjects.lists;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.ListADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class ArrayList<T> implements ListADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    protected T[] array;
    protected int count, modCount, targetIndex;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, 1);
        array = (T[]) new Object[initialCapacity];
        count = 0;
        modCount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        T element = array[0];
        removeElement(0);

        return element;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        T element = array[count - 1];
        removeElement(count - 1);

        return element;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");
        if (element == null) throw new ElementNullException("Element is null");
        if (!contains(element)) throw new ElementNotFoundException("Element not found");

        removeElement(targetIndex);

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeAll() {
        array = (T[]) new Object[count];
        count = 0;
        modCount++;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        return array[0];
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        return array[count - 1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        T[] list = (T[]) ((array[0] instanceof Comparable<?>) ? new Comparable[size()] : new Object[size()]);
        System.arraycopy(array, 0, list, 0, size());

        return list;
    }

    @Override
    public boolean contains(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        for (int i = 0; i < size(); i++) {
            if (array[i].equals(element)) {
                targetIndex = i;
                return true;
            }
        }

        return false;
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
    public Iterator<T> iterator() {
        return new ArrayIterator();
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
    protected void expandCapacity() {
        T[] tempArray = (T[]) new Object[(array.length == 1) ? array.length + array.length : array.length + (array.length / 2)];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }

    private void removeElement(int index) {
        count--;
        System.arraycopy(array, index + 1, array, index, count - index);
        modCount++;
    }

    private class ArrayIterator implements Iterator<T> {
        private int expectedModCount, currentIndex;
        private boolean okToRemove;

        public ArrayIterator() {
            expectedModCount = modCount;
            currentIndex = 0;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!hasNext()) throw new IndexOutOfBoundsException("Out of elements");

            okToRemove = true;
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!okToRemove) throw new UnsupportedOperationException("Operation not supported");

            ArrayList.this.removeElement(currentIndex - 1);

            expectedModCount = modCount;
            okToRemove = false;
        }
    }
}
