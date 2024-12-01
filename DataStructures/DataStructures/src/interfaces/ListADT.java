package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {

    /**
     * Removes the first element of this list
     * @return The removed element
     * @throws EmptyCollectionException If this list is empty
     */
    T removeFirst() throws EmptyCollectionException;

    /**
     * Removes the last element of this list
     * @return The removed element
     * @throws EmptyCollectionException If this list is empty
     */
    T removeLast() throws EmptyCollectionException;

    /**
     * Removes a specified element from this list
     * @param element The element to be removed
     * @return The removed element
     * @throws EmptyCollectionException If this list is empty
     * @throws ElementNullException If the element is null
     * @throws ElementNotFoundException If the element isn't in this list
     */
    T remove(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException;

    /**
     * Clears this list
     */
    void removeAll();

    /**
     * @return The first element of this list
     * @throws EmptyCollectionException If this list is empty
     */
    T first() throws EmptyCollectionException;

    /**
     * @return The last element from this list
     * @throws EmptyCollectionException If this list is empty
     */
    T last() throws EmptyCollectionException;

    /**
     * @return An array representation of this list
     */
    Object[] toArray();

    /**
     * Checks if a specified element is on this list
     * @param element The element to look in this list
     * @return A boolean value indicating if the element is in this list or not
     * @throws ElementNullException If the element is null
     */
    boolean contains(T element) throws ElementNullException;

    /**
     * Checks if this list is empty
     * @return A boolean value indicating if the list is empty or not
     */
    boolean isEmpty();

    /**
     * @return This list's size
     */
    int size();

    /**
     * @return An iterator over this list's element in proper sequence
     */
    Iterator<T> iterator();

    /**
     * @return A string representation of this list
     */
    @Override String toString();
}
