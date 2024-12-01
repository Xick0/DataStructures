package abstractobjects.binarytrees;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import interfaces.QueueADT;
import interfaces.UnorderedListADT;
import objects.lists.UnorderedArrayList;
import objects.queues.ArrayQueue;

import java.util.Iterator;

public abstract class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    private static final int DEFAULT_CAPACITY = 100;
    protected T[] array;
    protected int count = 0;

    public ArrayBinaryTree() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBinaryTree(T element) throws ElementNullException {
        this(DEFAULT_CAPACITY, element);
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, 1);
        count = 0;
        array = (T[]) new Object[initialCapacity];
    }

    /**
     * @param initialCapacity Minimum value is 1
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(int initialCapacity, T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        initialCapacity = Math.max(initialCapacity, 1);
        array = (T[]) new Object[initialCapacity];
        array[0] = element;
        count = 1;
    }

    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinaryTree is empty");

        return array[0];
    }

    @Override
    public T find(T element) throws ElementNullException, EmptyCollectionException, ElementNotFoundException {
        if (element == null) throw new ElementNullException("Element is null");
        if (isEmpty()) throw new EmptyCollectionException("BinaryTree is empty");

        for (int i = 0; i < size(); i++) {
            if (element.equals(array[i])) return element;
        }

        throw new ElementNotFoundException("Element not found");
    }

    @Override
    public boolean contains(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        try {
            find(element);
            return true;
        } catch (ElementNullException | EmptyCollectionException | ElementNotFoundException ignored) {
            return false;
        }
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
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> list = new UnorderedArrayList<>();
        inOrder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> list = new UnorderedArrayList<>();
        preOrder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> list = new UnorderedArrayList<>();
        postOrder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedListADT<T> list = new UnorderedArrayList<>();
        levelOrder(0, list);

        return list.iterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        Iterator<T> treeIterator = iteratorLevelOrder();
        int counter = 1;
        StringBuilder message = new StringBuilder("(" + size() + ")[");
        while (treeIterator.hasNext() && counter <= size()) {
            message.append(treeIterator.next()).append((counter < size()) ? ", " : "]");
            counter++;
        }

        return String.valueOf(message);
    }

    private void inOrder(int currentIndex, UnorderedListADT<T> list) {

            if (currentIndex < array.length && array[currentIndex] != null) {
                inOrder(currentIndex * 2 + 1, list);
                list.addToRear(array[currentIndex]);
                inOrder((currentIndex + 1) * 2, list);
            }

    }

    private void preOrder(int currentIndex, UnorderedListADT<T> list) {

            if (currentIndex < array.length && array[currentIndex] != null) {
                list.addToRear(array[currentIndex]);
                preOrder(currentIndex * 2 + 1, list);
                preOrder((currentIndex + 1) * 2, list);
            }

    }

    private void postOrder(int currentIndex, UnorderedListADT<T> list) {
            if (currentIndex < array.length && array[currentIndex] != null) {
                postOrder(currentIndex * 2 + 1, list);
                postOrder((currentIndex + 1) * 2, list);
                list.addToRear(array[currentIndex]);
            }
    }

    private void levelOrder(int currentIndex, UnorderedListADT<T> list) {
        if (currentIndex < array.length && array[currentIndex] != null) {
            QueueADT<Integer> queue = new ArrayQueue<>();
            queue.enqueue(currentIndex);
            while (!queue.isEmpty()) {
                int dequeuedIndex = queue.dequeue();
                list.addToRear(array[dequeuedIndex]);
                int leftChild = dequeuedIndex * 2 + 1;
                int rightChild = (dequeuedIndex + 1) * 2;
                if (leftChild < array.length && array[leftChild] != null) queue.enqueue(leftChild);
                if (rightChild < array.length && array[rightChild] != null) queue.enqueue(rightChild);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void expandCapacity() {
        T[] tempArray = (T[]) new Object[array.length * 3];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }
}
