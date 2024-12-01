package objects.stacks;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.StackADT;
import objects.nodes.Node;

public class LinkedStack<T> implements StackADT<T> {
    protected Node<T> top;
    protected int count;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    @Override
    public T push(T element)  {
        Node<T> newNode = new Node<>(element);
        if (!isEmpty()) newNode.setNext(top);
        top = newNode;
        count++;

        return element;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Stack is empty");

        T element = top.getElement();
        top = top.getNext();
        count--;

        return element;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Stack is empty");

        return top.getElement();
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

        return "(" + size() + ")[" + recursiveToString(top);
    }

    private String recursiveToString(Node<T> currentNode) {
        if (currentNode == null) return "";

        return recursiveToString(currentNode.getNext()) + currentNode.getElement() + ((currentNode == top) ? "]" : ", ");
    }
}
