import abstractobjects.Searching;
import abstractobjects.Sorting;
import exceptions.CollectionNotOrderedException;
import exceptions.ElementNotComparableException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.OrderedListADT;
import interfaces.UnorderedListADT;
import objects.lists.OrderedArrayList;
import objects.lists.UnorderedLinkedList;

import java.util.Arrays;

public class SearchingTesting {
    public static void main(String[] args) {
        Integer[] unorderedArray = {1, 3, 2, 4};
        Integer[] orderedArray = {1, 2, 3, 4};
        UnorderedListADT<Integer> unorderedList = new UnorderedLinkedList<>();
        OrderedListADT<Integer> orderedList = new OrderedArrayList<>();

        try {
            unorderedList.addToRear(1);
            unorderedList.addToRear(3);
            unorderedList.addToRear(2);
            unorderedList.addToRear(4);

            orderedList.add(1);
            orderedList.add(3);
            orderedList.add(2);
            orderedList.add(4);

            System.out.println("Array linear search: " + Searching.linearSearch(unorderedArray, 2));
            System.out.println("List linear search: " + Searching.linearSearch(unorderedList, 2));
            System.out.println("Array binary search: " + Searching.binarySearch(orderedArray, 2));
            System.out.println("List binary search: " + Searching.binarySearch(orderedList, 2));
        } catch (ElementNullException | EmptyCollectionException | ElementNotComparableException | CollectionNotOrderedException e) {
            System.err.println(e);
        }
    }
}
