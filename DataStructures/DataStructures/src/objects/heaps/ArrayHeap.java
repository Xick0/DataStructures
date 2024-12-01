package objects.heaps;

import abstractobjects.binarytrees.ArrayBinaryTree;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.HeapADT;

public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {
    public ArrayHeap() {
        super();
    }

    public ArrayHeap(T element) throws ElementNullException {
        super(element);
    }

    public ArrayHeap(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayHeap(int initialCapacity, T element) throws ElementNullException {
        super(initialCapacity, element);
    }

    @Override
    public T addElement(T element)  {

        if (count == array.length) expandCapacity();
        array[count] = element;
        count++;
        if (count > 1) heapifyAdd();

        return element;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        T minElement = getRoot();
        array[0] = array[count -1];
        heapifyRemove();
        count--;

        return minElement;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        return getRoot();
    }

    @SuppressWarnings("unchecked")
    private void heapifyAdd() {
        int next = count - 1;
        T temp = array[next];
        while ((next != 0) && (((Comparable<T>) temp).compareTo(array[(next - 1) / 2]) < 0)) {
            array[next] = array[(next - 1) / 2];
            next = (next - 1) / 2;
        }

        array[next] = temp;
    }

    @SuppressWarnings("unchecked")
    private void heapifyRemove() {
        int node = 0, leftNode = 1, rightNode = 2, nextNode;

        if ((array[leftNode] == null) && (array[rightNode] == null)) nextNode = count;
        else if (array[leftNode] == null) nextNode = rightNode;
        else if (array[rightNode] == null) nextNode = leftNode;
        else if (((Comparable<T>) array[leftNode]).compareTo(array[rightNode]) < 0) nextNode = leftNode;
        else nextNode = rightNode;

        T temp = array[node];
        while ((nextNode < count) && (((Comparable<T>) array[nextNode]).compareTo(temp) < 0)) {
            array[node] = array[nextNode];
            node = nextNode;
            leftNode = 2 * node + 1;
            rightNode = 2 * (node + 1);
            if ((array[leftNode] == null) && (array[rightNode] == null)) nextNode = count;
            else if (array[leftNode] == null) nextNode = rightNode;
            else if (array[rightNode] == null) nextNode = leftNode;
            else if (((Comparable<T>) array[leftNode]).compareTo(array[rightNode]) < 0) nextNode = leftNode;
            else nextNode = rightNode;
        }

        array[node] = temp;
    }
}
