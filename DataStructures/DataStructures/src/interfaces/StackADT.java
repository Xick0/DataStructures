package interfaces;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

public interface StackADT<T> {

    /**
     * Adds an element to this stack
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T push(T element);

    /**
     * Removes the top element of this stack
     * @return The removed element
     * @throws EmptyCollectionException If this stack is empty
     */
    T pop() throws EmptyCollectionException;

    /**
     * @return The top element of this stack
     * @throws EmptyCollectionException If this stack is empty
     */
    T peek() throws EmptyCollectionException;

    /**
     * Checks if this stack is empty
     * @return A boolean value indicating if this stack is empty or not
     */
    boolean isEmpty();

    /**
     * @return This stack's size
     */
    int size();

    /**
     * @return A string representation of this stack
     */
    @Override String toString();
}
