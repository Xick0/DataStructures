package objects.queues;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.QueueADT;
import objects.nodes.Node;

public class LinkedQueue<T> implements QueueADT<T> {
    protected Node<T> first, last;
    protected int count;

    public LinkedQueue() {
        first = null;
        last = null;
        count = 0;
    }

    @Override
    public T enqueue(T element) {
//        if (element == null) throw new ElementNullException("Element is null");

        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        count++;

        return element;
    }

    @Override
    public T dequeue()  {
        T element = first.getElement();
        first = first.getNext();
        count--;

        return element;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Queue is empty");

        return first.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        StringBuilder message = new StringBuilder("(" + size() + ")[");
        Node<T> currentNode = first;
        while (currentNode != null) {
            message.append(currentNode.getElement()).append((currentNode.getNext() == null) ? "]" : ", ");
            currentNode = currentNode.getNext();
        }

        return String.valueOf(message);
    }
}
