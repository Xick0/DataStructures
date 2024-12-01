package abstractobjects.lists;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.ListADT;
import objects.nodes.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class LinkedList<T> implements ListADT<T> {
    protected Node<T> head, tail, previousNode, targetNode;
    protected int count, modCount;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
        modCount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        T element = head.getElement();
        try {
            contains(element);
        } catch (ElementNullException ignored) {}
        removeElement();

        return element;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        T element = tail.getElement();
        try {
            contains(element);
        } catch (ElementNullException ignored) {}
        removeElement();

        return element;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNullException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");
        if (element == null) throw new ElementNullException("Element is null");
        if (!contains(element)) throw new ElementNotFoundException("Element not found");

        removeElement();

        return element;
    }

    @Override
    public void removeAll() {
        head = null;
        tail = null;
        count = 0;
        modCount++;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        return head.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        return tail.getElement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        if (size() == 0) return new Object[0];

        T[] list = (T[]) ((head.getElement() instanceof Comparable<?>) ? new Comparable[size()] : new Object[size()]);
        Node<T> currentNode = head;
        for (int i = 0; i < size(); i++) {
            list[i] = currentNode.getElement();
            currentNode = currentNode.getNext();
        }

        return list;
    }

    @Override
    public boolean contains(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        previousNode = null;
        targetNode = head;
        while (targetNode != null) {
            if (targetNode.getElement().equals(element)) return true;

            previousNode = targetNode;
            targetNode = targetNode.getNext();
        }

        return false;
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
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        StringBuilder message = new StringBuilder("(" + size() + ")[");
        Node<T> currentNode = head;
        while (currentNode != null) {
            message.append(currentNode.getElement()).append((currentNode == tail) ? "]" : ", ");
            currentNode = currentNode.getNext();
        }

        return String.valueOf(message);
    }

    private void removeElement() {
        if (previousNode == null) {
            head = head.getNext();
        } else {
            previousNode.setNext(targetNode.getNext());
            if (targetNode == tail) tail = previousNode;
        }
        count--;
        modCount++;
    }

    private class LinkedIterator implements Iterator<T> {
        private Node<T> previousNode, currentNode;
        private int expectedModCount;
        private boolean okToRemove;

        public LinkedIterator() {
            expectedModCount = modCount;
            previousNode = null;
            currentNode = head;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!hasNext()) throw new IndexOutOfBoundsException("Out of elements");

            previousNode = currentNode;
            currentNode = currentNode.getNext();
            okToRemove = true;

            return previousNode.getElement();
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!okToRemove) throw new UnsupportedOperationException("Operation not supported");

            try {
                LinkedList.this.remove(previousNode.getElement());
            } catch (EmptyCollectionException | ElementNullException | ElementNotFoundException e) {
                throw new RuntimeException(e);
            }

            expectedModCount = modCount;
            okToRemove = false;
        }
    }
}
