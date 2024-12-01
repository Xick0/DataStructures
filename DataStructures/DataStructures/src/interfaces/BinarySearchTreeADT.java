package interfaces;

import exceptions.ElementNotComparableException;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds an element to this binary search tree
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T addElement(T element) throws ElementNullException, ElementNotComparableException;

    /**
     * Removes one instance of a specified element from this binary search tree
     * @param element The element to be removed
     * @return The removed element
     * @throws EmptyCollectionException If this binary search tree is empty
     * @throws ElementNullException If the element is null
     * @throws ElementNotFoundException If the element isn't in this binary search tree
     */
    T removeElement(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException;

    /**
     * Removes all instances of a specified element from this binary search tree
     * @param element The element to be removed
     * @return The removed element
     * @throws EmptyCollectionException If this binary search tree is empty
     * @throws ElementNullException If the element is null
     * @throws ElementNotFoundException If the element isn't in this binary search tree
     */
    T removeAllOccurrences(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException;

    /**
     * Removes the smallest element from this binary search tree
     * @return The removed element
     * @throws EmptyCollectionException If this binary search tree is empty
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * Removes the biggest element from this binary search tree
     * @return The removed element
     * @throws EmptyCollectionException If this binary search tree is empty
     */
    T removeMax() throws EmptyCollectionException;

    /**
     * Finds the smallest element in this binary search tree
     * @return The smallest element
     * @throws EmptyCollectionException If this binary search tree is empty
     */
    T findMin() throws EmptyCollectionException;

    /**
     * Finds the biggest element in this binary search tree
     * @return The biggest element
     * @throws EmptyCollectionException If this binary search tree is empty
     */
    T findMax() throws EmptyCollectionException;
}
