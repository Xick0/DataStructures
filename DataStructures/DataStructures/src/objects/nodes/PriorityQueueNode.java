package objects.nodes;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {
    private static int nextOrder = 0;
    private int priority, order;
    private T element;

    public PriorityQueueNode(T element) {
        setElement(element);
        setPriority(0);
        setOrder(nextOrder);
        nextOrder++;
    }

    public PriorityQueueNode(T element, int priority) {
        setElement(element);
        setPriority(priority);
        setOrder(nextOrder);
        nextOrder++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compareTo(PriorityQueueNode obj) {
        PriorityQueueNode<T> temp = obj;

        if (priority > temp.priority) return 1;
        else if (priority < temp.priority) return -1;
        else return Integer.compare(order, temp.order);
    }

    @Override
    public String toString() {
        return "PriorityQueueNode{" +
                "order=" + order +
                ", element=" + element +
                ", priority=" + priority +
                '}';
    }

    public static int getNextOrder() {
        return nextOrder;
    }

    public static void setNextOrder(int nextOrder) {
        PriorityQueueNode.nextOrder = nextOrder;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
