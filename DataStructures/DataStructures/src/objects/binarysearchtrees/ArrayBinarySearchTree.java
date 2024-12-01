package objects.binarysearchtrees;

import abstractobjects.binarytrees.ArrayBinaryTree;
import exceptions.ElementNotComparableException;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.BinarySearchTreeADT;

public abstract class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height, maxIndex;

    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    public ArrayBinarySearchTree(int initialCapacity) {
        super(initialCapacity);
        height = 0;
        maxIndex = -1;
    }

    public ArrayBinarySearchTree(T element) throws ElementNullException {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    public ArrayBinarySearchTree(int initialCapacity, T element) throws ElementNullException {
        super(initialCapacity, element);
        height = 1;
        maxIndex = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T addElement(T element) throws ElementNullException, ElementNotComparableException {
        if (element == null) throw new ElementNullException("Element is null");
        if (!(element instanceof Comparable<?>)) throw new ElementNotComparableException("Element not comparable");

        if (array.length < maxIndex * 2 + 3) expandCapacity();
        if (isEmpty()) {
            array[0] = element;
            maxIndex = 0;
        } else {
            Comparable<T> comparableElement = (Comparable<T>) element;
            int currentIndex = 0;
            while (true) {
                if (comparableElement.compareTo(array[currentIndex]) < 0) {
                    if (array[currentIndex * 2 + 1] == null) {
                        array[currentIndex * 2 + 1] = element;
                        if (currentIndex * 2 + 1 > maxIndex) maxIndex = currentIndex * 2 + 1;
                        break;
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    if (array[currentIndex * 2 + 2] == null) {
                        array[currentIndex * 2 + 2] = element;
                        if (currentIndex * 2 + 2 > maxIndex) maxIndex = currentIndex * 2 + 2;
                        break;
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int)((Math.log(maxIndex + 1) / Math.log(2)) + 1);
        count++;

        return element;
    }

    @Override
    public T removeElement(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");
        if (element == null) throw new ElementNullException("Element is null");



        return null;
    }

    @Override
    public T removeAllOccurrences(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");
        if (element == null) throw new ElementNullException("Element is null");
        if (!contains(element)) throw new ElementNotFoundException("Element not found");

        while (contains(element)) {
            try {
                removeElement(element);
            } catch (ElementNotFoundException ignored) {}
        }

        return element;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        return null;
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        return null;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        return null;
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        return null;
    }

    protected void replacement(int currentIndex) {

    }
}
