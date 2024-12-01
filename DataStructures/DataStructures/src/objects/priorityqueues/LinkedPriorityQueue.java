package objects.priorityqueues;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.PriorityQueueADT;
import objects.heaps.LinkedHeap;
import objects.nodes.PriorityQueueNode;

public class LinkedPriorityQueue<T> extends LinkedHeap<PriorityQueueNode<T>> implements PriorityQueueADT<T> {
    public LinkedPriorityQueue() {
        super();
    }

    public LinkedPriorityQueue(T element) throws ElementNullException {
        super(new PriorityQueueNode<>(element));
    }
}
