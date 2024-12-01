package objects.binarysearchtrees;

import abstractobjects.binarytrees.LinkedBinaryTree;
import exceptions.ElementNotComparableException;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.BinarySearchTreeADT;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T element) throws ElementNullException {
        super(element);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T addElement(T element) throws ElementNullException, ElementNotComparableException {
        if (element == null) throw new ElementNullException("Element is null");
        if (!(element instanceof Comparable<?>)) throw new ElementNotComparableException("Element not comparable");

        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(element);
        if (isEmpty()) {
            root = newNode;
        } else {
            Comparable<T> comparableElement = (Comparable<T>) element;
            BinaryTreeNode<T> currentNode = root;
            while (true) {
                if (comparableElement.compareTo(currentNode.getElement()) < 0) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(newNode);
                        break;
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(newNode);
                        break;
                    } else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
        count++;

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T removeElement(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");
        if (element == null) throw new ElementNullException("Element is null");

        Comparable<T> comparableElement = (Comparable<T>) element;
        if (element.equals(root.getElement())) {
            root = replacement(root);
        } else {
            BinaryTreeNode<T> current = ((comparableElement.compareTo(root.getElement()) < 0) ? root.getLeft() : root.getRight()), parent = root;
            boolean found = false;

            while (current != null && !found) {
                if (element.equals(current.getElement())) {
                    found = true;
                    if (current == parent.getLeft()) parent.setLeft(replacement(current));
                    else parent.setRight(replacement(current));
                } else {
                    parent = current;
                    current = ((comparableElement.compareTo(current.getElement()) < 0) ? current.getLeft() : current.getRight());
                }
            }

            if (!found) throw new ElementNotFoundException("Element not found");
        }
        count--;

        return element;
    }

    @Override
    public T removeAllOccurrences(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");
        if (element == null) throw new ElementNullException("Element is null");
        if (!contains(element)) throw new ElementNotFoundException("Element not found");

        while (true) {
            try {
                removeElement(element);
            } catch (ElementNotFoundException ignored) {
                break;
            }
        }

        return element;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        try {
            return removeElement(findMin());
        } catch (ElementNotFoundException | ElementNullException ignored) {}

        return null;
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        try {
            return removeElement(findMax());
        } catch (ElementNotFoundException | ElementNullException ignored) {}

        return null;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        BinaryTreeNode<T> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getElement();
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinarySearchTree is empty");

        if (root.getRight() == null) return root.getElement();
        BinaryTreeNode<T> current = root.getRight();
        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current.getElement();
    }

    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> currentNode) {
        BinaryTreeNode<T> result;
        if (currentNode.getLeft() == null && currentNode.getRight() == null) result = null;
        else if (currentNode.getLeft() != null && currentNode.getRight() == null) result = currentNode.getLeft();
        else if (currentNode.getLeft() == null && currentNode.getRight() != null) result = currentNode.getRight();
        else {
            BinaryTreeNode<T> current = currentNode.getRight(), parent = currentNode;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }

            if (currentNode.getRight() == current) current.setLeft(currentNode.getLeft());
            else {
                parent.setLeft(current.getRight());
                current.setRight(currentNode.getRight());
                current.setLeft(currentNode.getLeft());
            }

            result = current;
        }

        return result;
    }
}
