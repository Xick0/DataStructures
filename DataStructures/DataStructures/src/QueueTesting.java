import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.QueueADT;
import objects.queues.ArrayQueue;
import objects.queues.CircularArrayQueue;
import objects.queues.LinkedQueue;

import java.util.Arrays;

public class QueueTesting {
    public static void main(String[] args) {
        QueueADT<Integer> queue = new LinkedQueue<>();

        System.out.println("Added element: " + queue.enqueue(1));
        System.out.println("Added element: " + queue.enqueue(3));
        System.out.println("Added element: " + queue.enqueue(4));
        System.out.println("Added element: " + queue.enqueue(2));
        System.out.println(queue);

        System.out.println("Removed element: " + queue.dequeue());
        System.out.println("Removed element: " + queue.dequeue());
        System.out.println(queue);
    }
}
