import exceptions.ElementNotComparableException;
import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.BinarySearchTreeADT;
import objects.binarysearchtrees.LinkedBinarySearchTree;

public class BinarySearchTreeTesting {
    public static void main(String[] args) {
        BinarySearchTreeADT<Integer> binarySearchTree = new LinkedBinarySearchTree<>();
        try {
            System.out.println("Added element: " + binarySearchTree.addElement(7));
            System.out.println("Added element: " + binarySearchTree.addElement(2));
            System.out.println("Added element: " + binarySearchTree.addElement(3));
            System.out.println("Added element: " + binarySearchTree.addElement(5));
            System.out.println("Added element: " + binarySearchTree.addElement(8));
            System.out.println("Added element: " + binarySearchTree.addElement(3));
            System.out.println("Added element: " + binarySearchTree.addElement(1));
            System.out.println(binarySearchTree);

            System.out.println("Removed element: " + binarySearchTree.removeElement(7));
            System.out.println("Removed element: " + binarySearchTree.removeMax());
            System.out.println("Root element: " + binarySearchTree.getRoot());
            System.out.println(binarySearchTree);
        } catch (ElementNullException | ElementNotComparableException | EmptyCollectionException | ElementNotFoundException e) {
            System.err.println(e);
        }
    }
}
