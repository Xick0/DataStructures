package objects.lists;

import abstractobjects.lists.ArrayList;
import exceptions.ElementNotComparableException;
import exceptions.ElementNullException;
import interfaces.OrderedListADT;

public class OrderedArrayList<T> extends ArrayList<T> implements OrderedListADT<T> {
    public OrderedArrayList() {
        super();
    }

    public OrderedArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T add(T element) throws ElementNullException, ElementNotComparableException {
        if (element == null) throw new ElementNullException("Element is null");
        if (!(element instanceof Comparable<?>)) throw new ElementNotComparableException("Element not comparable");

        if (size() == array.length) expandCapacity();
        Comparable<T> comparableElement = (Comparable<T>) element;

        int targetIndex = 0;
        while (targetIndex < size() && comparableElement.compareTo(array[targetIndex]) > 0) targetIndex++;
        for (int i = size(); i > targetIndex; i--) {
            array[i] = array[i - 1];
        }

        array[targetIndex] = element;
        count++;
        modCount++;

        return element;
    }
}
