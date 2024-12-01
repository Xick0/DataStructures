import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.HeapADT;
import objects.heaps.ArrayHeap;
import objects.heaps.LinkedHeap;

public class HeapTesting {
    public static void main(String[] args) {
        HeapADT<Integer> heap = new LinkedHeap<>();

        try {
            System.out.println(heap);
            System.out.println("Added element: " + heap.addElement(2));
            System.out.println("Added element: " + heap.addElement(3));
            System.out.println("Added element: " + heap.addElement(4));
            System.out.println("Added element: " + heap.addElement(1));
            System.out.println("Root element: " + heap.findMin());
            System.out.println(heap);

            System.out.println("Removed element: " + heap.removeMin());
            System.out.println("Root element: " + heap.findMin());
            System.out.println(heap);
        } catch (EmptyCollectionException e) {
            System.err.println(e);
        }
    }
}
