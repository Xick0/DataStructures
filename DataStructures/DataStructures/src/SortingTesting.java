import abstractobjects.Sorting;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.UnorderedListADT;
import objects.lists.UnorderedLinkedList;

import java.util.Arrays;
import java.util.Random;

public class SortingTesting {
    public static void main(String[] args) {
        try {
            int length = 10;
            long startTime, endTime;
            Integer[] array = new Integer[length];
            UnorderedListADT<Integer> list = new UnorderedLinkedList<>();

            Random random = new Random();
            for (int i = 0; i < length; i++) {
                int randomInt = random.nextInt(100);
                array[i] = randomInt;
                list.addToRear(randomInt);
            }

            startTime = System.currentTimeMillis();
            Sorting.mergeSort(array);
            endTime = System.currentTimeMillis();
            System.out.println("Array sorting time: " + (endTime - startTime) + " ms");
            System.out.println(Arrays.toString(array));

            startTime = System.currentTimeMillis();
            Sorting.mergeSort(list);
            endTime = System.currentTimeMillis();
            System.out.println("List sorting time: " + (endTime - startTime) + " ms");
            System.out.println(list);
        } catch ( EmptyCollectionException ignored) {}
    }
}
