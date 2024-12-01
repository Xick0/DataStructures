package abstractobjects;

import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.UnorderedListADT;

import java.util.Random;

public abstract class Sorting {
    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) throws EmptyCollectionException {
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        boolean hasSwapped;
        do {
            hasSwapped = false;
            for (int i = 0; i < (array.length - 1); i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    if (!hasSwapped) hasSwapped = true;
                    swap(array, i, i + 1);
                }
            }
        } while (hasSwapped);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void bubbleSort(UnorderedListADT<T> list) throws EmptyCollectionException {
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        T[] array = (T[]) list.toArray();
        bubbleSort(array);
        fill(array, list);
    }

    public static <T extends Comparable<? super T>> void selectionSort(T[] array) throws EmptyCollectionException {
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        T temp, minValue;
        int indexOfMin;
        for (int i = 0; i < (array.length - 1); i++) {
            minValue = array[i];
            indexOfMin = i;
            for (int j = (i + 1); j < array.length; j++) {
                if (array[j].compareTo(minValue) < 0) {
                    minValue = array[j];
                    indexOfMin = j;
                }
            }

            swap(array, i, indexOfMin);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void selectionSort(UnorderedListADT<T> list) throws EmptyCollectionException {
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        T[] array = (T[]) list.toArray();
        selectionSort(array);
        fill(array, list);
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] array) throws EmptyCollectionException {
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        T temp;
        for (int i = 1, j; i < array.length; i++) {
            temp = array[i];
            for (j = i - 1; j >= 0 && array[j].compareTo(temp) > 0; j--) {
                array[j + 1] = array[j];
            }

            array[j + 1] = temp;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void insertionSort(UnorderedListADT<T> list) throws EmptyCollectionException {
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        T[] array = (T[]) list.toArray();
        insertionSort(array);
        fill(array, list);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergeSort(T[] array) throws EmptyCollectionException {
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        if (array.length < 2) return;

        int midIndex = array.length / 2;
        T[] leftHalf = (T[]) new Comparable[midIndex];
        T[] rightHalf = (T[]) new Comparable[array.length - midIndex];
        for (int i = 0; i < array.length; i++) {
            if (i < midIndex) {
                leftHalf[i] = array[i];
            } else {
                rightHalf[i - midIndex] = array[i];
            }
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(array, leftHalf, rightHalf);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergeSort(UnorderedListADT<T> list) throws EmptyCollectionException {
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        T[] array = (T[]) list.toArray();
        mergeSort(array);
        fill(array, list);
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] array) throws EmptyCollectionException {
        quickSort(array, 0, array.length - 1);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void quickSort(UnorderedListADT<T> list) throws EmptyCollectionException {
        if (list == null || list.isEmpty()) throw new EmptyCollectionException("List is empty");

        T[] array = (T[]) list.toArray();
        quickSort(array);
        fill(array, list);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] array, int lowIndex, int highIndex) throws EmptyCollectionException {
        if (array == null || array.length == 0) throw new EmptyCollectionException("Array is empty");

        if (lowIndex < highIndex) {
            int pivotIndex = partition(array, lowIndex, highIndex);
            quickSort(array, lowIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, highIndex);
        }
    }

    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <T extends Comparable<? super T>> void merge(T[] array, T[] leftHalf, T[] rightHalf) {
        int i = 0, j = 0, k = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i].compareTo(rightHalf[j]) <= 0) {
                array[k] = leftHalf[i];
                i++;
            } else {
                array[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftHalf.length) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }
        while (j < rightHalf.length) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    private static <T extends Comparable<? super T>> int partition(T[] array, int lowIndex, int highIndex) {
        int pivotIndex = new Random().nextInt(highIndex - lowIndex + 1) + lowIndex;
        T pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int i = lowIndex - 1;
        for (int j = lowIndex; j < highIndex; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, highIndex);

        return i + 1;
    }

    private static <T> void fill(T[] array, UnorderedListADT<T> list) {
        list.removeAll();
        for (int i = 0; i < array.length; i++) {
            list.addToRear(array[i]);
        }
    }
}
