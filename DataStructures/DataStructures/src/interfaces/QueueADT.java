package interfaces;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

public interface QueueADT<T> {

    /**
     * Adds an element to this queue
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T enqueue(T element);

    /**
     * Removes the first element of this queue
     * @return The removed element
     * @throws EmptyCollectionException If this queue is empty
     */
    T dequeue();

    /**
     * @return The first element of this queue
     * @throws EmptyCollectionException If this queue is empty
     */
    T first() throws EmptyCollectionException;

    /**
     * Checks if this queue is empty
     * @return A boolean value indicating if this queue is empty or not
     */
    boolean isEmpty();

    /**
     * @return This queue's size
     */
    int size();

    /**
     * @return A string representation of this queue
     */
    @Override String toString();
}
