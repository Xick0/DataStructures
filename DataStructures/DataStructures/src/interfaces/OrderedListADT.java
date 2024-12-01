package interfaces;

import exceptions.ElementNotComparableException;
import exceptions.ElementNullException;

public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * Adds an element to this list at the proper location
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     * @throws ElementNotComparableException If the element isn't comparable
     */
    T add(T element) throws ElementNullException, ElementNotComparableException;
}
