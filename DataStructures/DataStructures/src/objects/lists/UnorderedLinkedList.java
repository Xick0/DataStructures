package objects.lists;

import abstractobjects.lists.LinkedList;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import interfaces.UnorderedListADT;
import objects.nodes.Node;

public class UnorderedLinkedList<T> extends LinkedList<T> implements UnorderedListADT<T> {
    public UnorderedLinkedList() {
        super();
    }

    @Override
    public T addToFront(T element){


        addElement(null, element);

        return element;
    }

    @Override
    public T addToRear(T element){
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

    private void addElement(Node<T> currentNode, T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (currentNode == null) {
            newNode.setNext(head);
            head = newNode;
        } else {
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
            if (currentNode == tail) tail = newNode;
        }

        count++;
        modCount++;
    }
}
