package objects.lists;

import abstractobjects.lists.LinkedList;
import exceptions.ElementNotComparableException;
import exceptions.ElementNullException;
import interfaces.OrderedListADT;
import objects.nodes.Node;

public class OrderedLinkedList<T> extends LinkedList<T> implements OrderedListADT<T> {
    public OrderedLinkedList() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T add(T element) throws ElementNullException, ElementNotComparableException {
        if (element == null) throw new ElementNullException("Element is null");
        if (!(element instanceof Comparable<?>)) throw new ElementNotComparableException("Element not comparable");

        Comparable<T> comparableElement = (Comparable<T>) element;
        Node<T> newNode = new Node<>(element);
        if (isEmpty() || comparableElement.compareTo(head.getElement()) < 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            targetNode = null;
            Node<T> currentNode = head;
            while (currentNode != null && comparableElement.compareTo(currentNode.getElement()) > 0) {
                targetNode = currentNode;
                currentNode = currentNode.getNext();
            }

            if (targetNode == null) {
                head = newNode;
            } else {
                targetNode.setNext(newNode);
            }
            newNode.setNext(currentNode);
        }
        if (tail == null || comparableElement.compareTo(tail.getElement()) > 0) tail = newNode;

        count++;
        modCount++;

        return element;
    }
}
