package objects.lists;

import abstractobjects.lists.ArrayList;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import interfaces.UnorderedListADT;

public class UnorderedArrayList<T> extends ArrayList<T> implements UnorderedListADT<T> {
    public UnorderedArrayList() {
        super();
    }

    public UnorderedArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public T addToFront(T element)  {

        if (size() == array.length) expandCapacity();
        addElement(0, element);

        return element;
    }

    @Override
    public T addToRear(T element){

        if (size() == array.length) expandCapacity();
        addElement(count, element);

        return element;
    }

    @Override
    public T addAfter(T element, T target) throws ElementNullException, ElementNotFoundException {
        if (element == null || target == null) throw new ElementNullException("Element is null");
        if (!contains(target)) throw new ElementNotFoundException("Element not found");

        if (size() == array.length) expandCapacity();
        addElement(targetIndex + 1, element);

        return element;
    }

    private void addElement(int index, T element) {
        for (int i = size(); i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        count++;
        modCount++;
    }
}
