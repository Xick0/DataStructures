import exceptions.ElementNotComparableException;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.OrderedListADT;
import objects.lists.OrderedArrayList;
import objects.lists.OrderedDoubleLinkedList;
import objects.lists.OrderedLinkedList;

import java.util.Arrays;

public class OrderedListTesting {
    public static void main(String[] args) {
        OrderedListADT<Integer> orderedList = new OrderedDoubleLinkedList<>();

        try {
            System.out.println("Added element: " + orderedList.add(1));
            System.out.println("Added element: " + orderedList.add(3));
            System.out.println("Added element: " + orderedList.add(2));
            System.out.println("Added element: " + orderedList.add(5));
            System.out.println("Added element: " + orderedList.add(4));
            System.out.println(orderedList);

            System.out.println("Removed element: " + orderedList.removeFirst());
            System.out.println("Removed element: " + orderedList.removeLast());
            System.out.println("Removed element: " + orderedList.remove(3));
            System.out.println(orderedList);
            System.out.println("Array representation: " + Arrays.toString(orderedList.toArray()));

            orderedList.removeAll();
            System.out.println(orderedList);
            System.out.println("Array representation: " + Arrays.toString(orderedList.toArray()));
        } catch (ElementNullException | ElementNotComparableException | EmptyCollectionException | ElementNotFoundException e) {
            System.err.println(e);
        }
    }
}
