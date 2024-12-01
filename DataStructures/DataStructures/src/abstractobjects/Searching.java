package abstractobjects;

import exceptions.CollectionNotOrderedException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.ListADT;
import interfaces.OrderedListADT;

public abstract class Searching {
    public static <T> boolean linearSearch(T[] array, T target) throws ElementNullException, EmptyCollectionException {
        if (target == null) throw new ElementNullException("Element is null");
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        return find(array, target);
    }

    public static <T> boolean linearSearch(ListADT<T> list, T target) throws ElementNullException, EmptyCollectionException {
        if (target == null) throw new ElementNullException("Element is null");
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        return find(list, target);
    }

    public static <T extends Comparable<? super T>> boolean binarySearch(T[] array, T target) throws ElementNullException, EmptyCollectionException, CollectionNotOrderedException {
        if (target == null) throw new ElementNullException("Element is null");
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");
        if (!isSorted(array)) throw new CollectionNotOrderedException("Array is not sorted");

        return binarySearch(array, 0, array.length - 1, target);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> boolean binarySearch(ListADT<T> list, T target) throws ElementNullException, EmptyCollectionException, CollectionNotOrderedException {
        if (target == null) throw new ElementNullException("Element is null");
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("Array is empty");

        return binarySearch((T[]) list.toArray(), 0, list.size() - 1, target);
    }

    private static <T extends Comparable<? super T>> boolean binarySearch(T[] array, int left, int right, T target) {
        if (left > right) return false;

        int mid = (left + right) / 2;
        int compareTo = array[mid].compareTo(target);
        if (compareTo == 0) {
            return true;
        } else if (compareTo > 0) {
            return binarySearch(array, left, mid - 1, target);
        } else {
            return binarySearch(array, mid + 1, right, target);
        }
    }

    private static <T> boolean find(T[] array, T target) {
        for (T element : array) {
            if (element != null && element.equals(target)) return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private static <T> boolean find(ListADT<T> list, T target) {
        boolean isOrdered = list instanceof OrderedListADT<T>;

        for (T element : list) {
            if (isOrdered) {
                if (((Comparable<T>) element).compareTo(target) == 0) return true;
                if (((Comparable<T>) element).compareTo(target) > 0) return false;
            } else {
                if (element.equals(target)) return true;
            }
        }

        return false;
    }

    protected static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int i = 0; i < (array.length - 1); i++) {
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        }

        return true;
    }

    private static <T extends Comparable<? super T>> boolean isSorted(ListADT<T> list) {
        T previousElement = null;
        for (T element : list) {
            if (previousElement != null && previousElement.compareTo(element) > 0) return false;
            previousElement = element;
        }

        return true;
    }
}
