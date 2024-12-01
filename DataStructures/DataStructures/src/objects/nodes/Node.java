package objects.nodes;

import java.util.Objects;

public class Node<T> {
    protected T element;
    protected Node<T> next;

    public Node() {
        setElement(null);
        setNext(null);
    }

    public Node(T element) {
        setElement(element);
        setNext(null);
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Node<?> node = (Node<?>) obj;
        return Objects.equals(element, node.element) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, next);
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
