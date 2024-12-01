import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;
import interfaces.StackADT;
import objects.stacks.ArrayStack;
import objects.stacks.LinkedStack;

public class StackTesting {
    public static void main(String[] args) {
        StackADT<Integer> stack = new LinkedStack<>();

        try {
            System.out.println("Added element: " + stack.push(1));
            System.out.println("Added element: " + stack.push(3));
            System.out.println("Added element: " + stack.push(4));
            System.out.println("Added element: " + stack.push(2));
            System.out.println(stack);

            System.out.println("Removed element: " + stack.pop());
            System.out.println("Removed element: " + stack.pop());
            System.out.println(stack);
        } catch (EmptyCollectionException e) {
            System.err.println(e);
        }
    }
}