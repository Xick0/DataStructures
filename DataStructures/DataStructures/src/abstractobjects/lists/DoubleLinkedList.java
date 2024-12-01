package abstractobjects.lists;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.ListADT;
import objects.nodes.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public abstract class DoubleLinkedList<T> implements ListADT<T> {
    protected DoubleNode<T> head, tail, targetNode;
    protected int count, modCount;

    public DoubleLinkedList() {
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
            contains(head.getElement());
        } catch (ElementNullException ignored) {}
        removeElement();

        return element;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List is empty");

        T element = tail.getElement();
        try {
            contains(tail.getElement());
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
        DoubleNode<T> currentNode = head;
        for (int i = 0; i < size(); i++) {
            list[i] = currentNode.getElement();
            currentNode = currentNode.getNext();
        }

        return list;
    }

    @Override
    public boolean contains(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        targetNode = head;
        while (targetNode != null) {
            if (targetNode.getElement().equals(element)) return true;
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
        return new DoubleLinkedIterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        StringBuilder message = new StringBuilder("(" + size() + ")[");
        DoubleNode<T> currentNode = head;
        while (currentNode != null) {
            message.append(currentNode.getElement()).append((currentNode == tail) ? "]" : ", ");
            currentNode = currentNode.getNext();
        }

        return String.valueOf(message);
    }

    private void removeElement() {
        if (targetNode.getPrev() == null) {
            head = head.getNext();
        } else {
            targetNode.getPrev().setNext(targetNode.getNext());
            if (targetNode == tail) tail = targetNode.getPrev();
        }

        count--;
        modCount++;
    }

    private class DoubleLinkedIterator implements Iterator<T> {
        private DoubleNode<T> currentNode;
        private int expectedModCount;
        private boolean okToRemove;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!hasNext()) throw new IndexOutOfBoundsException("Out of elements");

            T element = currentNode.getElement();
            currentNode = currentNode.getNext();
            okToRemove = true;

            return element;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException("List has been modified");
            if (!okToRemove) throw new UnsupportedOperationException("Operation not supported");

            try {
                DoubleLinkedList.this.remove(currentNode.getPrev().getElement());
            } catch (EmptyCollectionException | ElementNullException | ElementNotFoundException e) {
                throw new RuntimeException(e);
            }

            expectedModCount = modCount;
            okToRemove = false;
        }
    }

    protected static class DoubleNode<T> extends Node<T> {
        private DoubleNode<T> prev;

        public DoubleNode() {
            super();
            setPrev(null);
        }

        public DoubleNode(T element) {
            super(element);
            setPrev(null);
        }

        @Override
        public String toString() {
            return "DoubleNode{" +
                    "element=" + element +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            DoubleNode<?> doubleNode = (DoubleNode<?>) obj;
            return Objects.equals(element, doubleNode.element) && Objects.equals(prev, doubleNode.prev) && Objects.equals(next, doubleNode.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, prev, next);
        }

        public DoubleNode<T> getPrev() {
            return prev;
        }

        public void setPrev(DoubleNode<T> prev) {
            this.prev = prev;
        }

        public DoubleNode<T> getNext() {
            return (DoubleNode<T>) next;
        }

        public void setNext(DoubleNode<T> next) {
            this.next = next;
        }
    }
}
