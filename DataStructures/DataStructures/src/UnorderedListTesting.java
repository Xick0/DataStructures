import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.UnorderedListADT;
import objects.lists.UnorderedArrayList;
import objects.lists.UnorderedDoubleLinkedList;
import objects.lists.UnorderedLinkedList;

import java.util.Arrays;
import java.util.Iterator;

public class UnorderedListTesting {
    public static void main(String[] args) {
        UnorderedListADT<Integer> unorderedList = new UnorderedDoubleLinkedList<>();

        try {
            System.out.println("Added element: " + unorderedList.addToRear(1));
            System.out.println("Added element: " + unorderedList.addToRear(3));
            System.out.println("Added element: " + unorderedList.addToRear(2));
            System.out.println("Added element: " + unorderedList.addToRear(5));
            System.out.println("Added element: " + unorderedList.addToFront(4));
            System.out.println("Added element: " + unorderedList.addAfter(7, 4));
            System.out.println(unorderedList);

            System.out.println("Removed element: " + unorderedList.removeFirst());
            System.out.println("Removed element: " + unorderedList.removeLast());
            System.out.println("Removed element: " + unorderedList.remove(3));
            System.out.println(unorderedList);
            System.out.println("Array representation: " + Arrays.toString(unorderedList.toArray()));

            unorderedList.removeAll();
            System.out.println(unorderedList);
            System.out.println("Array representation: " + Arrays.toString(unorderedList.toArray()));
        } catch (ElementNullException | ElementNotFoundException | EmptyCollectionException e) {
            System.err.println(e);
        }
    }
}
