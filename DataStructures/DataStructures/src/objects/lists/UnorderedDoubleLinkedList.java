package objects.lists;

import abstractobjects.lists.DoubleLinkedList;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import interfaces.UnorderedListADT;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
    public UnorderedDoubleLinkedList() {
        super();
    }

    @Override
    public T addToFront(T element) {

        addElement(null, element);

        return element;
    }

    @Override
    public T addToRear(T element) {
        addElement(tail, element);

        return element;
    }

    @Override
    public T addAfter(T element, T target) throws ElementNullException, ElementNotFoundException {
        if (element == null || target == null) throw new ElementNullException("Element is null");
        if (!contains(target)) throw new ElementNotFoundException("Element not found");

        addElement(targetNode, element);

        return element;
    }

    private void addElement(DoubleNode<T> currentNode, T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (currentNode == null) {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        } else {
            newNode.setNext(currentNode.getNext());
            if (currentNode.getNext() != null) currentNode.getNext().setPrev(newNode);
            currentNode.setNext(newNode);
            newNode.setPrev(currentNode);
            if (currentNode == tail) tail = newNode;
        }

        count++;
        modCount++;
    }
}
