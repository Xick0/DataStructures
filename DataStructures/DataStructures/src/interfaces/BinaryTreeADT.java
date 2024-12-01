package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface BinaryTreeADT<T> {

    /**
     * @return The root element of this binary tree
     * @throws EmptyCollectionException If this binary tree is empty
     */
    T getRoot() throws EmptyCollectionException;

    /**
     * Checks if a specified element is in this binary tree
     * @param element The element to search in this binary tree
     * @return The specified element
     * @throws ElementNullException If the element is empty
     * @throws EmptyCollectionException If this binary tree is empty
     * @throws ElementNotFoundException If the specified element is not found in this binary tree
     */
    T find(T element) throws ElementNullException, EmptyCollectionException, ElementNotFoundException;

    /**
     * Checks if a specified element is in this binary tree
     * @param element The element to look in this binary tree
     * @return A boolean value indicating if the element is in this binary tree or not
     * @throws ElementNullException If the element is null
     */
    boolean contains(T element) throws ElementNullException;

    /**
     * Checks if this binary tree is empty
     * @return A boolean value indicating if this binary tree is empty or not
     */
    boolean isEmpty();

    /**
     * @return This binary tree's size
     */
    int size();

    /**
     * Performs an inorder traversal on this binary tree by calling an overloaded, recursive inorder method that starts with the root.
     * @return An iterator over the elements in this binary tree
     */
    Iterator<T> iteratorInOrder();

    /**
     * Performs a preorder traversal on this binary tree by calling an overloaded, recursive preorder method that starts with the root.
     * @return An iterator over the elements in this binary tree
     */
    Iterator<T> iteratorPreOrder();

    /**
     * Performs a postorder traversal on this binary tree by calling an overloaded, recursive postorder method that starts with the root.
     * @return An iterator over the elements in this binary tree
     */
    Iterator<T> iteratorPostOrder();

    /**
     * Performs a levelorder traversal on this binary tree using a queue.
     * @return An iterator over the elements in this binary tree
     */
    Iterator<T> iteratorLevelOrder();

    /**
     * @return A string representation of this binary tree
     */
    @Override String toString();
}
