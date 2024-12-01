package objects.priorityqueues;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.PriorityQueueADT;
import objects.heaps.ArrayHeap;
import objects.nodes.PriorityQueueNode;

public class ArrayPriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>> implements PriorityQueueADT<T> {
    public ArrayPriorityQueue() {
        super();
    }

    public ArrayPriorityQueue(T element) throws ElementNullException {
        super(new PriorityQueueNode<>(element));
    }

    public ArrayPriorityQueue(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayPriorityQueue(int initialCapacity, T element) throws ElementNullException {
        super(initialCapacity, new PriorityQueueNode<>(element));
    }
}
