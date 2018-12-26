import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T> {
    private ListNode<T> header = new ListNode<T>();

    public boolean isEmpty() {
        return header.getNext() == null;
    }

    public T getFirst() {
        ListNode<T> first = header.getNext();
        if (first == null) {
            throw new NullPointerException();
        }
        else {
            return first.getValue();
        }
    }

    public void addFirst(T element) {
        // Create a new node
        ListNode<T> newNode = new ListNode<T>();

        // Store the new element in the new node
        newNode.setValue(element);

        // Link the new node to the old first node
        newNode.setNext(header.getNext());

        // Set the new node as the first node
        header.setNext(newNode);
    }

    public void removeFirst() {
        ListNode<T> first = header.getNext();
        if (first == null) {
            throw new NullPointerException();
        }
        else {
            header.setNext(first.getNext());
        }
    }

    public void clear() {
        header.setNext(null);
    }

    private ListNode<T> findByIndex(int index) {
        int k = 0;
        ListNode<T> current = header.getNext();

        while (k < index && current != null) {
            k++;
            current = current.getNext();
        }

        if (current == null) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + k);
        }
        else {
            return current;
        }
    }

    public T get(int index) {
        return findByIndex(index).getValue();
    }

    public void set(int index, T element) {
        findByIndex(index).setValue(element);
    }

    private void scanToIndex(Iterator<T> iterator, int index) {
        int k = -1;

        while (k < index && iterator.hasNext()) {
            k++;
            iterator.next();
        }

        if (k < index) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + (k + 1));
        }
    }

    private void scanToEnd(Iterator<T> iterator) {
        int k = -1;

        while (iterator.hasNext()) {
            k++;
            iterator.next();
        }
    }

    public int size() {
        int k = 0;
        ListNode<T> current = header.getNext();

        while (current != null) {
            k++;
            current = current.getNext();
        }
        return k;
    }


    public void sort(Comparator<T> c) {//descending
        T temp;

        if(isEmpty()){
            throw new NullPointerException();
        }
        else{
            ListNode<T> previous = header.getNext();
            ListNode current = header.getNext().getNext();
            boolean swap = true;

            while(swap){
                swap = false;
                while(current != null){
                    if(c.compare((T) current.getValue(),(T) previous.getValue()) > 0){// > suppose
                        temp = (T) current.getValue();
                        current.setValue(previous.getValue());
                        previous.setValue(temp);
                        swap = true;
                    }
                    previous = current;
                    current = current.getNext();
                }
                previous = header.getNext();
                current = header.getNext().getNext();
            }
        }
    }


    public static class CompareTo implements Comparator<Item>{
        @Override
        public int compare(Item o1, Item o2) {
            if(o1.getValueWeightRatio() > o2.getValueWeightRatio()){
                return 1;
            }
            if(o1.getValueWeightRatio() < o2.getValueWeightRatio()){
                return -1;
            }
            else{
                return 0;
            }
        }
    }

    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(header);
    }

}
