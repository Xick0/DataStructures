package interfaces;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import objects.nodes.PriorityQueueNode;

public interface PriorityQueueADT<T> extends HeapADT<PriorityQueueNode<T>> {

    /**
     *
     * @param element The element to be added
     * @param priority The priority of the element
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    default T addElement(T element, int priority) throws ElementNullException {
        return addElement(new PriorityQueueNode<>(element, priority)).getElement();
    }

    /**
     * Removes the minimum element of this priority queue
     * @return The removed element
     * @throws EmptyCollectionException If this priority queue is empty
     */
    default T removeNext() throws EmptyCollectionException {
        return removeMin().getElement();
    }
}
