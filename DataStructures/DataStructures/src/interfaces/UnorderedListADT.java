package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;

public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds an element to this list's front
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T addToFront(T element);

    /**
     * Adds an element to this list's rear
     * @param element The element to be added
     * @return The added element
     * @throws ElementNullException If the element is null
     */
    T addToRear(T element);

    /**
     * Adds an element to this list after a specified target
     * @param element The element to be added
     * @param target The reference element
     * @return The added element
     * @throws ElementNullException If the target/element is null
     * @throws ElementNotFoundException If the target isn't in this list
     */
    T addAfter(T element, T target) throws ElementNullException, ElementNotFoundException;
}
