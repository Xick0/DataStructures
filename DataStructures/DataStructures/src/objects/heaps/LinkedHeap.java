package objects.heaps;

import abstractobjects.binarytrees.LinkedBinaryTree;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.HeapADT;

public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {
    private HeapNode<T> lastNode;

    public LinkedHeap() {
        super();
        lastNode = null;
    }

    public LinkedHeap(T element) throws ElementNullException {
        super(element);
        lastNode = null;
    }

    @Override
    public T addElement(T element)  {

        HeapNode<T> newNode = new HeapNode<>(element);
        if (isEmpty()) {
            root = newNode;
        } else {
            HeapNode<T> nextParent = getNextParentAdd();
            if (nextParent.getLeft() == null) nextParent.setLeft(newNode);
            else nextParent.setRight(newNode);

            newNode.setParent(nextParent);
        }
        lastNode = newNode;
        count++;
        if (count > 1) heapifyAdd();

        return element;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        T minElement = getRoot();
        if (size() == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> nextLastNode = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode) lastNode.getParent().setLeft(null);
            else lastNode.getParent().setRight(null);

            root.setElement(lastNode.getElement());
            lastNode = nextLastNode;
            heapifyRemove();
        }
        count--;

        return minElement;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        return getRoot();
    }

    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() != result)) result = result.getParent();

        if (result != root) {
            if (result.getParent().getRight() == null) result = result.getParent();
            else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null) result = (HeapNode<T>) result.getLeft();
            }
        } else while (result.getLeft() != null) result = (HeapNode<T>) result.getLeft();

        return result;
    }

    @SuppressWarnings("unchecked")
    private void heapifyAdd() {
        HeapNode<T> next = lastNode;
        T temp = next.getElement();

        while ((next != root) && (((Comparable<T>) temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() == result)) result = result.getParent();

        if (result != root) result = (HeapNode<T>) result.getParent().getLeft();
        while (result.getRight() != null) result = (HeapNode<T>) result.getRight();

        return result;
    }

    @SuppressWarnings("unchecked")
    private void heapifyRemove() {
        HeapNode<T> node = (HeapNode<T>) root, left = (HeapNode<T>) node.getLeft(), right = (HeapNode<T>) node.getRight(), next;

        if ((left == null) && (right == null)) next = null;
        else if (left == null) next = right;
        else if (right == null) next = left;
        else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0) next = left;
        else next = right;

        T temp = node.getElement();
        while ((next != null) && (((Comparable<T>) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null)) next = null;
            else if (left == null) next = right;
            else if (right == null) next = left;
            else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0) next = left;
            else next = right;
        }

        node.setElement(temp);
    }
    public void removeAllElements() {
        root = null;
        lastNode = null;
        count = 0;
    }

    private static class HeapNode<T> extends BinaryTreeNode<T> {
        protected HeapNode<T> parent;

        public HeapNode() {
            super();
            setParent(null);
        }

        public HeapNode(T element) {
            super(element);
            setParent(null);
        }

        public HeapNode<T> getParent() {
            return parent;
        }

        public void setParent(HeapNode<T> parent) {
            this.parent = parent;
        }
    }
}
