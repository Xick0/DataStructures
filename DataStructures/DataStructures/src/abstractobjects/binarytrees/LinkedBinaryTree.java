package abstractobjects.binarytrees;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import interfaces.QueueADT;
import interfaces.UnorderedListADT;
import objects.lists.UnorderedArrayList;
import objects.lists.UnorderedLinkedList;
import objects.queues.CircularArrayQueue;

import java.util.Iterator;
import java.util.Objects;

public abstract class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;
    protected int count;

    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    public LinkedBinaryTree(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        root = new BinaryTreeNode<>(element);
        count = 1;
    }

    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("BinaryTree is empty");

        return root.getElement();
    }

    @Override
    public T find(T element) throws ElementNullException, EmptyCollectionException, ElementNotFoundException {
        if (element == null) throw new ElementNullException("Element is null");
        if (isEmpty()) throw new EmptyCollectionException("BinaryTree is empty");

        BinaryTreeNode<T> currentNode = findAgain(root, element);
        if (currentNode == null) throw new ElementNotFoundException("Element not found");

        return currentNode.getElement();
    }

    @Override
    public boolean contains(T element) throws ElementNullException {
        if (element == null) throw new ElementNullException("Element is null");

        try {
            find(element);
            return true;
        } catch (ElementNullException | EmptyCollectionException | ElementNotFoundException e) {
            return false;
        }
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
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> list = new UnorderedArrayList<>();
        inOrder(root, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> list = new UnorderedLinkedList<>();
        preOrder(root, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> list = new UnorderedLinkedList<>();
        postOrder(root, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedListADT<T> unorderedList = new UnorderedArrayList<>();
        levelOrder(unorderedList);

        return unorderedList.iterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "(" + size() + ")[]";

        Iterator<T> treeIterator = iteratorLevelOrder();
        int counter = 1;
        StringBuilder message = new StringBuilder("(" + size() + ")[");
        while (treeIterator.hasNext()) {
            message.append(treeIterator.next()).append((counter < size()) ? ", " : "]");
            counter++;
        }

        return String.valueOf(message);
    }

    private BinaryTreeNode<T> findAgain(BinaryTreeNode<T> current, T element) {
        if (current == null) return null;
        if (current.getElement().equals(element)) return current;

        BinaryTreeNode<T> temp = findAgain(current.getLeft(), element);
        if (temp == null) temp = findAgain(current.getRight(), element);

        return temp;
    }

    private void inOrder(BinaryTreeNode<T> node, UnorderedListADT<T> list) {
            if (node != null) {
                inOrder(node.getLeft(), list);
                list.addToRear(node.getElement());
                inOrder(node.getRight(), list);
            }
    }

    private void preOrder(BinaryTreeNode<T> node, UnorderedListADT<T> list) {
            if (node != null) {
                list.addToRear(node.getElement());
                preOrder(node.getLeft(), list);
                preOrder(node.getRight(), list);
            }
    }

    private void postOrder(BinaryTreeNode<T> node, UnorderedListADT<T> list) {
            if (node != null) {
                postOrder(node.getLeft(), list);
                postOrder(node.getRight(), list);
                list.addToRear(node.getElement());
            }
    }

    private void levelOrder(UnorderedListADT<T> unorderedList) {
        BinaryTreeNode<T> currentNode = root;
        QueueADT<BinaryTreeNode<T>> queue = new CircularArrayQueue<>();
        queue.enqueue(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.dequeue();

            unorderedList.addToRear(currentNode.getElement());
            if (currentNode.getLeft() != null) queue.enqueue(currentNode.getLeft());
            if (currentNode.getRight() != null) queue.enqueue(currentNode.getRight());
        }
    }

    protected static class BinaryTreeNode<T> {
        protected T element;
        protected BinaryTreeNode<T> left, right;

        public BinaryTreeNode() {
            this(null);
        }

        public BinaryTreeNode(T element) {
            setElement(element);
            setLeft(null);
            setRight(null);
        }

        public int numChildren() {
            int children = 0;

            if (left != null) children += 1 + left.numChildren();
            if (right != null) children += 1 + right.numChildren();

            return children;
        }

        @Override
        public String toString() {
            return "BinaryTreeNode{" +
                    "element=" + element +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            BinaryTreeNode<?> binaryTreeNode = (BinaryTreeNode<?>) obj;
            return Objects.equals(element, binaryTreeNode.element) && Objects.equals(left, binaryTreeNode.left) && Objects.equals(right, binaryTreeNode.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, left, right);
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public BinaryTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode<T> left) {
            this.left = left;
        }

        public BinaryTreeNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode<T> right) {
            this.right = right;
        }
    }
}
