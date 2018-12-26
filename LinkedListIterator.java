import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T>
{
    private ListNode<T> previous;
    private ListNode<T> current;

    // No <T> needed in constructor!
    public LinkedListIterator(ListNode<T> header) {
        current = header;
    }

    public boolean hasNext() {
        return current.getNext() != null;
    }

    public T next() {
        if (!hasNext()) {
            throw new NullPointerException();
        }

        previous = current;
        current = current.getNext();

        return current.getValue();
    }

    public T getValue() {
        return current.getValue();
    }

    public void setValue(T value) {
        current.setValue(value);
    }

    public void add(T value) {
        // Create a new node
        ListNode<T> newNode = new ListNode<T>();

        // Store the new value in the new node
        newNode.setValue(value);

        // Link the new node to the next node
        newNode.setNext(current.getNext());

        // Link the current node to the new node
        current.setNext(newNode);

        // Advance to the node just created
        next();
    }

    public void remove() {
        // Prevent null pointer exception when either attempting to 
        // remove the header node, or attempting to remove two elements in a row 
        // without calling next().
        if (previous == null) {
            throw new IllegalStateException("Remove not allowed in the current state.");
        }

        previous.setNext(current.getNext());
        current = previous;
        previous = null;
    }

    //Remove method for just removing one item and relinking the nodes
    //Remove method for removing the ending on the linkedlist so after a certain node make it point to null
}