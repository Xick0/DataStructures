package interfaces;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds an element to this heap
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T addElement(T element);

    /**
     * Removes the minimum value from this heap
     * @return The removed element
     * @throws EmptyCollectionException If this heap is empty
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * @return The minimum value from this heap
     * @throws EmptyCollectionException If this heap is empty
     */
    T findMin() throws EmptyCollectionException;
}
