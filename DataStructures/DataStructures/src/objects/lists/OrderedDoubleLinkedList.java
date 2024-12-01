package objects.lists;

import abstractobjects.lists.DoubleLinkedList;
import exceptions.ElementNotComparableException;
import exceptions.ElementNullException;
import interfaces.OrderedListADT;

public class OrderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {
    public OrderedDoubleLinkedList() {
        super();
    }

    @Override
    public T add(T element) throws ElementNullException, ElementNotComparableException {
        if (element == null) throw new ElementNullException("Element is null");
        if (!(element instanceof Comparable<?>)) throw new ElementNotComparableException("Element not comparable");

        Comparable<T> comparableElement = (Comparable<T>) element;
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if (isEmpty() || comparableElement.compareTo(head.getElement()) < 0) {
            newNode.setNext(head);
            if (head != null) head.setPrev(newNode);
            head = newNode;
        } else {
            targetNode = null;
            DoubleNode<T> currentNode = head;
            while (currentNode != null && comparableElement.compareTo(currentNode.getElement()) > 0) {
                targetNode = currentNode;
                currentNode = currentNode.getNext();
            }

            if (targetNode == null) {
                head = newNode;
            } else {
                targetNode.setNext(newNode);
            }
            newNode.setPrev(targetNode);
            newNode.setNext(currentNode);
            if (currentNode != null) currentNode.setPrev(newNode);
        }
        if (tail == null || comparableElement.compareTo(tail.getElement()) > 0) tail = newNode;

        count++;
        modCount++;

        return element;
    }
}
